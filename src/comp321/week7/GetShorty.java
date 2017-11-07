package comp321.week7;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import comp321.week7.Kattio;

/**
 * Solution to the GetShorty Problem on Kattis.
 * (https://open.kattis.com/problems/getshorty)
 * 
 * Use of shortest path on a graph. (Dijkstra's Algorithm)
 * 
 * TODO Make use of smart adjacency list for nice speedup! 
 * Done first with normal adjacency list.
 * 
 * @author Yann Vonlanthen (260808862)
 *
 */
public class GetShorty {

    private static DecimalFormat df4 = new DecimalFormat("#0.0000");

    public static void main(String[] args) throws FileNotFoundException {

        // Uncomment this to test on a local file
        // File initialFile = new File("src/comp321/week7/getshorty.in");
        // InputStream targetStream = new FileInputStream(initialFile);
        // Kattio io = new Kattio(targetStream, System.out);

        // Use Kattio for I/O as suggested in tutorial
        Kattio io = new Kattio(System.in, System.out);


        // Read input
        int vertices = io.getInt(); // intersections
        int edges = io.getInt(); // corridors
        int startNode = 0;
        int targetNode = vertices - 1;

        // Iterate through all problem instances (max 20)
        while (edges > 0 || vertices > 0) {

            // Read all vertices input and store in adjacency list (lec 6)

            // --- Method 1: Linked List
            List<Pair<Integer, Double>>[] successors = new List[vertices];
            for (int i = 0; i < vertices; i++) {
                successors[i] = new LinkedList<Pair<Integer, Double>>();
            }
            
            // --- Method 2: Lecture Trick
            // Pair<Integer, Integer>[] E = new Pair[2*edges]; //the pair represents (to, nextID)
            // int[] LE = new int[vertices];
            // for(int i = 0; i < 2*edges; i++)
            // E[i] = new Pair<Integer, Integer>(0,0);
            // for(int i = 0; i < vertices; i++)
            // LE[i] = -1;
            // double[] lengths = new double[2*edges];
            
            
            for (int e = 0; e < edges; e++) {
                int start = io.getInt();
                int end = io.getInt();
                
                // Trick that solves this problem, taking log allows us 
                // to reduce the problem to a shortest path search.
                double l = -1 * Math.log(io.getDouble()); 

                // We have an undirected graph so we add the edge in both directions
                
                // --- Method 1 ---
                successors[start].add(new Pair<Integer, Double>(end, l)); 
                successors[end].add(new Pair<Integer, Double>(start, l));
                
                // --- Method 2 ---
                // E[2*e] = new Pair(end, LE[start]);
                // LE[start] = 2*e;
                // E[2*e + 1] = new Pair(start, LE[end]);
                // LE[end] = 2*e + 1;
                // lengths[2*e] = l;
                // lengths[2*e + 1] = l;
            }

            // --- ALGORITHM 1 --- Dijkstra's shortest path Algorithm no improvements

            // --- create array for the distances ---
            double[] distance = new double[vertices];

            // --- create array fort the best Predecessor of each vertex ---
            // int[] bestPredecessor = new int[vertices];

            // --- fill both arrays: distance[] with infinity and
            // bestPredecessor[] with -1 ---
            for (int i = 0; i < distance.length; ++i) {
                distance[i] = Double.POSITIVE_INFINITY;
                // bestPredecessor[i] = -1;
            }

            // --- Dijkstra Algorithm ---
            distance[startNode] = 0;
            boolean modified = true;
            while (modified) {
                modified = false;
                for (int v = 0; v < vertices; ++v) {
                    if (!Double.isInfinite(distance[v])) {
                        for (Pair<Integer, Double> p : successors[v]) { //method 1: successors[v]
                            if (distance[p._1()] > distance[v] + p._2()) {
                                distance[p._1()] = distance[v] + p._2();
                                // bestPredecessor[p._1()] = v;
                                modified = true;
                            }
                        }
                    }
                }
            }
            
            
            // --- ALGORITHM 2 --- Dijkstra's with queue
            // TODO actually more complicated and speedup is not needed here
            
            
            // get back the factor by which he gets shorter from log distance
            double f = Math.pow(Math.E, -distance[targetNode]);

            io.println(df4.format(f));

            // set new problem instance
            vertices = io.getInt();
            edges = io.getInt();
            targetNode = vertices - 1;
        }

        // Close I/O stream and terminate
        io.close();
    }

    /**
     *  
     * Quick Helper class.
     * Represents a Pair.
     * Should be immutable.
     */
    public static final class Pair<A, B> {
        private final A a;
        private final B b;

        public Pair(A a, B b) {
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
