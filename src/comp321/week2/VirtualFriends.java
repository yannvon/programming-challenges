package comp321.week2;

import java.io.FileNotFoundException;
import java.util.HashMap;

import comp321.week4.Kattio;


/**
 * Virtual Friends. 
 * (https://open.kattis.com/problems/virtualfriends)
 * 
 * @author Yann Vonlanthen (260808862)
 *
 */
public class VirtualFriends {

    /**
     * UNION-FIND data structure with path compression.
     * Set is a helper class that represents a single element of a Set.
     */
    private static class Set {

        private Set p = null;
        private int rank = 0;
        private int members = 1;

        public Set() {
            p = this;
        }

        
        /**
         * Finds the representative.
         * 
         * @return the set representative
         */
        public Set find() {
            if (this != this.p)
                this.p = this.p.find();
            return this.p;
        }

        /**
         * Links two sets together.
         * 
         * @param that an element of another set
         * @return the parent of the new set
         */
        public Set link(Set that) {
            if (this == that){
                return that;                
            } else if (this.rank > that.rank) {
                that.p = this;
                this.members += that.members;
                return this;
            } else {
                this.p = that;
                if (this.rank == that.rank) {
                    that.rank += 1;
                }
                that.members += this.members;
                return that;
            }
        }

        /**
         * Takes the union of two sets.
         * 
         * @param that any element of the other set
         * @return the representative of the new set
         */
        public int union(Set that) {
            return (this.find()).link(that.find()).members;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        
        // Uncomment this to test on a local file
        /*
        File initialFile = new File(
          "src/comp321/week2/virtualfriends.txt"); 
        InputStream targetStream = new FileInputStream(initialFile); 
        Kattio io =  new Kattio(targetStream, System.out);
        */

        // Use Kattio for I/O as suggested in tutorial
        Kattio io = new Kattio(System.in, System.out);

        // Iterate over every problem instance
        int n = io.getInt();
        for (int i = 0; i < n; i++) {

            // Create HashMap that contains Name: Object pair
            HashMap<String, Set> map = new HashMap<>();

            // Iterate over all relationships
            int f = io.getInt();
            for (int j = 0; j < f; j++) {

                String name1 = io.getWord();
                String name2 = io.getWord();

                // Check if name already part of network
                if (!map.containsKey(name1)) {
                    map.put(name1, new Set());
                }
                if (!map.containsKey(name2)) {
                    map.put(name2, new Set());
                }
                
                //Take union of both friends circles
                int num = (map.get(name1)).union(map.get(name2));
                
                // Print number of friends in circle
                io.println(num);
            }
        }
        // Close I/O stream and terminate
        io.close();
    }
}
