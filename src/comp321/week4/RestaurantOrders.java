package comp321.week4;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Restaurant Orders.
 * Assignment 4.
 * (https://open.kattis.com/problems/orders)
 * 
 * DP-Algorithm implementation
 * 
 * @author Yann Vonlanthen (260808862)
 *
 */
public class RestaurantOrders {
    
    //Global variables
    public static int maxOrder = 30000;
    

    public static void main(String[] args) {
     
        // Uncomment this to test on a local file
        /*
        File initialFile = new File(
          "src/comp321/week2/virtualfriends.txt"); 
        InputStream targetStream = new FileInputStream(initialFile); 
        Kattio io =  new Kattio(targetStream, System.out);
        */

        // Use Kattio for I/O as suggested in tutorial
        Kattio io = new Kattio(System.in, System.out);

        // Get the numbers of items on menu
        int n = io.getInt();
        
        // Read all menu items
        // TODO better to use native java array?
        ArrayList<Integer> items = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            items.add(io.getInt());
        }
        
        // Get number of orders placed
        int orders = io.getInt();
        
        // Initialize DP-Array

        int[] r = new int[maxOrder];
        for (int i = 0; i < maxOrder; i++) {
            r[i] = Integer.MIN_VALUE;
        }
        
        // Iterate through all orders and find correct answer 
        // while filling out DP-array if necessary
        
        for (int i = 0; i < orders; i++){
            int orderValue = io.getInt();
            int orderPossibilities = memoized_orders_aux(r, orderValue);
            
            // Output correct values
            
            
        }
        

        
        
        // Close I/O stream and terminate
        io.close();
    }
    
    // auxiliary method
    public static int memoized_orders_aux(int[] r, int value) {
        if(value >= maxOrder)
            return 0;   //aim was reached :)

        double x = r[value];
        if (x > Integer.MIN_VALUE)
            return (int) x; //already computed
        
        
        
        
        
        return 0;

    }

}
