package comp321.contest1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Problem D
 * Exact Change
 * 
 * @author Yann Vonlanthen (260808862)
 *
 */
public class D {

    public static void main(String[] args) throws FileNotFoundException {

        // Uncomment this to test on a local file
//         File initialFile = new File("src/comp321/contest1/d.in");
//         InputStream targetStream = new FileInputStream(initialFile);
//         Kattio io = new Kattio(targetStream, System.out);

        // Use Kattio for I/O as suggested in tutorial
        Kattio io = new Kattio(System.in, System.out);

        // Read input and get Data structures ready
        int testCases = io.getInt();
        
        // Iterate through all problem instances
        for (int i = 0; i < testCases; i++) {
            int price = io.getInt();
            int n = io.getInt(); // at most 100
            
            int[] bills = new int[n+1];
            for (int j = 0; j < n; j++){
                bills[j] = io.getInt();
            }
            
            /*
             *  COMPLETLY DIFFERENT DP THAN LAST TRY
             *  dp array only stores min number of bills to get to this value
             *  solution can then be retrieved from that by checking which value can be reached
             */

            // set up dp array
            int dpArraySize = 200000;  //should be enough since 10000 is max coin and price size
            int[] dp = new int[dpArraySize];
            dp[0] = 0;
            for(int j = 1; j < dpArraySize; j++){
                dp[j] = -1;
            }
            
            /*
             *  Iterate through bills, every time checking if it can reduce the number of 
             *  bills used or get us to a new amount of money.
             */
            for(int j = 0; j < n; j++) {
                /*
                 * iterate through dpArray, note that we are not interested in improving 
                 * above price.
                 * also, we need to go from large to low values, 
                 * in order to not used same bill multiple times
                 */
                for(int a = price; a >= 0; a--) {
                    if(dp[a] != -1 && (dp[a + bills[j]] > dp[a] + 1 || dp[a + bills[j]] == -1)){
                        dp[a + bills[j]] = dp[a] + 1;
                    }
                }
            }
            
            /*
             * Start at price we want to reach and find first array entry that was reached.
             * Print the price it represents (index) 
             * and the amount of bills used to get there (value)
             */
            int currentMoneyPaied = price; //min we have to pay
            while(dp[currentMoneyPaied] == -1) {
                currentMoneyPaied++;
            }
            
            io.println(currentMoneyPaied + " " + dp[currentMoneyPaied]);
        }

        // Close I/O stream and terminate
        io.close();
    }
}
