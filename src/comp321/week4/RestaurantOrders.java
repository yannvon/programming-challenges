package comp321.week4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Restaurant Orders. Assignment 4. (https://open.kattis.com/problems/orders)
 * 
 * DP-Algorithm implementation
 * 
 * @author Yann Vonlanthen (260808862)
 *
 */
public class RestaurantOrders {

    /*
     * Global variables
     */
    public static int maxOrder = 30000;

    public static void main(String[] args) throws FileNotFoundException {
        // Uncomment this to test on a local file
//        File initialFile = new File("src/comp321/week4/orders-2.in");
//        InputStream targetStream = new FileInputStream(initialFile);
//        Kattio io = new Kattio(targetStream, System.out);
        // Use Kattio for I/O as suggested in tutorial
        Kattio io = new Kattio(System.in, System.out);


        // Get the numbers of items on menu
        int n = io.getInt();

        // Read all menu items
        // TODO better to use native java array?
        ArrayList<Integer> items = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int item = io.getInt();
                items.add(item);
        }

        Collections.sort(items);
        
        // Initialize DP-Array
        int[][] r = new int[n][maxOrder]; // FIXME allocate smarter dont use max order
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < maxOrder; j++) {
                r[i][j] = Integer.MIN_VALUE;
            }
        }
        
        // Initialize Solution Array
        int[][] m = new int[n][maxOrder]; // FIXME allocate smarter dont use max order
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < maxOrder; j++) {
                m[i][j] = Integer.MIN_VALUE;
            }
        }
        

        // Get number of orders placed
        int orders = io.getInt();

        // Iterate through all orders and find correct answer
        // while filling out DP-array if necessary

        for (int i = 0; i < orders; i++) {
            int orderValue = io.getInt();
            int x = memoized_orders_aux(r, orderValue, n-1, items, m);

            // Output correct values
            if (x == Integer.MAX_VALUE) {
                io.println("Ambiguous");
            } else if (x == 0) {
                io.println("Impossible");
            } else if (x == 1){
                tracebackItems(io, r, orderValue, n-1, items, m);
            } else {
                throw new Error();
            }
        }

        // Close I/O stream and terminate
        io.close();
    }

    public static int memoized_orders_aux(int[][] r, int order,
            int itemIndex, ArrayList<Integer> items, int[][] m) {
       
        if (order == 0){
            return 1; //aim reached            
        }
        if (order < 0 || itemIndex < 0) {
            return 0; //overshoot, no more items left            
        }
        
        int x = r[itemIndex][order];
        if (x > Integer.MIN_VALUE) {
            return x; // already computed
        }

        // Here the magic happens

        int dontBuyMostExpensive = memoized_orders_aux(r, order, itemIndex - 1, items, m);
        int buyMostExpensive = memoized_orders_aux(r, order - items.get(itemIndex), itemIndex, items, m);

        // FIXME rearrange test cases

        // --- Case 1 : problem has more than two solutions ---
        if (buyMostExpensive > 1
                || dontBuyMostExpensive > 1
                || (buyMostExpensive == 1 && dontBuyMostExpensive == 1 )) {
            
            r[itemIndex][order] = Integer.MAX_VALUE;    // store max value to indicate ambiguous
            m[itemIndex][order] = Integer.MAX_VALUE;    // store max value to indicate ambiguous
            
            return Integer.MAX_VALUE;
        }

        // --- Case 2: one solution found ---
        else if (buyMostExpensive == 1) {
            m[itemIndex][order] = 1;    // store index of bought item
            r[itemIndex][order] = 1;    // store price of bought item
            return 1;
        }
        
        // --- Case 3: one solution found ---
        else if (dontBuyMostExpensive == 1) {
            m[itemIndex][order] = -1;  // store -1 to indicate an item was skipped
            r[itemIndex][order] = 1;
            return 1;
        }
        
        // --- Case 4: none of above, no solution ---
        r[itemIndex][order] = 0;    // store 0 to indicate no solution found
        m[itemIndex][order] = 0;    // store 0 to indicate no solution found
        return 0;
    }

    public static void tracebackItems(Kattio io, int[][] r, int value, int itemIndex, ArrayList<Integer> items, int[][] m) {
        int moneyLeft = value;
        int item = itemIndex;
        
        LinkedList<Integer> ordered = new LinkedList<>();

        while (moneyLeft != 0) {
            int x = m[item][moneyLeft];
            if (x == -1){
                item--;
            } else if (x == 1){
                ordered.add(item+1);
                moneyLeft -= items.get(item);
            }
        }
        Collections.sort(ordered);
        
        for (int i = 0; i < ordered.size()-1; i++){
            io.print(ordered.get(i));
            io.print(" ");
        }
        io.println(ordered.get(ordered.size()-1));
    }
}
