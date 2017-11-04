package comp321.week6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import comp321.week6.Kattio;
import sun.misc.Queue;

/**
 * Coast Length. COMP 321 - Assignment 6
 * 
 * Uses a tinkered algorithm close to BFS but less nice.
 * Can be improved.
 * 
 * @author Yann Vonlanthen (260808862)
 *
 */
public class CoastLength {

    public static void main(String[] args)
            throws InterruptedException, FileNotFoundException {
        // Uncomment this to test on a local file
        // File initialFile = new File("src/comp321/week6/coast-sample-1.in");
        // InputStream targetStream = new FileInputStream(initialFile);
        // Kattio io = new Kattio(targetStream, System.out);

        // Use Kattio for I/O as suggested in tutorial
        Kattio io = new Kattio(System.in, System.out);

        
        // Read input
        int N = io.getInt() + 2;
        int M = io.getInt() + 2;
        // Note: the idea behind adding 2 rows and columns is to add water all around
        // the map, and thus removing corner cases that are nontrivial to solve.

        int[][] map = new int[N][M];
        for (int i = 1; i < N - 1; i++) {
            String s = io.getWord();
            for (int j = 1; j < M - 1; j++) {
                map[i][j] = s.charAt(j - 1) - '0';
            }
        }

        // --- ALGORITHM --- similar to breadth first search

        Queue<Integer> queue = new Queue<>();
        int count = 0;

        // --- Step 1: go through all borders of the world and enqueue them
        //             note they are all water that was added.
        for (int i = 0; i < M; i++) {
            map[0][i] = 2;
            queue.enqueue(i);
        }
        for (int i = 1; i < N; i++) {
            map[i][0] = 2;
            queue.enqueue(i * M);
        }
        for (int i = 1; i < N; i++) {
            map[i][M - 1] = 2;
            queue.enqueue(i * M + M - 1);
        }
        for (int i = 1; i < M - 1; i++) {
            map[N - 1][i] = 2;
            queue.enqueue((N - 1) * M + i);
        }

        // --- Step 2: traverse map and add new undiscovered nodes to queue
        while (!queue.isEmpty()) {
            int z = queue.dequeue();

            // Visit East
            int e = getEast(z, N, M);
            count += exploreNew(queue, map, M, e);
            // Visit West
            int w = getWest(z, N, M);
            count += exploreNew(queue, map, M, w);
            // Visit South
            int s = getSouth(z, N, M);
            count += exploreNew(queue, map, M, s);
            // Visit North
            int n = getNorth(z, N, M);
            count += exploreNew(queue, map, M, n);
        }

        io.print(count);

        // Close I/O stream and terminate
        io.close();
    }
    
    // 4 equivalent helper methods to get index for new node.
    
    public static int getEast(int z, int N, int M) {
        int x = z % M;
        if (x + 1 < M)
            return z + 1;
        else
            return -1;
    }

    public static int getWest(int z, int N, int M) {
        int x = z % M;
        if (x - 1 >= 0)
            return z - 1;
        else
            return -1;
    }

    public static int getNorth(int z, int N, int M) {
        int y = z / M;
        if (y - 1 >= 0)
            return z - M;
        else
            return -1;
    }

    public static int getSouth(int z, int N, int M) {
        int y = z / M;
        if (y + 1 < N)
            return z + M;
        else
            return -1;
    }
    
    /**
     * Helper method that explores a new node, i.e. checks if there is a coast,
     * and enqueues it if it is undiscovered.
     * @return 1 if coast detected 0 otherwise
     */
    public static int exploreNew(Queue<Integer> queue, int[][] map, int M, int newNode) {
        if (newNode > 0) {
            int x = newNode / M;
            int y = newNode % M;
            int m = map[x][y];
            if (m == 1) {
                return 1;
            } else if (m == 0) {
                queue.enqueue(newNode);
                map[x][y] = 2;
            }
        }
        return 0;
    }
}
