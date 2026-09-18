import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        //Calls getText function. Stores array into programText
        String[] programText = getText();

        //checking array
        for (int i = 0; i < programText.length; i++) {
            System.out.println(programText[i]);
        }
    }

    //


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
                sb.append(line).append("");
            }
        }
        br.close();
        return sb.toString().split(";");
    }


}
