package comp321.contest1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.PriorityQueue;
import static java.util.Comparator.comparingDouble;

/**
 * Problem C
 * 
 * @author Yann Vonlanthen (260808862)
 *
 */
public class C {

    public static void main(String[] args) throws FileNotFoundException {

        // File initialFile = new File("src/comp321/contest1/c.in");
        // InputStream targetStream = new FileInputStream(initialFile);
        // Kattio io = new Kattio(targetStream, System.out);

        Kattio io = new Kattio(System.in, System.out);
        
        Comparator<Pair> comparator = comparingDouble(
                pair -> (double) -1 * pair._1() / pair._2());

        // Loop through all problem instances until -1 -1 is input
        while (true) {
            int N = io.getInt();
            int B = io.getInt();

            if (N == -1 && B == -1)
                break;
            
            // Use a Heap to keep track of city with most people per box
            PriorityQueue<Pair> maxPeoplePerCityHeap = new PriorityQueue<>(N,
                    comparator);

            // Read input and directly assign one ballot to each city
            for (int j = 0; j < N; j++) {
                maxPeoplePerCityHeap.add(new Pair(io.getInt(), 1));
            }
            B -= N; // remove the assigned ballots
            
            // Take max and assign a new box to that city, re-add to heap
            Pair currentMax;
            while (B != 0) {
                currentMax = maxPeoplePerCityHeap.poll();
                currentMax.increase_2_by1();
                maxPeoplePerCityHeap.add(currentMax);
                B--;
            }
            
            // Output the max when no boxes are left.
            int max = (int) Math.ceil((double) maxPeoplePerCityHeap.peek()._1()
                    / maxPeoplePerCityHeap.peek()._2());
            io.println(max);
        }

        io.close();
    }

    /**
     * 
     * Quick Helper class. Represents a Pair. Should be immutable but takes too
     * much time if it was.
     */
    public static final class Pair {
        private int x;
        private int y;

        public Pair(int a, int b) {
            this.x = a;
            this.y = b;
        }

        public int _1() {
            return x;
        }

        public int _2() {
            return y;
        }

        public void increase_2_by1() {
            this.y += 1;
        }
    }
}
