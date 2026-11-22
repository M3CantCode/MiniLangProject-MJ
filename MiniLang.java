// ----------
// MiniLang Project - CSC 220
//
// Authors: Melvin & Jonathan
//
// Purpose:
// -
// -
// -
//
// How It Works:
// -
// -
// ----------

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MiniLang {
    public static void main(String[] args) {
        // Check for correct usage
        if (args.length < 1) {
            System.out.println("To run your MiniLang program type: java MiniLang <source_file> [--trace]");
            return;
        }

        String filePath = args[0];
        boolean trace = args.length > 1 && args[1].equals("--trace");

        // Step 1: Read the source file and tokenize it
        try {
            String source = Files.readString(Path.of(filePath));
            Lexer lexer = new Lexer(source);
            List<TOKEN> tokens = lexer.scanTokens();

            if (trace) {
                for (TOKEN token : tokens) {
                    System.out.println(token);
                }
            }
            // Step 2: pass `tokens` into Parser here (to complete later)
        } catch (IOException e) {
            System.out.println("Error: Could not read file '" + filePath + "'.");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
