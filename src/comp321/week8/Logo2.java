package comp321.week8;

import comp321.week8.Kattio;

/**
 * Solution to the Logo2 Problem on Kattis.
 * (https://open.kattis.com/problems/logo2)
 * 
 * Topic of Geometry.
 * 
 * @author Yann Vonlanthen (260808862)
 *
 */
public class Logo2 {

    public static void main(String[] args) {

        // Uncomment this to test on a local file
        // File initialFile = new File("src/comp321/week7/getshorty.in");
        // InputStream targetStream = new FileInputStream(initialFile);
        // Kattio io = new Kattio(targetStream, System.out);

        // Use Kattio for I/O as suggested in tutorial
        Kattio io = new Kattio(System.in, System.out);


        // Read input
        int testCases = io.getInt();
        
        // Iterate through all problem instances (max 20)
        for(int i = 0; i < testCases; i++) {
            int nCmd = io.getInt();
            
            
        
        }

        // Close I/O stream and terminate
        io.close();

            
    }

}
