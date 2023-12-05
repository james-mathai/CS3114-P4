/**
 * Handle class allows for retrieval of objects from memory.
 * 
 * @author Vansh Parikh (vparikh33)
 * @author James Mathai (jmathai)
 * 
 * @version December 2023
 */
public class Handle {
    private String name;
    
    /**
     * Default constructor for Handle class.
     * 
     * @param tempName of item to store
     */
    public Handle(String tempName) {
        name = tempName;
    }
    
    /**
     * Returns name of item stored by handle.
     * 
     * @return name of item in handle
     */
    public String getName() {
        return name;
    }
    
    /**
     * Accessor method to retrieve item length.
     * 
     * @return length of name of object stored in handle
     */
    public int getLength() {
        return name.length();
    }
}
