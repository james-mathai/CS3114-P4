import student.TestCase;

/**
 * @author James Mathai (jmathai)
 * @author Vansh Parikh (vparikh33)
 * @version December 2023
 */
public class HashTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing Here
    }


    /**
     * Check out the sfold method
     *
     * @throws Exception
     *             either a IOException or FileNotFoundException
     */
    public void testSfold() throws Exception {
        assertTrue(Hash.h("a", 10000) == 97);
        assertTrue(Hash.h("b", 10000) == 98);
        assertTrue(Hash.h("aaaa", 10000) == 1873);
        assertTrue(Hash.h("aaab", 10000) == 9089);
        assertTrue(Hash.h("baaa", 10000) == 1874);
        assertTrue(Hash.h("aaaaaaa", 10000) == 3794);
        assertTrue(Hash.h("Long Lonesome Blues", 10000) == 4635);
        assertTrue(Hash.h("Long   Lonesome Blues", 10000) == 4159);
        assertTrue(Hash.h("long Lonesome Blues", 10000) == 4667);
        
        assertEquals(12, Hash.h("Bohemian Rhapsody", 20));
        assertEquals(3, Hash.h("Brothers in Arms", 20));
        assertEquals(5, Hash.h("Aqualung", 20));
        assertEquals(16, Hash.h("Telegraph Road", 20));
        assertEquals(5, Hash.h("Thick as a Brick", 20));
        assertEquals(12, Hash.h("Father to Son", 20));
    }
}
