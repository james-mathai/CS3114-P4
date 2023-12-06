import student.TestCase;

/**
 * @author <Put something here>
 * @version <Put something here>
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
        assertTrue(Hash.hash("a", 10000) == 97);
        assertTrue(Hash.hash("b", 10000) == 98);
        assertTrue(Hash.hash("aaaa", 10000) == 1873);
        assertTrue(Hash.hash("aaab", 10000) == 9089);
        assertTrue(Hash.hash("baaa", 10000) == 1874);
        assertTrue(Hash.hash("aaaaaaa", 10000) == 3794);
        assertTrue(Hash.hash("Long Lonesome Blues", 10000) == 4635);
        assertTrue(Hash.hash("Long   Lonesome Blues", 10000) == 4159);
        assertTrue(Hash.hash("long Lonesome Blues", 10000) == 4667);
    }
}
