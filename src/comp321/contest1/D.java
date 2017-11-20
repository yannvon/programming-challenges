package comp321.contest1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Problem D
 * 
 * @author Yann Vonlanthen (260808862)
 *
 */
public class D {

    public static void main(String[] args) throws FileNotFoundException {

        // Uncomment this to test on a local file
         File initialFile = new File("src/comp321/week8/d.in");
         InputStream targetStream = new FileInputStream(initialFile);
         Kattio io = new Kattio(targetStream, System.out);

        // Use Kattio for I/O as suggested in tutorial
//        Kattio io = new Kattio(System.in, System.out);

        // Read input and get Data structures ready
        int testCases = io.getInt();
        
        
        
        // Iterate through all problem instances (max 20)
        for (int i = 0; i < testCases; i++) {

        }

        // Close I/O stream and terminate
        io.close();
    }
}
