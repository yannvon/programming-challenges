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

//         File initialFile = new File("src/comp321/contest1/c.in");
//         InputStream targetStream = new FileInputStream(initialFile);
//         Kattio io = new Kattio(targetStream, System.out);

        Kattio io = new Kattio(System.in, System.out);

         
         Comparator<Pair> comparator = comparingDouble(pair -> (double) -1 * pair._1() / pair._2());

        while(true) {
            int N = io.getInt();
            int B = io.getInt();
            
            if (N == -1 && B == -1)
                break;
            
            PriorityQueue<Pair> maxPeoplePerCityHeap = new PriorityQueue<>(N,
                    comparator);
            
            // read input and directly assign one ballot to each city
            for(int j = 0; j < N; j++) {
                maxPeoplePerCityHeap.add(new Pair(io.getInt(), 1));
            }
            B -= N; // remove the assigned ballots
            if (B < 0)
                System.err.println("whoops something went wrong");
            
            // take max as long as there are still ballots and divide by 2 (take ceil)
            Pair currentMax;
            while (B != 0) {
                currentMax = maxPeoplePerCityHeap.poll();
                currentMax.increase_2_by1();
                maxPeoplePerCityHeap.add(currentMax);
                B--;
            }
            int max = (int) Math.ceil( (double) maxPeoplePerCityHeap.peek()._1() / maxPeoplePerCityHeap.peek()._2());
            io.println(max);
        }

        io.close();
    }
    
    /**
     *  
     * Quick Helper class.
     * Represents a Pair.
     * Should be immutable but takes too much time if it was.
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
