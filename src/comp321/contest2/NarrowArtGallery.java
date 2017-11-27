package comp321.contest2;

import java.util.Scanner;

/**
 * Problem 3 Contest 2. Additional Problem 
 * 
 * @author Yann Vonlanthen (260808862)
 *
 */
public class NarrowArtGallery {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int K = sc.nextInt();
        
        int[][] val = new int[N][2];
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < 2; j++){
                val[i][j] = sc.nextInt();
            }
        }
        
        int[][][] A = new int[K+1][N+1][3];
        
        
        for(int k = 1; k < K; k++){
            for(int i = 1; i < N; i++){
                
                if(k > i) {
                    A[k][i][2] = Integer.MIN_VALUE;
                }
                // not restrictions
            }
        }
        
        System.out.println(A[N][K][2]);
        
    }

}
