/**
 * Record class represents nodes/vertexes in a graph.
 * 
 * @author Vansh Parikh (vparikh33)
 * @author James Mathai (jmathai)
 * 
 * @version December 2023
 */
public class Record {
    private Handle handle;
    private int vertex;
    
    /**
     * Default constructor for Record class.
     * 
     * @param hand is the handle to store
     * @param vert is the vertex to store
     */
    public Record(Handle hand, int vert) {
        handle = hand;
        vertex = vert;
    }
    
    /**
     * Accessor method to retrieve handle.
     * 
     * @return handle
     */
    public Handle getHandle() {
        return handle;
    }
    
    /**
     * Accessor method to retrieve vertex.
     * 
     * @return vertex
     */
    public int getVertex() {
        return vertex;
    }
    
    /**
     * Changes record's handle object.
     * 
     * @param newHandle is the updated handle
     */
    public void setHandle(Handle newHandle) {
        handle = newHandle;
    }
    
    /**
     * Changes record's vertex value.
     * 
     * @param newVertex is the new vertex value
     */
    public void setVertex(int newVertex) {
        vertex = newVertex;
    }
}
