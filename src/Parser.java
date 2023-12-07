import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

/**
 * Parser class parses through input file and executes instructions.
 * 
 * @author Vansh Parikh (vparikh33)
 * @author James Mathai (jmathai)
 * 
 * @version December 2023
 */
public class Parser {
    private Scanner file;
    private int hashSize;
    private Processor processor;

    /**
     * Default constructor for Parser.
     */
    public Parser(String filename, int size) throws FileNotFoundException {
        file = new Scanner(new File(filename));
        hashSize = size;
        processor = new Processor(hashSize);
    }


    public void parseAll() {
        while (file.hasNext()) {
            parseNext();
        }
    }


    /**
     * Parses through tokens in input file and executes them as it goes.
     */
    private void parseNext() {
        String command = file.next();
        switch (command) {
            case "insert":
                file.useDelimiter("<SEP>");
                String artist = file.next().trim();
                file.useDelimiter("\n");
                String song = file.next();
                song = song.substring("<SEP>".length(), song.length() - 1);
                file.useDelimiter("\\p{javaWhitespace}+");

                processor.insert(artist, song);
                break;

            case "remove":
                String token = file.next();
                file.useDelimiter("\n");
                String name = file.next();
                name = name.substring(1); // Remove space from front
                file.useDelimiter("\\p{javaWhitespace}+");

                processor.remove(token, name);
                break;

            case "print":
                String type = file.next();

                processor.print(type);
                break;

            default:
                break;
        }
    }


    /**
     * Accessor method retrieves hash size.
     * 
     * @return hash size
     */
    public int getHashSize() {
        return hashSize;
    }
}
