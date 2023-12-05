/**
 * Hash table class
 *
 * @author Vansh Parikh (vparikh33)
 * @author James Mathai (jmathai)
 * 
 * @version December 2023
 */
public class Hash {

    /**
     * Computes the hash function.
     * 
     * @param s
     *      The string that we are hashing
     * @param length
     *      Length of the hash table (needed because this method is static)
     * @return
     *      The hash function value (the home slot in the table for this key)
     */
    public static int hash(String s, int length) {
        int intLength = s.length() / 4;
        long sum = 0;
        
        for (int j = 0; j < intLength; j++) {
            char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }

        char[] c = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }

        return (int)(Math.abs(sum) % length);
    }
}
