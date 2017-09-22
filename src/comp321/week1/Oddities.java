package comp321.week1;

import comp321.week1.Kattio;

/**
 * Oddities. 
 * (https://open.kattis.com/problems/oddities)
 * 
 * @author Yann Vonlanthen (260808862)
 *
 */
public class Oddities {

    public static void main(String[] args) {
        
        // Use Kattio for I/O as suggested in tutorial
        Kattio k = new Kattio(System.in, System.out);
        int n = k.getInt();
        int x;
        
        // Iterate over all problem instances
        for (int i = 0; i < n; i++) {
            x = k.getInt();
            
            // Check whether given number is odd or even
            if (x % 2 == 0)
                k.println(x + " is even");
            else
                k.println(x + " is odd");
        }
        
        // Close I/O stream and terminate
        k.close();
    }
}
