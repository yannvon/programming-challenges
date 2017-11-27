package comp321.contest2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 * Additional Problem individually solved (rewritten) after competition 2.
 * 
 * @author Yann Vonlanthen (260808862)
 *
 */
public class Dominoes2 {
    
    public static HashSet<Integer> fallen;
    public static List<Integer>[] successors;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int cases = sc.nextInt();
        
        // Iterate through all cases
        for (int c = 0; c < cases; c ++){
            
            // Read input and set up data structures
            int N = sc.nextInt();
            int M = sc.nextInt();
            int L = sc.nextInt();
            
            successors = new ArrayList[N+1];
            fallen = new HashSet<>();
            
            for(int i = 1; i <= N; i++) {
                successors[i] = new ArrayList<>();
            }
            
            // Add all links in graph
            for(int i = 0; i < M; i++){
                int x = sc.nextInt();
                int y = sc.nextInt();
                successors[x].add(y);
            }
            
            // Perform DPS in graph
            for(int i = 0; i < L; i++){
                int z = sc.nextInt();
                push(z);
            }
            
            // Output number of visited nodes
            System.out.println(fallen.size());
        }
        
        sc.close();
    }
    
    /**
     * Helper function for DPS.
     */
    public static void push(int z){
        if (!fallen.contains(z)) {
            fallen.add(z);
            successors[z].forEach(x -> push(x));
        }
    }
}
