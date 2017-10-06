package comp321.week3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;


/**
 * String Matching.
 * Implementation of Knuth-Morris-Pratt Algorithm.
 * 
 * (https://open.kattis.com/problems/stringmatching)
 * 
 * @author Yann Vonlanthen (260808862)
 *
 */
public class StringMatchingTrivial {
    
    
    public static void main(String[] args) throws IOException {
        
        Scanner io = new Scanner(System.in);
        //BufferedReader io = new BufferedReader(new FileReader("src/comp321/week3/stringmatching.txt"));


        while (io.hasNext()) {
            char[] pattern = io.nextLine().toCharArray();
            char[] text = io.nextLine().toCharArray();

            // --- STEP 1: Traverse text without use of auxiliary array ---
            int n = text.length;
            int p = pattern.length;
            boolean found = false;
            for (int i = 0; i < n - p + 1; i++) {
                int j = 0;
                while (j < p && text[i + j] == pattern[j] ) {
                    if (j == p - 1){
                        if (found){
                            System.out.print(" " + i);
                        }else {
                            System.out.print(i);
                            found = true;
                        }
                    }
                    j++;
                }
            }
            System.out.println();
        }
        
     //close scanner
     io.close();   
    }

}
