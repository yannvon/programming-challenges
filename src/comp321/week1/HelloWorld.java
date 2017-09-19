package comp321.week1;

import comp321.week1.Kattio;


/**
 * Describe class here.
 * 
 * @author Yann Vonlanthen (260808862)
 *
 */
public class HelloWorld {

    public static void main(String[] args) {
        
        /*
         * File initialFile = new File(
         * "src/comp321/week1/guessthedatastructure_sample.txt"); InputStream
         * targetStream = new FileInputStream(initialFile); Kattio io = new
         * Kattio(targetStream, System.out);
         */

        
        Kattio k = new Kattio(System.in, System.out);
        k.println("Hello World!");
        k.close();
    }

}
