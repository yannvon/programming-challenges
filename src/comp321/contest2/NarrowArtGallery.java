package comp321.contest2;

import java.util.Scanner;

/**
 * Problem 3 Contest 2. Additional Problem.
 * 
 * @author Yann Vonlanthen (260808862)
 *
 */
public class NarrowArtGallery {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int K = sc.nextInt();
        
        int[][] val = new int[N+1][2];
        val[0][0] = 0;
        val[0][1] = 0;
        for(int i = 1; i <= N; i++){
            for(int j = 0; j < 2; j++){
                val[i][j] = sc.nextInt();
            }
        }
        
        int[][][] A = new int[K+1][N+1][3];
        
        // special case 1: i = 0 (run out of rooms to block)
        for(int k = 1; k <= K; k++){
            A[k][0][0] = Integer.MIN_VALUE;
            A[k][0][1] = Integer.MIN_VALUE;
            A[k][0][2] = Integer.MIN_VALUE;
        }

        A[0][0][0] = 0;
        A[0][0][1] = 0;
        A[0][0][2] = 0;
        
        // special case 2: k = 0
        for(int i = 1; i <= N; i++){
            A[0][i][0] = val[i][0] + val[i][1] + A[0][i-1][2];
            A[0][i][1] = val[i][0] + val[i][1] + A[0][i-1][2];
            A[0][i][2] = val[i][0] + val[i][1] + A[0][i-1][2];
        }

        // BOTTOM-UP DP ALGORITHM (fill A)
        for(int k = 1; k <= K; k++){
            for(int i = 1; i <= N; i++){
                //instead of third inner for loop fill out all 3 possibilities
                
                // case restricted to taking left (0)
                A[k][i][0] = Math.max(val[i][0] + A[k-1][i-1][0],               //closing right room
                                        val[i][0] + val[i][1] + A[k][i-1][2]);  //closing no room
                
                // case restricted to taking right (1)
                A[k][i][1] = Math.max(val[i][1] + A[k-1][i-1][1],               //closing left room
                                        val[i][0] + val[i][1] + A[k][i-1][2]);  //closing no room
                
                // case no restriction (2)
                A[k][i][2] = Math.max(val[i][1] + A[k-1][i-1][1], 
                                Math.max(val[i][0] + A[k-1][i-1][0], 
                                        val[i][0] + val[i][1] + A[k][i-1][2]));
                
            }
        }
        
        System.out.println(A[K][N][2]);
        sc.close();
    }
}
