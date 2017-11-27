package comp321.contest2;

import java.util.*;

public class dominoes_contest {
    private static HashMap<Integer, ArrayList<Integer>> map;
    private static HashSet<Integer> fallen;

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int numCases = reader.nextInt();
        
        for (int i = 0; i < numCases; i++) {
            map = new HashMap<>();
            fallen = new HashSet<>();
            
            int N = reader.nextInt();
            int M = reader.nextInt();
            int L = reader.nextInt();
            
            for (int j = 1; j <= N; j++) {
                ArrayList<Integer> a = new ArrayList<>();
                map.put(j, a);
            }
            for (int j = 0; j < M; j++) {
                int x = reader.nextInt();
                int y = reader.nextInt();
                map.get(x).add(y);
            }
            for (int j = 0; j < L; j++) {
                int z = reader.nextInt();
                push(z); // pushing l
            }
            
            System.out.print(fallen.size());
        }
    }

    private static void push(int z) {
        if (!fallen.contains(z)) {
            fallen.add(z);
            map.get(z).forEach(y -> push(y));
        }
    }

}
