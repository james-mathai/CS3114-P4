import student.TestCase;

/**
 * Tests for the HashTable class
 * 
 * @author James Mathai (jmathai)
 * @author Vansh Parikh (vparikh33)
 * @version December 2023
 */
public class HashTableTest extends TestCase {
    private HashTable h;

    /**
     * Set up test cases
     */
    public void setUp() {
        h = new HashTable(10);
    }


    /**
     * Test that the hash function is correct (with probing)
     */
    public void testHash() {
        assertEquals(Hash.h("a", 10), h.probe("a"));
        h.insert("a");
        assertEquals((Hash.h("k", 10) + 1) % 10, h.probe("k"));
        h.insert("k");
        assertEquals((Hash.h("u", 10) + 4) % 10, h.probe("u"));
    }


    /**
     * Test that we can insert, set, and get values
     */
    public void testInsertSetGet() {
        h.insert("a");
        h.set("a", 42);
        h.insert("b");
        h.set("b", 43);
        h.insert("k");
        h.set("k", 44);
        h.insert("u");
        h.set("u", 45);

        assertEquals(42, h.get("a"));
        assertEquals(43, h.get("b"));
        assertEquals(44, h.get("k"));
        assertEquals(45, h.get("u"));

        assertTrue(h.remove("a"));
        assertTrue(h.remove("k"));
        assertFalse(h.remove("c"));

        assertEquals(-1, h.get("a"));
        assertEquals(43, h.get("b"));
        assertEquals(-1, h.get("k"));
        assertEquals(45, h.get("u"));
    }


    /**
     * Test that the hashtable grows as it should
     */
    public void testGrow() {
        h.insert("a");
        h.set("a", 42);
        h.insert("b");
        h.set("b", 43);
        h.insert("k");
        h.set("k", 44);
        h.insert("u");
        h.set("u", 45);
        h.insert("c");
        h.set("c", 46);
        h.insert("d");
        h.set("d", 47);

        /*
         * DEBUG MESSAGE
         * for (int i = 0; i < h.getCapacity(); i++) {
         * String s = "null";
         * if (h.getIndex(i) != null)
         * s = h.getIndex(i).key() + ": " + h.getIndex(i).vertex();
         * System.out.println(i + " -> " + s);
         * }
         */

        assertEquals(42, h.get("a"));
        assertEquals(43, h.get("b"));
        assertEquals(44, h.get("k"));
        assertEquals(45, h.get("u"));
        assertEquals(46, h.get("c"));
        assertEquals(47, h.get("d"));

    }
}
