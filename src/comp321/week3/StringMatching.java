package comp321.week3;

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
public class StringMatching {
    
    public static void main(String[] args) {
        
        // Note: For this assignment I will use the standard I/O and not kattio.
        Scanner io = new Scanner(System.in);
        
        // For local testing purposes uncomment this line:
        //BufferedReader io = new BufferedReader(new FileReader("src/comp321/week3/stringmatching.txt"));

        while (io.hasNext()) {
            /*
             * Disclaimer: This implementation of the KMP Algorithm was taken from 
             * Wikipedia. (https://en.wikipedia.org/wiki/Knuth-Morris-Pratt_algorithm)
             */
            
            // --- STEP 0: read Word (sometimes called string) and text String (also called text)
            char[] W = io.nextLine().toCharArray();
            char[] S = io.nextLine().toCharArray();
            
            
            // --- STEP 1: Construct auxiliary array called T
            int[] T = new int[W.length + 1];

            int pos = 1;
            int cnd = 0;
            
            T[0] = -1;
            while(pos < W.length){
                if (W[pos] == W[cnd]){
                    T[pos] = T[cnd];
                    pos += 1;
                    cnd += 1;
                } else {
                    T[pos] = cnd;
                    cnd = T[pos];
                    
                    while(cnd >= 0 && W[pos] != W[cnd]){
                        cnd = T[cnd];
                    }
                    pos += 1;
                    cnd += 1;
                }
            }
            
            T[pos] = cnd;
            
            // --- STEP 2: Traverse text with use of auxiliary array ---
            boolean found = false;
            int m = 0;
            int i = 0;
            
            while(m+i < S.length){
                if (W[i] == S[m + i]){
                    i += 1;
                    if (i == W.length){
                        // --- an occurrence of the string was found ---
                        
                        // Problem specific case handling
                        if (found){ 
                            System.out.print(" " + (m));
                        }else {
                            System.out.print(m);
                            found = true;
                        }
                        
                        m = m + i - T[i];
                        i = T[i];
                    }
                } else {
                    if (T[i] > -1){
                        m = m + i - T[i];
                        i = T[i];
                    }else {
                        m = m + i + 1;
                        i = 0;
                    }
                }
            }
            
            System.out.println("");
            
            // Alternative Algorithm suggested on the lecture slides.
            
            /*
            pi[0] = -1;
            int k = -1;
            for (int i = 1; i <= m; i++){
                while(k >= 0 && pattern[k+1] != pattern[i]){
                    k = pi[k];
                }
                pi[i] = ++k;
            }
            
            boolean found = false;
            
            int kk = 0;
            for (int i = 0; i < n; i++){
                while (kk < m && pattern[kk] != text[i]){
                    kk = pi[kk];
                }
                kk++;
                if(kk == m) {
                    // pattern matches text[i-m+1,  ,i]
                    kk = pi[kk-1];
                    if (found){ 
                        System.out.print(" " + (i));
                    }else {
                        System.out.print(i);
                        found = true;
                    }
                }
            }
            System.out.println();
            */
     }
     
     // close scanner
     io.close();   
    }
}
