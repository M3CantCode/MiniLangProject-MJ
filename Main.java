import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        //
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader("MiniLang_Program.txt"));
        System.out.println(br.toString());

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        br.close();

        String[] strings = sb.toString().split(" ");


        for (int j = 0; j < strings.length; j++) {
            System.out.printf("%s ", strings[j]);
        }
    }
}
