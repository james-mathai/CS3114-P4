/**
 * Processor class allows centralization of all command processing.
 * 
 * @author Vansh Parikh (vparikh33)
 * @author James Mathai (jmathai)
 * 
 * @version December 2023
 */
public class Processor {
    private Hash artists;
    private Hash songs;
    // private MemManager manager;
    // private Graph graph;
    
    /**
     * Default constructor for Processor class.
     */
    public Processor(int size) {
        
    }
    
    /**
     * Attempts to insert artist/song into the appropriate table
     *  and connects the record in graph.
     *  
     * @param artist to insert
     * @param song to insert
     */
    public void insert(String artist, String song) {
        
    }
    
    /**
     * Attempts to remove record based on the type of record it is.
     * 
     * @param type of record to remove
     * @param name of record to remove
     */
    public void remove(String type, String name) {
        
    }
    
    /**
     * Outputs all information determined by the type of print.
     * 
     * @param type of print info to output
     */
    public void print(String type) {
        
    }
    
    /**
     * Accessor method retrieves artist table.
     * 
     * @return artist table
     */
    public Hash getArtistTable() {
        return artists;
    }
    
    /**
     * Accessor method retrieves song table.
     * 
     * @return song table
     */
    public Hash getSongTable() {
        return songs;
    }
}
