package comp321.week5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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
public class BankQueue {


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
        HashMap<Integer, ArrayList<Integer>> timeslots = new HashMap<>();
        
        for (int i = 0; i < N; i++) {
            int ci = io.getInt();
            int ti = io.getInt();
            if(!timeslots.containsKey(ti)){
                ArrayList<Integer> newL = new ArrayList<>();
                newL.add(ci);
                timeslots.put(ti, newL);
            }
            else{
                timeslots.get(ti).add(ci);                
            }
        }
        
        // GREEDY ALGORITHM
        int money_scheduled = 0;
        int waitlisted_people = 0;
        
        PriorityQueue<Integer> available_people = new PriorityQueue<>(Collections.reverseOrder());
        //am I allowed to use hashSet?
        
        ArrayList<Integer> empty = new ArrayList<>();
        
        for(int t = T-1; t >= 0 && waitlisted_people < N; t--){
            available_people.addAll(timeslots.getOrDefault(t, empty));
            
            //Decide who is richest
            Integer c = available_people.poll();
            if(c != null) {
                money_scheduled += c;
                waitlisted_people++;
            }
        }
        // Print solution
        io.print(money_scheduled);

        // Close I/O stream and terminate
        io.close();
    }
}
