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
import java.util.*;

public class MiniLang {

    public static void main(String[] args) throws IOException {

        String file1 = "MiniLang_Program.txt";
        String file2 = "MiniLang_Read.txt";
        String file3 = "MiniLang_ErrorTests.txt";

        //Scanner scanner = new Scanner(System.in);
        //System.out.print("Enter which file you wish to run. (1, 2, 3)\n: ");
        //String fileIn = scanner.nextLine();


        //Calls getText function. Stores array into programText
        String[] programText = getText(file3);

        //checking array that it read correctly

        System.out.println("--------------------------------------------------");
        //checking validity of code to be true or false
        for (String s : programText) {
            System.out.print("[" + s + "] ");
        }System.out.println(isValidCode(programText));
        System.out.println("--------------------------------------------------");

        //checking tokenizer
        tokenize(programText);
        System.out.println("--------------------------------------------------");

    }

    // tokenize():
    // - takes in a String array of code
    // - splits each line into chunks based on whitespace
    // - prints out the type and value of each token (will be used for debugging purposes)
    // - TODO: add more functionality to the tokenizer, such as handling variables and other types of tokens
    public static void tokenize(String[] code) {

        ArrayList<TOKEN> tokens = new ArrayList<>();
        for (String line : code) {                      //for each line in code
            String cleaned = (line.replaceAll("\\s+", " ").strip()) + " ";   //cleanup whitespaces
            System.out.println("--" + cleaned + "--");
            char chara = cleaned.charAt(0);
            StringBuilder sb = new StringBuilder();
            //boolean isComment = false;
            for (int i = 0; i < cleaned.length(); i++) {
                if (sb.toString().contains("//")) {
                    while (i < cleaned.length()) {
                        sb.append(cleaned.charAt(i));
                        i++;
                    }
                    tokens.add(new COMMENT(sb.toString()));
                    sb.delete(0, sb.length());
                }
                else if (cleaned.charAt(i) == ';') {
                    tokens.add(toToken(sb.toString()));
                    sb.delete(0, sb.length());
                    sb.append(cleaned.charAt(i));
                    tokens.add(new SEMICOLON(sb.toString()));
                    sb.delete(0, sb.length());
                    i++;
                }
                else if (cleaned.charAt(i) == ' ') {
                    if (!sb.toString().isBlank()) {
                        tokens.add(toToken(sb.toString()));
                        sb.delete(0, sb.length());
                    }
                    //else {sb.delete(0, sb.length());}
                }
                else {
                    sb.append(cleaned.charAt(i));
                }


                /*
                if (sb.toString().contains("//")) {
                    while (i < cleaned.length()) {
                        sb.append(cleaned.charAt(i));
                        i++;
                    }
                    tokens.add(new COMMENT(sb.toString()));
                    sb.delete(0, sb.length());
                } else if (cleaned.charAt(i) == ' ') {
                    if (sb.toString().equals("let")) {
                        tokens.add(new LET(sb.toString()));
                    } else if (isInArray(keywords, sb.toString())) {
                        tokens.add(new KEYWORD(sb.toString()));
                    } else if (sb.toString().equals("=")) {
                        tokens.add(new ASSIGN(sb.toString()));
                    } else if (isInArray(operator, sb.toString())) {
                        tokens.add(new OPERATOR(sb.toString()));
                    } else if (isNumber(sb.toString())) {
                        tokens.add(new VARIABLE(sb.toString()));
                    } else {
                        tokens.add(new IDENTIFIER(sb.toString()));
                    }
                    sb.delete(0, sb.length());
                }
                else {
//                    if (cleaned.charAt(i) == ';') {
//                        tokens.add(new IDENTIFIER(sb.toString()));
//                        sb.delete(0, sb.length());
//                        sb.append(cleaned.charAt(i));
//                        tokens.add(new SEMICOLON(sb.toString()));
//                        i++;
//                        sb.delete(0, sb.length());
//                    }
                    else if (cleaned.charAt(i) == '(') {
                        do {
                            sb.append(cleaned.charAt(i));
                            i++;
                        } while (cleaned.charAt(i) != ')');
                        sb.append(cleaned.charAt(i));
                        i++;
                        tokens.add(new IDENTIFIER(sb.toString()));
                        sb.delete(0, sb.length());
                    }
                    else { sb.append(cleaned.charAt(i)); }

                }
                */
            }
        }
        for (TOKEN tok : tokens) {
            System.out.printf("%s: %s\n", tok.getClass().getSimpleName(), tok.getName());
        }
    }

    //checks the contents of the string, returns type of token
    private static TOKEN toToken(String s) {
        String[] operator = {"+", "-", "*", "/", "%"};
        String[] keywords = {"display", "if", "then", "else"};


        if (s.equals("let")) {
            return (new LET(s));
        } else if (isInArray(keywords, s)) {
            return (new KEYWORD(s));
        } else if (s.equals("=")) {
            return (new ASSIGN(s));
        } else if (isInArray(operator, s)) {
            return (new OPERATOR(s));
        } else if (isNumber(s)) {
            return (new VARIABLE(s));
        } else {
            return (new IDENTIFIER(s));
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
        ////TODO: Check first element and compare with valid commands
        ////TODO: other validity checks in the code text
        for (String s : code) {
            String[] str = s.split(" ");      //split line into elements split by whitespace

////TODO: Check first element and compare with valid commands
            //checks every element in the line
            int commandCount = 0;
            ////TODO: other validity checks in the code text
            for (String string : str) {
                //running checks for each element in line
                for (String name : validCommandName) {
                    //checking if there are multiple named commands in the same line
                    if (name.strip().equalsIgnoreCase(string)) {
                        commandCount++;
                    }
                }
                if (commandCount > 1) {
                    return false;
                }     //returns false if any multiple commands in line
////TODO: other validity checks in the code text
            }

        }

        return true;
    }


    //
    public static void runCode(String[][] code) {
        //

    }


    ///checks for element in array
    private static boolean isInArray(String[] array, String lookup) {
        //
        for (String el : array) {
            if (el.equals(lookup)) {
                return true;
            }
        }
        return false;
    }

    /// returns true if it is a number, false if letter
    private static boolean isNumber(String lookup) {
        //int num = 0;
        for (int i = 0; i < lookup.length(); i++) {
            if (!(Character.isDigit(lookup.charAt(i)))) {
                return false;
            }
        }
        return true;
    }


    // getText():
    // - reads off data from file
    // - searches for "MiniLang_Program.txt"
    // - tries to read
    // - if able to: add line to ArrayList, convert ArrayList to Array
    // returns Array of lines
    private static String[] getText(String fileName) throws IOException {
        //

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int num = 0;
            ArrayList<String> lines = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    num++;
                    lines.add(line);
                }
            }
            String[] arr = new String[num];
            for (int i = 0; i < num; i++) {
                arr[i] = lines.get(i);
            }
            return arr;

        } catch (IOException e) {
            throw new FileNotFoundException(e.getMessage());
        }

    }

}
