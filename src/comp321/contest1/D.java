package comp321.contest1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Problem D
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
            
            // set up dp array
            int[] dp = new int[10001];
            int[] billsUsed = new int[10001];
            dp[0] = 0;
            for (int m = 1; m < price+1; m++) {
                int closestSoFar = Integer.MAX_VALUE;
                int closestBillUsed = Integer.MAX_VALUE;
                
                for(int b = 0; b < n; b++) {
                    int candidate;
                    int candidateBUsed;
                    
                    if(price - bills[b] <= 0) {
                        candidate = bills[b];
                        candidateBUsed = 1;
                    } else {
                        candidate = bills[b] + dp[price - bills[b]];
                        candidateBUsed = 1 + billsUsed[price - bills[b]];
                    }

                    if (candidate > m && (candidate < closestSoFar || 
                            (candidate == closestSoFar && candidateBUsed < closestBillUsed))) {
                        closestSoFar = candidate;
                        closestBillUsed = candidateBUsed;
                    }
                }
                
                dp[m] = closestSoFar;
                billsUsed[m] = closestBillUsed;
            }
            io.println(dp[price] + " " + billsUsed[price]);
            
        }

        // Close I/O stream and terminate
        io.close();
    }
}
