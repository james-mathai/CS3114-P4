/**
 * Hash table class allows for hashed storage of records.
 *
 * @author Vansh Parikh (vparikh33)
 * @author James Mathai (jmathai)
 * 
 * @version December 2023
 */
public class HashTable {
    private Record[] table;
    private int count;
    private Record tombstone;

    /**
     * Default constructor for HashTable class.
     * 
     * @param size
     *            The initial capacity of the table
     */
    public HashTable(int size) {
        count = 0;
        table = new Record[size];
        tombstone = new Record("tombstone", -1);
    }


    /**
     * Attempts to insert a name into table.
     * 
     * @param name
     *            of item to insert
     * 
     * @return return T/F depending on success of insert
     */
    public boolean insert(String name) {
        if (hash(name) == -1) {
            int temp = probe(name);

            if (temp != -1) {
                temp = probe(name);
                table[temp] = new Record(name, -1);
                count++;

                resize();

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
     * Set a value
     * 
     * @param name
     *            The key
     * @param value
     *            The value to assign
     * @return Whether the value was assigned
     */
    public boolean set(String name, int value) {
        int idx = hash(name);
        if (idx == -1) {
            return false;
        }

        table[idx].setVertex(value);
        return true;
    }


    /**
     * Get a value from the table
     * 
     * @param name
     *            The key for the value
     * @return The value associated with the key
     */
    public int get(String name) {
        int idx = hash(name);
        if (idx == -1) {
            return -1;
        }
        return table[idx].vertex();
    }


    /**
     * Get the value stored at a given index in the table
     * 
     * @param index
     *            The index to look at
     * @return The value stored at that index
     */
    public Record getIndex(int index) {
        return table[index];
    }


    /**
     * Attempts to remove an item from the hash table.
     * 
     * @param name
     *            of item to remove from table
     * 
     * @return T/F depending on success of removal
     */
    public boolean remove(String name) {
        int found = hash(name);

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
     * @param key
     *            to be searched for
     * 
     * @return index of value if found, -1 if not
     */
    public int hash(String key) {
        int base = Hash.h(key, table.length);
        int temp = base;

        for (int i = 0;; i++) {
            if (table[temp] == null) {
                return -1;
            }
            else if (table[temp].key().equals(key)) {
                return temp;
            }
            else {
                temp = ((base + (i * i)) % table.length);
            }
        }
    }


    /**
     * Check whether the table stores a given key
     * 
     * @param key
     *            The key to check
     * @return Whether the table contains key
     */
    public boolean hasRecord(String key) {
        return hash(key) != -1;
    }


    /**
     * Probes table for an insert point.
     * 
     * @param name
     *            of the record to insert
     * 
     * @return index of insert point, -1 if not found
     */
    public int probe(String name) {
        if (count == table.length)
            return -1;
        int base = Hash.h(name, table.length);
        int temp = base;

        for (int i = 0;; i++) {
            if (table[temp] != null) {
                if (table[temp] == tombstone)
                    return temp;
                temp = ((base + (i * i)) % table.length);
            }
            else {
                return temp;
            }
        }
    }


    /**
     * Resizes and rehashes table if over half capacity.
     * 
     * @return T/F depending on success of resize
     */
    public boolean resize() {
        if (count > (table.length / 2)) {
            int newSize = table.length * 2;
            // int tempSize = count;

            // record array without tombstones -> converted to null
            Record[] values = new Record[table.length];

            for (int i = 0; i < values.length; i++) {
                if (table[i] != null && table[i].equals(tombstone)) {
                    values[i] = null;
                }
                else {
                    values[i] = table[i];
                }

                // count = tempSize;
            }

            table = new Record[newSize];

            for (int i = 0; i < values.length; i++) {
                if (values[i] != null) {
                    int index = probe(values[i].key());
                    table[index] = values[i];
                }
            }

            System.out.println("Song hash table size doubled.");
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


    /**
     * Return the tombstone instance
     * 
     * @return The tombstone
     */
    public Record tombstone() {
        return tombstone;
    }
}
