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
 * @author Yann Vonlanthen (260808862)
 *
 */
public class CoastLength {

    public static void main(String[] args)
            throws InterruptedException, FileNotFoundException {
        // Uncomment this to test on a local file
//        File initialFile = new File("src/comp321/week6/coast-sample-1.in");
//        InputStream targetStream = new FileInputStream(initialFile);
//        Kattio io = new Kattio(targetStream, System.out);

        // Use Kattio for I/O as suggested in tutorial
         Kattio io = new Kattio(System.in, System.out);

        // Read input
        int N = io.getInt() + 2;
        int M = io.getInt() + 2;
        int[][] map = new int[N][M];

        for (int i = 1; i < N-1; i++) {
            String s = io.getWord();
            for (int j = 1; j < M-1; j++) {
                map[i][j] = s.charAt(j-1) - '0';
            }
        }
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(map[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println();

        // ALGORITHM similar to depth first search

        Queue<Integer> queue = new Queue<>();
        int count = 0;

        // --- Step 1: go through all borders of the world
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

        // --- Real start of Algo
        while (!queue.isEmpty()) {
            int z = queue.dequeue();

            // Visit East
            int e = getEast(z, N, M);
            if (e > 0) {
                int x = e / M;
                int y = e % M;
                int m = map[x][y];
                if (m == 1) {
                    count++;
                } else if (m == 0) { // dont enque if q == 2 or 1 !
                    queue.enqueue(e);
                    map[x][y] = 2;
                }
            }

            // Visit West
            int w = getWest(z, N, M);
            if (w > 0) {
                int x = w / M;
                int y = w % M;
                int m = map[x][y];
                if (m == 1) {
                    count++;
                } else if (m == 0) {
                    queue.enqueue(w);
                    map[x][y] = 2;
                }
            }

            // Visit South
            int s = getSouth(z, N, M);
            if (s > 0) {
                int x = s / M;
                int y = s % M;
                int m = map[x][y];
                if (m == 1) {
                    count++;
                } else if (m == 0) {
                    queue.enqueue(s);
                    map[x][y] = 2;
                }
            }

            // Visit North
            int n = getNorth(z, N, M);
            if (n > 0) {
                int x = n / M;
                int y = n % M;
                int m = map[x][y];
                if (m == 1) {
                    count++;
                } else if (m == 0) {
                    queue.enqueue(n);
                    map[x][y] = 2;
                }
            }
        }

        io.print(count);

        // Close I/O stream and terminate
        io.close();

    }

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
}
