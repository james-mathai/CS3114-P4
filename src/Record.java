/**
 * Record class represents nodes/vertexes in a graph.
 * 
 * @author Vansh Parikh (vparikh33)
 * @author James Mathai (jmathai)
 * 
 * @version December 2023
 */
public class Record {
    private String key;
    private int vertex;

    /**
     * Default constructor for Record class.
     * 
     * @param hand
     *            is the handle to store
     * @param vert
     *            is the vertex to store
     */
    public Record(String k, int v) {
        key = k;
        vertex = v;
    }


    /**
     * Accessor method to retrieve key.
     * 
     * @return key
     */
    public String key() {
        return key;
    }


    /**
     * Accessor method to retrieve vertex.
     * 
     * @return vertex
     */
    public int vertex() {
        return vertex;
    }


    /**
     * Changes record's key.
     * 
     * @param newKey
     *            is the updated key
     */
    public void setKey(String newKey) {
        key = newKey;
    }


    /**
     * Changes record's vertex value.
     * 
     * @param newVertex
     *            is the new vertex value
     */
    public void setVertex(int newVertex) {
        vertex = newVertex;
    }
}
