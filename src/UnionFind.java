/**
 * Union/Find tree implementation
 * 
 * @author James Mathai (jmathai)
 * @author Vansh Parikh (vparikh33)
 * @version December 2023
 */
public class UnionFind {
    private int[] array;

    /**
     * Constructor for the UnionFind class
     * 
     * @param size
     *            The capacity of the graph (not size because there may be
     *            indices within graph that are empty)
     */
    UnionFind(int size) {
        array = new int[size];
        // Initialize array
        for (int i = 0; i < size; i++) {
            array[i] = -1;
        }
    }


    /**
     * Merge two groups
     * 
     * @param a
     *            A vertex in group A
     * @param b
     *            A vertex in group B
     */
    public void union(int a, int b) {
        int root1 = find(a);
        int root2 = find(b);
        if (root1 != root2) {
            array[root1] = root2;
        }
    }


    /**
     * Find the root for a given vertex
     * 
     * @param v
     *            The vertex to check
     * @return The root of v's group
     */
    public int find(int v) {
        while (array[v] != -1) {
            v = array[v];
        }
        return v;
    }
}
