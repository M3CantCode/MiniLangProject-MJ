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
        System.out.println("--------------------------------------------------");
        //checking validity of code to be true or false
        System.out.println(isValidCode(programText));
        System.out.println("--------------------------------------------------");
        //checking logic
        //String[][]
        for (String[] r : toMatrix(programText))
            System.out.println(r[1]);
        runCode(toMatrix(programText));
        System.out.println("--------------------------------------------------");
        //checking tokenizer
        tokenize(programText);
    }

    // tokenize():
    // - takes in a String array of code
    // - splits each line into chunks based on whitespace
    // - checks if chunk(s) meet the criteria for a token
    // - prints out the type and value of each token (will be used for debugging purposes)
    // - TODO: add more functionality to the tokenizer, such as handling variables and other types of tokens
    public static void tokenize(String[] code) {
        String[] operators = {"+", "-", "*", "/", "%"};
        ArrayList<Token> tokens = new ArrayList<>();
        for (String line : code) {
            String[] chunks = line.split("(?<=;)|(?=;)|\\s+"); // splits the line into chunks based on whitespace and semicolons

            // check if line is empty or a comment, if so, skip to the next line
            // NOTE: doesn't work properly, need to fix
            if (chunks.length == 0 || chunks[0].equals("//")) {
                System.out.println("Skipping line: " + line);
                continue; 
            }

            // Iterates through the chunks to create tokens
            for (int i = 0; i < chunks.length; i++) {
                String chunk = chunks[i];
                // checks if the chunk is an operator, (For Operator Tokens)
                if (Arrays.asList(operators).contains(chunks[i])) {
                    // creates a new Operator token
                    Operator op = new Operator(chunk);
                    tokens.add(op);
                    System.out.println("Operator: " + op.getType());
                // checks if the chunk is a 'let' command (For Variable Tokens)
                } else if (chunk.matches("let")) {

                    // check if the next chunks of text are valid for a 'let' command
                    String varName = chunks[i + 1]; // should be the variable name (only letters)
                    String assignment = chunks[i + 2]; // should be "="
                    String varValue = chunks[i + 3]; // should be either a number or boolean
                    //TODO: also check if varValue is a valid integer variable
                    if (varName == null || assignment == null ||
                        varValue == null || varValue.equals(";") ||
                          !assignment.equals("=")) {
                        System.out.println("Error: Invalid syntax for 'let' command.");
                        continue;
                    }

                    // check if the variable name is valid (only letters)
                    boolean variableNameValid = varName.matches("[a-zA-Z]+");
                    if (!variableNameValid) {
                        System.out.println("Error: Invalid variable name. Variable names must consist of letters only.");
                        continue;
                    }
                    
                    // check if the variable value is valid (number or boolean)
                    // if the variable value is valid, create a new Variable token
                    boolean onlyNumbers = varValue.matches("[0-9]+");
                    boolean isBoolean = varValue.equals("true") || varValue.equals("false");
                    if (isBoolean) {
                        Variable t = new Variable(varName,varValue);
                        System.out.println("Boolean: " + t.getName());
                        tokens.add(t);
                        System.out.println(t.getName() + "'s value is: " + t.getValue());
                    } else if (onlyNumbers) {
                        Variable t = new Variable(varName,varValue);
                            System.out.println("Number: " + t.getName());
                            tokens.add(t);
                            System.out.println(t.getName() + "'s value is: " + t.getValue());
                        } else {
                            System.out.println("Error: Invalid value for variable. Must be either a boolean or a number.");
                        }
                    

                } else {
                    // creates an unclassified token
                    // NOTE: This is a placeholder for any other tokens that may be added in the future
                    Token t = new Token("token", chunk);
                    System.out.println("Token: " + t.getValue());
                    tokens.add(t);
                }
            }
        }
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

////TODO: Check first element and compare with valid commands
            //checks every element in the line
            int commandCount = 0;
            for (int j = 0; j < str.length; j++) {
                //running checks for each element in line
                for (String name : validCommandName) {
                    //checking if there are multiple named commands in the same line
                    if (name.strip().equalsIgnoreCase(str[j])) { commandCount++; }
                }
                if (commandCount > 1) { return false; }     //returns false if any multiple commands in line
////TODO: other validity checks in the code text
            }

        }

        return true;
    }


    //
    public static void runCode(String[][] code) {
        //
        List<Variable> list = new ArrayList<>();
        list.add(new Variable("testing", "test"));
        for (String[] c : code) {
            //
            if (c[0].equalsIgnoreCase("let")) {
                list.add(new Variable(c[1], c[2]));
            }
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName() + ": " + list.get(i).getValue());
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
