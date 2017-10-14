package comp321.week5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import comp321.week4.Kattio;

/**
 * Restaurant Orders. Assignment 4. (https://open.kattis.com/problems/orders)
 * 
 * DP-Algorithm implementation
 * 
 * @author Yann Vonlanthen (260808862)
 *
 */
public class RestaurantOrdersBottomUp {

    /*
     * Global variables
     */
    public static int maxOrder = 30000;

    public static void main(String[] args) throws FileNotFoundException {
        
        // Uncomment this to test on a local file
        // File initialFile = new File("src/comp321/week4/orders-1.txt");
        // InputStream targetStream = new FileInputStream(initialFile);
        // Kattio io = new Kattio(targetStream, System.out);
        
        // Use Kattio for I/O as suggested in tutorial
        Kattio io = new Kattio(System.in, System.out);

        // Get the numbers of items on menu
        int n = io.getInt();

        // Read all menu items
        ArrayList<Integer> items = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int item = io.getInt();
                items.add(item);
        }

        // Create DP-Array
        int[][] r = new int[n][maxOrder + 1];

        // When 0 money left there is one solution: not ordering anything!
        for (int i = 0; i < n; i++)
            r[i][0] = 1;
        
        
        // Fill out 2D-array, Note: I believe a 1D array could also be sufficient
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < maxOrder + 1; j++) {

                int dontBuy;
                if (i == 0){
                    dontBuy = -1;
                }else {
                    dontBuy = r[i-1][j];                    
                }
                
                int buy;
                if (j - items.get(i) < 0){
                    buy = -1;                    
                }
                else {
                    buy = r[i][j-items.get(i)];                    
                }
                
                // --- case 1: ambiguous solution 
                if ((buy >= 0 && dontBuy >= 0) ||
                     buy == Integer.MAX_VALUE ||
                     dontBuy == Integer.MAX_VALUE) {
                    r[i][j] = Integer.MAX_VALUE;
                }
                
                // --- case 2: there is a solution by buying item i
                else if (buy >= 0) {
                    r[i][j] = 1;
                }
                
                // --- case 3: there is a solution by not buying item i
                else if (dontBuy >= 0) {
                    r[i][j] = 0;
                }
                
                // --- case 4: there is no solution
                else if (dontBuy < 0 && buy < 0){
                    r[i][j] = -1;
                }
                
                // --- unexpected (can be deleted)
                else {
                    throw new Error();
                }
            }
        }
        
        
        // Get number of orders placed
        int orders = io.getInt();

        // Iterate through all orders and find correct answer
        for (int i = 0; i < orders; i++) {
            int orderValue = io.getInt();
            int x = r[n-1][orderValue];

            // Output correct values
            if (x == Integer.MAX_VALUE) {
                io.println("Ambiguous");
            } else if (x == -1) {
                io.println("Impossible");
            } else if (x >= 0){
                tracebackItems(io, r, orderValue, items);
            } else {
                throw new Error();
            }
        }
        
        // Close I/O stream and terminate
        io.close();
    }

    
    /**
     * This method prints a solution to given io interface.
     */
    public static void tracebackItems(Kattio io, int[][] r, int order, ArrayList<Integer> items) {
        int i = items.size() - 1;
        int j = order;
        ArrayList<Integer> solution = new ArrayList<>();
        
        // go through array and save items that were ordered
        while(j > 0){
            int x = r[i][j];
            if(x == 0){
                i -= 1;
            } 
            else if(x == 1){
                int price = items.get(i);
                solution.add(i + 1);
                j -= price;
            } else {
                throw new Error();
            }
        }

        // sort and then print solution with spaces
        Collections.sort(solution);
        StringBuilder sb = new StringBuilder();
        for (int x : solution) {
            sb.append(x).append(" ");
        }

        String result = sb.deleteCharAt(sb.length() - 1).toString();
        io.println(result);
    }
}
