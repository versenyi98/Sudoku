package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class is responsible for running the unit tests.
 * Currently available only in UNIX systems.
 */
public class Tester {
    
    /**
     * The current working directory.
     */
    private static final Path PWD = Paths.get(".").toAbsolutePath();
    /**
     * The path to the required third party libraries.
     */
    private static final String LIBS = PWD.getParent().getParent().toString() + "/libs/*";
    /**
     * The file name of the shell script containing the required commands.
     */
    private static final String fileName = "test.sh";

    /**
     * Compiles the java classes required to run the tests, afterwards it does run them
     * and saves the output to the filesystem.
     */
    public static void launch() {
        Process proc;
        try {
            proc = Runtime.getRuntime().exec("sh " + fileName);
            logCommand(proc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void logCommand(Process proc) throws IOException {
        BufferedReader stdInput = new BufferedReader(new 
                InputStreamReader(proc.getInputStream()));
         String s = null;
         while ((s = stdInput.readLine()) != null) {
             System.out.println(s);
         }
    }
    
}
