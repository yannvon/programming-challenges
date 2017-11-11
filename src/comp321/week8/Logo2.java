package comp321.week8;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import comp321.week8.Kattio;

/**
 * Solution to the Logo2 Problem on Kattis.
 * (https://open.kattis.com/problems/logo2)
 * 
 * Topic of Geometry.
 * 
 * @author Yann Vonlanthen (260808862)
 *
 */
public class Logo2 {
    
    static final double EPS = 0.001; //arbitrary value, had it higher but didnt work.
    static final double PI = 2 * Math.acos(0);  //Trick seen in class, I didn't see a difference though

    public static void main(String[] args) throws FileNotFoundException {

        // Uncomment this to test on a local file
//         File initialFile = new File("src/comp321/week8/E.in");
//         InputStream targetStream = new FileInputStream(initialFile);
//         Kattio io = new Kattio(targetStream, System.out);

        // Use Kattio for I/O as suggested in tutorial
        Kattio io = new Kattio(System.in, System.out);
        
        // Read input and get Data structures ready
        int testCases = io.getInt();
        int[] values = new int[1000];    // I will denote '?' by MINVALUE
        char[] commands = new char[1000];
        
        // Iterate through all problem instances (max 20)
        for(int i = 0; i < testCases; i++) {
            int nCmd = io.getInt();
            
            // --- Step 1 : read all input and find missing command
            int missing = -1;
            for (int j = 0; j < nCmd; j++) {
                commands[j] = io.getWord().charAt(0);
                String v = io.getWord();
                if (v.equals("?")){
                    values[j] = Integer.MIN_VALUE;
                    missing = j;
                } else {
                    values[j] = Integer.parseInt(v);                    
                }
            }
            
            // --- Step 2 : check whether an angle or dist command is missing
            char c = commands[missing];
            if (c == 'r' || c == 'l'){
                // an angle command is missing
                
                // --- SOLUTION CASE 1: try all 360 angles and see which leads back to start
                boolean found = false;
                for (int a = 0; a < 360; a++) {
                    Position end = simulatePass(a, nCmd, commands, values);
                    if (Math.sqrt(end.x*end.x + end.y*end.y) < EPS){
                        // we found the angle
                        //System.err.println(Math.sqrt(end.x*end.x + end.y*end.y) - EPS);
                        io.println(a);
                        found = true;
                        break;
                    }
                }                
                if (!found)
                    System.err.println("no angle found :(");                
                
            } else if (c == 'f' || c == 'b'){
                // a distance command is missing
                
                // --- SOLUTION CASE 2 : move 0 forward at ? and return missing dist at end
                Position end = simulatePass(0, nCmd, commands, values);
                double solution = Math.sqrt(end.x*end.x + end.y*end.y);
                //System.err.println(Math.round(solution) - solution);
                io.println(Math.round(solution));
                
            } else {
                System.err.println("not a valid command :(");
            }
        }

        // Close I/O stream and terminate
        io.close();
    }
    
        
    /**
     * Simulates the turtle commands given using replacement value instead of '?'.
     * 
     * @return the Position where the turtle ends up
     */
    public static Position simulatePass(int replacementValue, int steps, char[] cmds, int[] values) {
        double x = 0;
        double y = 0;
        double angle = 0;
        
        // Go through all instructions and execute them
        // make sure to use replacement value instead of '?'.
        for(int i = 0; i < steps; i++){
            char cmd = cmds[i];
            int value = values[i];
            if (value < 0)
                value = replacementValue;
            
            double sgn = 1;
            switch (cmd) {
                case 'b' :
                    sgn = -1;
                case 'f' : 
                    // I believe use of sin/cos and direction is arbitrary.
                    x += sgn*value*Math.sin(angle / 180 * PI);  
                    y += sgn*value*Math.cos(angle / 180 * PI);
                    break;
                case 'l' :
                    sgn = -1;
                case 'r' :
                    // side of turning is arbitrary! l is simply opposite of r.
                    angle += sgn*value;
                    break;
            }
        }
        return new Position(x, y, angle);
    }
    
    
    /**
     * Small helper class to represent a position.
     */
    public static final class Position {
        public final double x;
        public final double y;
        public final double angle;
        
        public Position(double x, double y, double angle) {
            this.x = x;
            this.y = y;
            this.angle = angle;
        }
    }

}
