package comp321.contest1;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.NClob;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Problem H
 * 
 * @author Yann Vonlanthen (260808862)
 *
 */
public class H {
    public static int N = 0;
    public static int M = 0;
    public static char[][] input;
    public static boolean[][] visited;
    

    public static void main(String[] args) throws FileNotFoundException {

//        File initialFile = new File("src/comp321/contest1/c.in");
//        InputStream targetStream = new FileInputStream(initialFile);

        Scanner io = new Scanner(System.in);
        int caseN = 1;
        
        while (io.hasNext()) {
            // print case number
            String str = String.format("Case %d: ", caseN);
            System.out.print(str);
            
            // read input
            int n = io.nextInt();
            int m = io.nextInt();
            io.nextLine();
            N = n;
            M = m;
            
            input = new char[n][m];
            visited = new boolean[n][m];
            
            int nStars = 0;
            
            for (int i = 0; i < n; i++){
                String s = io.nextLine();
                char[] line = s.toCharArray();
                for (int j = 0; j < m; j++) {
                    char c = line[j];
                    input[i][j] = line[j];
                    visited[i][j] = false;
                }
            }
            
            for (int i = 0; i < n; i++){
                for (int j = 0; j < m; j++) {
                    if(visited[i][j] == false && input[i][j] != '#') {
                        //found a new star
                        nStars++;
                        bfsFrom(i,j);
                    }
                }
            }
            caseN++;
            System.out.println(nStars);
        }

        io.close();
    }
    public static void bfsFrom(int i, int j){
        if(i < 0 || j < 0 || i >= N || j >= M)
            return;
        
        if(input[i][j] == '#')
            return;
        if(visited[i][j] == true)
            return;
        
        visited[i][j] = true;
        
        bfsFrom(i-1,j);
        bfsFrom(i+1,j);
        bfsFrom(i,j+1);
        bfsFrom(i,j-1);
    }    
}
