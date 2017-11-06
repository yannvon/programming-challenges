package comp321.week7;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import comp321.week7.Kattio;
import sun.misc.Queue;

/**
 * Solution to the GetShorty Problem on Kattis.
 * (https://open.kattis.com/problems/getshorty)
 * 
 * Use of shortest path on a graph. (Dijkstra's Algorithm)
 * 
 * Make use of smart adjacency list for nice speedup!
 * TEST with normal adjacency list ! TODO
 * 
 * @author Yann Vonlanthen (260808862)
 *
 */
public class GetShorty {

    private static DecimalFormat df4 = new DecimalFormat("#0.0000");


    public static void main(String[] args) throws FileNotFoundException {

        // Uncomment this to test on a local file
//        File initialFile = new File("src/comp321/week7/getshorty.in");
//        InputStream targetStream = new FileInputStream(initialFile);
//        Kattio io = new Kattio(targetStream, System.out);

        // Use Kattio for I/O as suggested in tutorial
         Kattio io = new Kattio(System.in, System.out);

        // Iterate through all problem instances (max 20)

        // Read input
        int vertices = io.getInt(); // intersections
        int edges = io.getInt();    // corridors
        int startNode = 0;
        int targetNode = vertices - 1;

        while (edges > 0 || vertices > 0) {

            // Read all vertices input and store in adjacency list (lec 6)
            //HashMap<Integer, Integer> length = new HashMap<>(); //length for given edge id
            
            // --- Method 1: Linked list
            
            List<Pair>[] successors = new List[vertices];
            
            for (int i = 0; i < vertices; i++) {
                successors[i] = new LinkedList<Pair>();
            }
            
            for(int e = 0; e < edges; e++){
                int start = io.getInt();
                int end = io.getInt();
                double l = -1 * Math.log(io.getDouble());  //TODO Trick that solves this problem
                
                successors[start].add(new Pair(end, l));    //don't add edge 
                successors[end].add(new Pair(start, l)); //undirected graph!
            }

            // --- ALGORITHM 1 --- Dijkstra's shortest path Algorithm no improvements
            
            //--- create array for the distances ---
            double[] distance = new double[vertices];
            
            //--- create array fort the best Predecessor of each vertex ---
            //int[] bestPredecessor = new int[vertices];
            
            //--- fill both arrays: distance[] with infinity and bestPredecessor[] with -1 ---
            for (int i = 0; i < distance.length; ++i){
                distance[i] = Double.POSITIVE_INFINITY;
                // bestPredecessor[i] = -1; 
            }
            
            //--- Dijkstra Algorithm ---
            distance[startNode] = 0;
            boolean modified = true;
            while (modified){
                modified = false;
                for (int v = 0;  v < successors.length; ++v){                       
                    if (!Double.isInfinite(distance[v])){                //suggested optimisation
                        for (Pair<Integer, Double> p : successors[v]){
                            if (distance[p._1()] > distance[v] + p._2()){ 
                                distance[p._1()] = distance[v] + p._2();
                                // bestPredecessor[p._1()] = v;
                                modified = true;
                            }
                        }
                    }
                }
            }
            
            double dist = Math.pow(Math.E, -distance[targetNode]);
            
            
            io.println(df4.format(dist));
            
            
            // set new problem
            vertices = io.getInt();
            edges = io.getInt();
            targetNode = vertices - 1;
        }
        
        // Close I/O stream and terminate
        io.close();

    }
    
    public static class Pair<A, B> {
        private A a;
        private B b;
        
        public Pair(A a, B b){
            this.a = a;
            this.b = b;
        }
        public A _1() {
            return a;
        }
        public B _2() {
            return b;
        }
    }

}
