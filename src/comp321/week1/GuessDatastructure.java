package comp321.week1;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;

import comp321.week1.Kattio;
import sun.misc.Queue;

/**
 * I Can Guess the Data Structure
 * (https://open.kattis.com/problems/guessthedatastructure)
 * 
 * @author Yann Vonlanthen (260808862)
 *
 */
public class GuessDatastructure {

    public static void main(String[] args)
            throws InterruptedException, FileNotFoundException {

        // Use Kattio for I/O as suggested in tutorial
        Kattio io = new Kattio(System.in, System.out);

        while (io.hasMoreTokens()) {
            int n = io.getInt();

            // Create a fresh instance of every Data structure
            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new Queue<>();
            PriorityQueue<Integer> priority = new PriorityQueue<>(1000,
                    Collections.reverseOrder());

            boolean isStack = true;
            boolean isQueue = true;
            boolean isPriority = true;
            int count = 0;

            for (int i = 0; i < n; i++) {
                int op = io.getInt();
                int elem = io.getInt();

                if (op == 1) {
                    // --- OPERATION 1: Add an element to all data structures
                    count++;
                    stack.push(elem);
                    queue.enqueue(elem);
                    priority.add(elem);
                } else if (op == 2) {
                    // --- OPERATION 2: retrieve element from all data
                    // structures
                    if (count == 0) {
                        isPriority = false;
                        isQueue = false;
                        isStack = false;
                    } else {
                        // --- Check for stack error if stack is still a
                        // possibility
                        if (isStack) {
                            int s = stack.pop();
                            if (s != elem)
                                isStack = false;
                        }

                        // --- Check for priority queue error if it is still a
                        // possibility
                        if (isPriority) {
                            int p = priority.remove();
                            if (p != elem)
                                isPriority = false;
                        }
                        // --- Check for queue error if queue is still a
                        // possibility
                        if (isQueue) {
                            int q = queue.dequeue();
                            if (q != elem)
                                isQueue = false;
                        }
                    }
                    count--;
                }
            }
            
            // after every operation is executed check which data structure it was
            if (isStack && !isQueue && !isPriority)
                io.println("stack");
            else if (!isStack && isQueue && !isPriority)
                io.println("queue");
            else if (!isStack && !isQueue && isPriority)
                io.println("priority queue");
            else if (!isStack && !isQueue && !isPriority)
                io.println("impossible");
            else
                io.println("not sure");
        }
        //Close I/O stream and terminate
        io.close();
    }
}
