package comp321.contest1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Problem B
 * 
 * @author Yann Vonlanthen (260808862)
 *
 */
public class B {

    public static void main(String[] args) throws FileNotFoundException {

//         File initialFile = new File("src/comp321/contest1/b.in");
//         InputStream targetStream = new FileInputStream(initialFile);
//         Kattio io = new Kattio(targetStream, System.out);
         
        Scanner io = new Scanner(System.in);
         
         HashMap<Character, String> map = new HashMap<>();
         map.put('a', "2");
         map.put('b', "22");
         map.put('c', "222");
         map.put('d', "3");
         map.put('e', "33");
         map.put('f', "333");
         map.put('g', "4");
         map.put('h', "44");
         map.put('i', "444");
         map.put('j', "5");
         map.put('k', "55");
         map.put('l', "555");
         map.put('m', "6");
         map.put('n', "66");
         map.put('o', "666");
         map.put('p', "7");
         map.put('q', "77");
         map.put('r', "777");
         map.put('s', "7777");
         map.put('t', "8");
         map.put('u', "88");
         map.put('v', "888");
         map.put('w', "9");
         map.put('x', "99");
         map.put('y', "999");
         map.put('z', "9999");
         map.put(' ', "0");
         
         
        int testCases = io.nextInt();
        io.nextLine();
        System.err.println(testCases);
        
        for (int i = 0; i < testCases; i++) {
            String start = String.format("Case #%d: ", i+1);
            System.out.print(start);
            
            StringBuilder finalString = new StringBuilder();
            String last = "X";
            
            String s = io.nextLine();
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                
                String charInNumbers = map.get(c);
                String oneChar = charInNumbers.substring(0, 1);
                //TODO if zero not a problem
                
                if(last.equals(oneChar))
                    // add space
                    finalString.append(" ");
                
                finalString.append(charInNumbers);
                last = oneChar;
            }
            
            System.out.println(finalString.toString());
        }

        // Close I/O stream and terminate
        io.close();
    }
}
