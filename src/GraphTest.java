import student.TestCase;

/**
 * Tests for the Graph class
 * 
 * @author James Mathai (jmathai)
 * @author Vansh Parikh (vparikh33)
 * @version December 2023
 */
public class GraphTest extends TestCase {
    private Graph graph;
    private int a;
    private int b;
    private int c;

    /**
     * Set up test cases
     */
    public void setUp() {
        graph = new Graph(4);

        // Add vertices
        a = graph.addVertex();
        b = graph.addVertex();
        c = graph.addVertex();

        // Add edges
        graph.addEdge(a, b);
        graph.addEdge(b, c);
        graph.addEdge(a, c);
    }


    /**
     * Test that hasVertex() doesn't give false positives
     */
    public void testHasVertex() {
        assertFalse(graph.hasVertex(3));
        assertFalse(graph.hasVertex(4));
        assertFalse(graph.hasVertex(-1));
    }


    /**
     * Test that vertices were added correctly in setUp()
     */
    public void testAddVertex() {
        // Check number of vertices
        assertEquals(graph.numVertices(), 3);

        // Check that addVertex() returns the correct values
        assertEquals(0, a);
        assertEquals(1, b);
        assertEquals(2, c);

        // Check that the vertices were actually added
        assertTrue(graph.hasVertex(a));
        assertTrue(graph.hasVertex(b));
        assertTrue(graph.hasVertex(c));
    }


    /**
     * Test that edges were added correctly in setUp()
     */
    public void testAddEdge() {
        // Check number of edges
        assertEquals(graph.numEdges(), 3);

        // Check that edges were added
        assertTrue(graph.hasEdge(a, b));
        assertTrue(graph.hasEdge(b, c));
        assertTrue(graph.hasEdge(a, c));
    }


    /**
     * Ensure that removeVertex() removes a vertex along with its associated
     * edges
     */
    public void testRemoveVertex() {
        // Remove the vertex
        assertTrue(graph.removeVertex(b));

        // Ensure that the right vertices exist
        assertTrue(graph.hasVertex(a));
        assertFalse(graph.hasVertex(b));
        assertTrue(graph.hasVertex(a));

        // Ensure that the right edges exist
        assertFalse(graph.hasEdge(a, b));
        assertFalse(graph.hasEdge(b, c));
        assertTrue(graph.hasEdge(a, c));

        // Ensure that the number of vertices and edges is updated correctly
        assertEquals(2, graph.numVertices());
        assertEquals(1, graph.numEdges());
    }


    /**
     * Test removeVertex() when the vertex doesn't exist
     */
    public void testIllegalRemoveVertex() {
        assertFalse(graph.removeVertex(-1));
        assertTrue(graph.removeVertex(a));
        assertFalse(graph.removeVertex(a));
    }


    /**
     * Test that removeEdge() works
     */
    public void testRemoveEdge() {
        assertEquals(3, graph.numEdges());
        assertTrue(graph.hasEdge(a, b));
        assertTrue(graph.removeEdge(a, b));
        assertEquals(2, graph.numEdges());
        assertFalse(graph.hasEdge(a, b));
    }


    /**
     * Test that removeEdge() works when the edge doesn't exist
     */
    public void testIllegalRemoveEdge() {
        assertFalse(graph.removeEdge(-1, -1));
        assertFalse(graph.removeEdge(c, a));
    }


    /**
     * Test that the graph grows when it reaches capacity
     */
    public void testGrow() {
        // Test that the graph is allowed to be full before growing
        graph.addVertex();
        assertEquals(4, graph.size());

        // Test that the graph does grow
        graph.addVertex();
        assertEquals(8, graph.size());
    }


    /**
     * Test that addVertex() fills empty spaces before growing
     */
    public void testAddVertexAfterRemove() {
        // Remove a vertex to create an empty space
        graph.removeVertex(b);

        // Check that the empty space is filled before growing
        assertEquals(3, graph.addVertex());
        assertEquals(1, graph.addVertex());
        assertEquals(4, graph.size());

        // Check that the graph still grows
        assertEquals(4, graph.addVertex());
        assertEquals(8, graph.size());
    }
}
