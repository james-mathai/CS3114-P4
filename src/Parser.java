import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
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
    private BufferedReader reader;
    private int hashSize;
    private Processor processor;

    /**
     * Default constructor for Parser.
     */
    public Parser(String filename, int size) {
        try {
            reader = new BufferedReader(new FileReader(filename));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        hashSize = size;
        processor = new Processor(hashSize);
    }


    public void parseAll() {
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                parseNext(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Parses through tokens in input file and executes them as it goes.
     */
    private void parseNext(String line) {
        String command = line.substring(0, line.indexOf(" "));
        String args = line.substring(line.indexOf(" ") + 1);
        switch (command) {
            case "insert":
                String[] temp = args.split("<SEP>");
                String artist = temp[0];
                String song = temp[1];
                processor.insert(artist, song);
                break;

            case "remove":
                String token = args.substring(0, args.indexOf(" "));
                String name = args.substring(args.indexOf(" ") + 1);
                processor.remove(token, name);
                break;

            case "print":
                processor.print(args);
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
