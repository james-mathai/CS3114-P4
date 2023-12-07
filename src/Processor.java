/**
 * Processor class allows centralization of all command processing.
 * 
 * @author Vansh Parikh (vparikh33)
 * @author James Mathai (jmathai)
 * 
 * @version December 2023
 */
public class Processor {
    private HashTable artists;
    private HashTable songs;
    private Graph graph;
    // private Graph graph;

    /**
     * Default constructor for Processor class.
     */
    public Processor(int size) {
        artists = new HashTable(size);
        songs = new HashTable(size);
        graph = new Graph(size);
    }


    /**
     * Attempts to insert artist/song into the appropriate table
     * and connects the record in graph.
     * 
     * @param artist
     *            to insert
     * @param song
     *            to insert
     */
    public void insert(String artist, String song) {
        int artistsSize = artists.getCapacity();
        int songsSize = songs.getCapacity();

        if (artists.hasRecord(artist) && songs.hasRecord(song) && graph.hasEdge(
            artists.get(artist), songs.get(song))) {

            System.out.println("|" + artist + "<SEP>" + song
                + "| duplicates a record already in the database.");
            return;
        }

        int artistVert;
        int songVert;

        if (artists.hasRecord(artist)) {
            artistVert = artists.get(artist);
        }
        else {
            artistVert = graph.addVertex();
            artists.insert(artist);
            artists.set(artist, artistVert);
            System.out.println("|" + artist
                + "| is added to the Artist database.");
        }
        if (songs.hasRecord(song)) {
            songVert = songs.get(song);
        }
        else {
            songVert = graph.addVertex();
            songs.insert(song);
            songs.set(song, songVert);
            System.out.println("|" + song + "| is added to the Song database.");
        }

        graph.addEdge(artistVert, songVert);
        graph.addEdge(songVert, artistVert);

        if (artists.getCapacity() > artistsSize) {
            System.out.println("Artist hash table size doubled.");
        }
        if (songs.getCapacity() > songsSize) {
            System.out.println("Song hash table size doubled.");
        }
    }


    /**
     * Attempts to remove record based on the type of record it is.
     * 
     * @param type
     *            of record to remove
     * @param name
     *            of record to remove
     */
    public void remove(String type, String name) {
        int v = -1;
        if (type.equals("artist")) {
            v = artists.get(name);
            if (artists.remove(name)) {
                System.out.println("|" + name
                    + "| is removed from the Artist database.");
            }
            else {
                System.out.println("|" + name
                    + "| does not exist in the Artist database.");
            }
        }
        if (type.equals("song")) {
            v = songs.get(name);
            if (songs.remove(name)) {
                System.out.println("|" + name
                    + "| is removed from the Song database.");
            }
            else {
                System.out.println("|" + name
                    + "| does not exist in the Song database.");
            }
        }

        if (v == -1) {
            return;
        }

        graph.removeVertex(v);
    }


    /**
     * Outputs all information determined by the type of print.
     * 
     * @param type
     *            of print info to output
     */
    public void print(String type) {
        if (type.equals("graph")) {
            printGraph();
            return;
        }

        HashTable table = null;
        if (type.equals("artist")) {
            table = artists;
        }
        if (type.equals("song")) {
            table = songs;
        }
        Record curr;
        for (int i = 0; i < table.getCapacity(); i++) {
            if ((curr = table.getIndex(i)) != null) {
                String key = "|" + curr.key() + "|";
                if (curr == table.tombstone()) {
                    key = "TOMBSTONE";
                }
                System.out.println(i + ": " + key);
            }
        }
        System.out.println("total " + type + "s: " + table.getSize());
    }


    private void printGraph() {

    }


    /**
     * Accessor method retrieves artist table.
     * 
     * @return artist table
     */
    public HashTable getArtistTable() {
        return artists;
    }


    /**
     * Accessor method retrieves song table.
     * 
     * @return song table
     */
    public HashTable getSongTable() {
        return songs;
    }
}
