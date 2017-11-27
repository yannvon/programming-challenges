package comp321.contest2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
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
        
        for (int c = 0; c < cases; c ++){
            int N = sc.nextInt();
            int M = sc.nextInt();
            int L = sc.nextInt();
            
            successors = new ArrayList[N+1];
            fallen = new HashSet<>();
            
            for(int i = 1; i <= N; i++) {
                successors[i] = new ArrayList<>();
            }
            
            for(int i = 0; i < M; i++){
                int x = sc.nextInt();
                int y = sc.nextInt();
                successors[x].add(y);
            }
            
            for(int i = 0; i < L; i++){
                int z = sc.nextInt();
                push(z);
            }
            
            System.out.println(fallen.size());
        }
    }
    
    public static void push(int z){
        if (!fallen.contains(z)) {
            fallen.add(z);
            successors[z].forEach(x -> push(x));
        }
    }
}
