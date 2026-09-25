// ----------
// MiniLang Project - CSC 220
//
// Authors: Kelvin & Jonathan
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
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MiniLang {

    public static void main(String[] args) throws IOException {

        //Calls getText function. Stores array into programText
        String[] programText = getText();

        //checking array that it read correctly
        for (int i = 0; i < programText.length; i++) {
            System.out.println(programText[i]);
        }

        //checking validity of code to be true or false
        System.out.println(isValidCode(programText));

        //checking logic
        //String[][]
        for (String[] r : toMatrix(programText))
            System.out.println(r[1]);
        runCode(toMatrix(programText));

    }

    // isValidCode
    // - checks legitimacy of the text
    // - checks how many commands are called and if there are too many
    // - ___
    // returns false in any case of invalidity, defaults true otherwise
    public static boolean isValidCode(String[] code) {
        String[] validCommandName = {"let", "display"};

        //for each line
        for (int i = 0; i < code.length; i++) {
            String[] str = code[i].split(" ");      //split line into elements split by whitespace

////TO-DO: Check first element and compare with valid commands
            //checks every element in the line
            int commandCount = 0;
            for (int j = 0; j < str.length; j++) {
                //running checks for each element in line
                for (String name : validCommandName) {
                    //checking if there are multiple named commands in the same line
                    if (name.strip().equalsIgnoreCase(str[j])) { commandCount++; }
                }
                if (commandCount > 1) { return false; }     //returns false if any multiple commands in line
////TO-DO: other validity checks in the code text
            }

        }

        return true;
    }


    //
    public static void runCode(String[][] code) {
        //
        List<VariableClass> list = new ArrayList<>();
        list.add(new VariableClass("testing"));
        for (String[] c : code) {
            //
            if (c[0].equalsIgnoreCase("let")) {
                list.add(new VariableClass(c[1]));
            }
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName());
        }

    }


    // getText():
    // - reads off data from file
    // - searches for "MiniLang_Program.txt"
    // - tries to read
    // - if able to, for each line in the text file, append to StringBuilder
    // returns String array split by ";"
    private static String[] getText() throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("MiniLang_Program.txt"));

        } catch (IOException e) {
            System.out.println("Unable to read file.");
            throw new RuntimeException(e);
        } finally {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }
        br.close();
        return sb.toString().split(";");
    }


    //
    public static String[][] toMatrix(String[] code) {
        //
        String[][] m = new String[code.length][];

        for (int i = 0; i < code.length; i++) {
            m[i] = code[i].strip().split(" ");
        }

        return m;
    }


}
