import java.io.IOException;

/**
 * Hash table class allows for hashed storage of records.
 *
 * @author Vansh Parikh (vparikh33)
 * @author James Mathai (jmathai)
 * 
 * @version December 2023
 */
public class Hash {
    private Record[] table;
    private int count;
    private Record tombstone;
    
    /**
     * Default constructor for Hash class.
     */
    public Hash(int size) {
        count = 0;
        table = new Record[size];
        tombstone = new Record(new Handle("tombstone"), -1);
    }
    
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
    
    /**
     * Attempts to insert a name into table.
     * 
     * @param name of item to insert
     * 
     * @return return T/F depending on success of insert
     */
    public boolean insert(String name) {
        if (check(name) == -1) {
            int temp = probe(name);
            
            if (temp != -1) {
                Handle tempHandle = new Handle(name);
                table[temp] = new Record(tempHandle, -1);
                count++;
                
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
    
    /**
     * Attempts to remove an item from the hash table.
     * 
     * @param name of item to remove from table
     * 
     * @return T/F depending on success of removal
     */
    public boolean remove(String name) {
        int found = check(name);
        
        if (found != -1) {
            table[found] = tombstone;
            count--;
            
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Checks hashtable for a specific value.
     * 
     * @param value to be searched for
     * 
     * @return index of value if found, -1 if not
     */
    public int check(String value) {
        int base = hash(value, table.length);
        int temp = base;
        
        for (int i = 0; i < table.length; i++) {
            if (table[temp] == null) {
                return -1;
            }
            else if (table[temp].getHandle().getName().equals(value)) {
                return temp;
            }
            else {
                temp = ((base + (i * i)) % table.length);
            }
        }
        
        return -1;
    }
    
    /**
     * Probes table for an insert point.
     * 
     * @param name of the record to insert
     * 
     * @return index of insert point, -1 if not found
     */
    public int probe(String name) {
        int base = hash(name, table.length);
        int temp = base;
        
        for (int i = 0; i < table.length; i++) {
            if (table[temp] != null) {
                temp = ((base + (i * i)) % table.length);
            }
            else {
                return temp;
            }
        }
        return -1;
    }
    
    /**
     * Resizes and rehashes table if over half capacity.
     * 
     * @return T/F depending on success of resize
     */
    public boolean resize() {
        if (getSize() >= (table.length / 2)) {
            int newSize = table.length * 2;
            int tempSize = count;
            
            // record array without tombstones -> converted to null
            Record[] values = new Record[table.length];
            
            for (int i = 0; i < values.length; i++) {
                if (table[i].equals(tombstone)) {
                    values[i] = null;
                }
                else {
                    values[i] = table[i];
                }
                
                count = tempSize;
            }
            
            table = new Record[newSize];
            
            for (int i = 0; i < values.length; i++) {
                if (values[i] != null) {
                    int index =
                            probe(values[i].getHandle().getName());
                    table[index] = values[i];
                }
            }
            
            return true;
        }
        
        return false;
    }
    
    /**
     * Returns number of objects in the hash table.
     * 
     * @return current number of items in table
     */
    public int getSize() {
        return count;
    }
    
    /**
     * Returns current capacity of the hash table.
     * 
     * @return length/capacity of table
     */
    public int getCapacity() {
        return table.length;
    }
}
