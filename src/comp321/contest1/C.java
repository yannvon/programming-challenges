package comp321.contest1;

import java.io.FileNotFoundException;

import comp321.contest1.Kattio;

/**
 * Problem C
 * 
 * @author Yann Vonlanthen (260808862)
 *
 */
public class C {

    public static void main(String[] args) throws FileNotFoundException {

        // File initialFile = new File("src/comp321/week8/E.in");
        // InputStream targetStream = new FileInputStream(initialFile);
        // Kattio io = new Kattio(targetStream, System.out);

        Kattio io = new Kattio(System.in, System.out);

        int testCases = io.getInt();
        
        for (int i = 0; i < testCases; i++) {
            int N = io.getInt();
            int B = io.getInt();
            
            if (N == -1 && B == -1)
                return;
            
            int[] cities = new int[N];
            
            for(int j = 0; j < N; j++) {
                cities[j] = io.getInt();
            }
            
            
        }

        
        
        io.close();
    }
}
