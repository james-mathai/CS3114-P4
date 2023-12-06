/**
 * An adjacency list graph implementation
 * 
 * @author James Mathai (jmathai)
 * @author Vansh Parikh (vparikh33)
 * @version December 2023
 */
public class Graph {

    /**
     * Stores an edge in an adjacency list
     */
    private class Edge {
        private int vertex;
        private Edge prev, next;

        /**
         * Create an edge pointing
         * 
         * @param v
         *            The vertex that the edge points to
         * @param p
         *            The previous edge in the adjacency list
         * @param n
         *            The next edge in the adjacency list
         */
        private Edge(int v, Edge p, Edge n) {
            vertex = v;
            prev = p;
            next = n;
        }
    }

    private Edge[] adjLists;
    private int size;
    private int numVertices;
    private int numEdges;
    private int nextVertex;

    /**
     * Create a graph
     * 
     * @param n
     *            The initial capacity
     */
    public Graph(int n) {
        adjLists = new Edge[n];
        size = n;
        numVertices = 0;
        numEdges = 0;
        nextVertex = 0;
    }


    /**
     * Get numVertices
     * 
     * @return The number of vertices in the graph
     */
    public int numVertices() {
        return numVertices;
    }


    /**
     * Get numEdges
     * 
     * @return The number of edges
     */
    public int numEdges() {
        return numEdges;
    }


    /**
     * Get size
     * 
     * @return The capacity of the graph
     */
    public int size() {
        return size;
    }


    /**
     * Find the edge between two vertices
     * 
     * @param v
     *            The first vertex
     * @param w
     *            The second vertex
     * @return The edge immediately before where the specified edge should be
     */
    private Edge find(int v, int w) {
        // If either vertex does not exist, the edge cannot exist either
        if (!(hasVertex(v) && hasVertex(w))) {
            return null;
        }

        // Iterate through the adjacency list until we come to the correct
        // location
        Edge curr = adjLists[v];
        while (curr.next != null && curr.next.vertex < w) {
            curr = curr.next;
        }
        return curr;
    }


    /**
     * Add a vertex
     * 
     * @return The index of the added vertex
     */
    public int addVertex() {
        // If we run out of space, double the capacity
        if (numVertices >= size) {
            size *= 2;
            Edge[] newAdjLists = new Edge[size];
            for (int i = 0; i < numVertices; i++) {
                newAdjLists[i] = adjLists[i];
            }
            adjLists = newAdjLists;
        }

        int idx = nextVertex;
        // Ideally, we append to the end
        if (idx < size) {
            nextVertex++;

        }
        // We know we have space, so if we cannot append to the end, we must
        // find an open space somewhere in the middle
        else {
            idx = 0;
            while (hasVertex(idx)) {
                idx++;
            }
        }
        // Set the head of the adjacency list and update numVertices
        adjLists[idx] = new Edge(-1, null, null);
        numVertices++;
        return idx;
    }


    /**
     * Add an edge
     * 
     * @param v
     *            The first vertex
     * @param w
     *            The second vertex
     * @return Whether the edge was added
     */
    public boolean addEdge(int v, int w) {
        // If either vertex doesn't exist or if the edge already exists, we
        // can't add
        if (!(hasVertex(v) && hasVertex(w)) || hasEdge(v, w)) {
            return false;
        }

        // Add the edge
        Edge curr = find(v, w);
        curr.next = new Edge(w, curr, curr.next);
        // Update curr.next.next if necessary
        if (curr.next.next != null) {
            curr.next.next.prev = curr.next;
        }
        numEdges++;
        return true;
    }


    /**
     * Remove an edge
     * 
     * @param v
     *            The first vertex
     * @param w
     *            The second vertex
     * @return Whether the edge was removed
     */
    public boolean removeEdge(int v, int w) {
        // If the edge doesn't exist, it can't be removed
        if (!hasEdge(v, w)) {
            return false;
        }

        // Find and remove
        Edge curr = find(v, w);
        curr.next = curr.next.next;
        // Update curr.next if necessary
        if (curr.next != null) {
            curr.next.prev = curr;
        }
        numEdges--;
        return true;
    }


    /**
     * Remove a vertex
     * 
     * @param v
     *            The index of the vertex
     * @return Whether the vertex was removed
     */
    public boolean removeVertex(int v) {
        // If the vertex doesn't exist, we can't remove it
        if (!hasVertex(v)) {
            return false;
        }

        // Update numEdges to reflect that all edges from vertex v are being
        // removed
        for (Edge curr = adjLists[v]; curr.next != null; curr = curr.next) {
            numEdges--;
        }
        // Remove all vertex pointing to vertex v
        for (int i = 0; i < numVertices; i++) {
            removeEdge(i, v);
        }
        adjLists[v] = null;
        numVertices--;
        return true;
    }


    /**
     * Check whether an edge exists
     * 
     * @param v
     *            The first vertex
     * @param w
     *            The second vertex
     * @return Whether an edge exists from vertex v to vertex w
     */
    public boolean hasEdge(int v, int w) {
        // Both vertices must exist for the edge to exist
        if (!(hasVertex(v) && hasVertex(w))) {
            return false;
        }

        // Find where the edge should be and if it's there, return true
        Edge curr = find(v, w);
        return curr.next != null && curr.next.vertex == w;
    }


    /**
     * Check whether a vertex exists
     * 
     * @param v
     *            The index of the vertex
     * @return Whether vertex v exists
     */
    public boolean hasVertex(int v) {
        // v must be within the range of adjLists and there must be an
        // associated adjacency list
        return v >= 0 && v < size && adjLists[v] != null;
    }


    /**
     * Get the neighbors of a vertex
     * 
     * @param v
     *            The index of a vertex
     * @return An array of the indices of neighbors
     */
    public int[] neighbors(int v) {
        // Count the number of neighbors
        int n = 0;
        Edge curr;
        for (curr = adjLists[v]; curr != null; curr = curr.next) {
            n++;
        }
        // Get the neighbors and return
        int[] temp = new int[n];
        n = 0;
        for (curr = adjLists[v].next; curr != null; curr = curr.next) {
            temp[n++] = curr.vertex;
        }
        return temp;
    }
}
