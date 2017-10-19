package comp321.week5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

import comp321.week5.Kattio;

/**
 * Bank Queue. Assignment 5. (https://open.kattis.com/problems/bank)
 * 
 * Greedy implementation.
 * 
 * @author Yann Vonlanthen (260808862)
 *
 */
public class BankQueueAlex {


    public static void main(String[] args) throws FileNotFoundException {
        
        // Uncomment this to test on a local file
//         File initialFile = new File("src/comp321/week5/bank-01.in");
//         InputStream targetStream = new FileInputStream(initialFile);
//         Kattio io = new Kattio(targetStream, System.out);
        
        // Use Kattio for I/O as suggested in tutorial
        Kattio io = new Kattio(System.in, System.out);
        
        int N = io.getInt();
        int T = io.getInt();
   
        // Create HashMap containing all people
        PriorityQueue<Pair> money = new PriorityQueue<>( (p1, p2) -> p2.getValue() - p1.getValue());
        HashMap<Integer, Integer> people = new HashMap<>();
        
        for (int i = 0; i < N; i++) {
            int ci = io.getInt();
            int ti = io.getInt();
            money.add(new Pair(i, ci));
            people.put(i, ti);
        }
        
        // Array with time slots occupancy
        byte[] slots = new byte[T];
        for (int i = 0; i < T; i++){
            slots[i] = 0;
        }
        
        
        // GREEDY ALGORITHM
        int waitlist_size = 0;
        long money_scheduled = 0;
        
        while (!money.isEmpty() && waitlist_size < T+1) {   //actually T

            // --- Step 1: take richest guy in queue
            Pair p = money.poll();
            
            // --- Step 2: if there is a space left for him put him on wait list at latest possible time instance
            int leaving_time = people.get(p.getKey());
            boolean freeSlotFound = false;
            
            for(int t = leaving_time; t >= 0 && !freeSlotFound; t--) {
                if (slots[t] == 0) {
                    slots[t] = 1;
                    waitlist_size++;
                    money_scheduled += (int) p.getValue();
                    freeSlotFound = true;
                }
            }
        }
        
        // Print solution
        io.print(money_scheduled);

        // Close I/O stream and terminate
        io.close();
    }
    public static class Pair {
        private int x;
        private int y;
        
        public Pair(int a, int b){
            this.x = a;
            this.y = b;
        }
        
        public int getValue(){
            return y;
        }
        public int getKey(){
            return x;
        }
    }
}

