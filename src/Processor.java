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
     * 
     * @param size
     *            The initial size of the hash tables (and the graph)
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
                return;
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
                return;
            }
        }

        if (v == -1) {
            System.out.println("THIS SHOULD NOT HAPPEN");
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
            curr = table.getIndex(i);
            if (curr != null) {
                String key = "|" + curr.key() + "|";
                if (curr == table.tombstone()) {
                    key = "TOMBSTONE";
                }
                System.out.println(i + ": " + key);
            }
        }
        System.out.println("total " + type + "s: " + table.getSize());
    }


    /**
     * Print graph information (Union/Find and Floyd's algorithms courtesy of
     * OpenDSA)
     */
    private void printGraph() {
        UnionFind temp = new UnionFind(graph.size());
        // Join connected vertices using Union/Find
        for (int i = 0; i < graph.size(); i++) {
            for (int j = 0; j < graph.size(); j++) {
                if (graph.hasVertex(i) && graph.hasVertex(j) && graph.hasEdge(i,
                    j)) {

                    temp.union(i, j);
                }
            }
        }

        // Determine which group is largest and which it is
        int[] count = new int[graph.size()];
        for (int i = 0; i < count.length; i++) {
            count[i] = 0;
        }
        for (int i = 0; i < count.length; i++) {
            if (graph.hasVertex(i)) {
                count[temp.find(i)]++;
            }
        }
        int max = 0;
        int root = -1;
        int components = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                components++;
                if (count[i] > max) {
                    max = count[i];
                    root = i;
                }
            }
        }

        // Create a representation of vertices within the chosen group
        int[] vertices = new int[max];
        int numVertices = 0;
        for (int i = 0; i < graph.size(); i++) {
            if (temp.find(i) == root) {
                vertices[numVertices++] = i;
            }
        }

        // Use Floyd's algorithm
        int[][] dist = new int[numVertices][numVertices];
        // Initialize distances between exactly two vertices (1 if there is an
        // edge between them, "infinity" if not)
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (graph.hasEdge(vertices[i], vertices[j])) {
                    dist[i][j] = 1;
                }
                else {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        // Brute force paths
        for (int k = 0; k < numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    if ((dist[i][k] != Integer.MAX_VALUE)
                        && (dist[k][j] != Integer.MAX_VALUE)
                        && (dist[i][j] > (dist[i][k] + dist[k][j]))) {

                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Find the max distance that isn't "infinity"
        int diameter = 0;
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (graph.hasVertex(i) && graph.hasVertex(j)
                    && dist[i][j] != Integer.MAX_VALUE && dist[i][j] > diameter)
                    diameter = dist[i][j];
            }
        }

        System.out.println("There are " + components + " connected components");
        System.out.println("The largest connected component has " + max
            + " elements");
        System.out.println("The diameter of the largest component is "
            + diameter);
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
