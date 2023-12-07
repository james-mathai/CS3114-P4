// -------------------------------------------------------------------------
/**
 * Main for Graph project (CS3114/CS5040 Fall 2023 Project 4).
 * Usage: java GraphProject <init-hash-size> <command-file>
 *
 * @author James Mathai (jmathai)
 * @author Vansh Parikh (vparikh33)
 * @version December 2023
 *
 */

// On my honor:
// - I have not used source code obtained from another current or
// former student, or any other unauthorized source, either
// modified or unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

import java.io.FileNotFoundException;

public class GraphProject {
    private static Parser parser;
    private static Processor processor;

    /**
     * @param args
     *            Command line parameters
     * @throws FileNotFoundException
     *             If the specified file is not found
     * @throws NumberFormatException
     *             If initial hash size is ill-formed
     */
    public static void main(String[] args)
        throws NumberFormatException, FileNotFoundException {
        parser = new Parser(args[1], Integer.parseInt(args[0]));
    }
}
