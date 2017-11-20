package comp321.contest1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Time;

import comp321.contest1.Kattio;

/**
 * Problem A
 * 
 * @author Yann Vonlanthen (260808862)
 *
 */
public class A {

    public static void main(String[] args) throws FileNotFoundException {

//        File initialFile = new File("src/comp321/contest1/a2.in");
//        InputStream targetStream = new FileInputStream(initialFile);
//        Kattio io = new Kattio(targetStream, System.out);

         Kattio io = new Kattio(System.in, System.out);

        String currTime = io.getWord();
        int cH = Integer.parseInt(currTime.substring(0,2));
        int cM = Integer.parseInt(currTime.substring(3,5));
        int cS = Integer.parseInt(currTime.substring(6,8));
        System.err.println(cH + " " + cM + " " + cS);
        int curSeconds = cH*60*60 + cM * 60 + cS;
        
        String explosionTime = io.getWord();
        int eH = Integer.parseInt(explosionTime.substring(0,2));
        int eM = Integer.parseInt(explosionTime.substring(3,5));
        int eS = Integer.parseInt(explosionTime.substring(6,8));
        int expSeconds = eH*60*60 + eM * 60 + eS;
        
        //corner case may not work :O
        if(curSeconds == expSeconds){
            System.out.println("24:00:00");
            io.close();
            return;
        }
        
        // computation done here
        if (expSeconds < curSeconds)
            expSeconds += 24*3600;
        int timeDifference = expSeconds - curSeconds;
        
        
        int bS = timeDifference % 60;
        timeDifference -= bS;
        int bM = (timeDifference % 3600)/60;
        timeDifference -= bM*60;
        int bH = timeDifference / 3600;
                
        String bombTime = String.format("%02d:%02d:%02d", bH % 24, bM % 60, bS % 60);
        
        io.println(bombTime);

        // Close I/O stream and terminate
        io.close();
    }
}
