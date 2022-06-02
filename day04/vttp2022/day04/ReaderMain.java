package vttp2022.day04;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ReaderMain {

    // java ReaderMain text_file
    public static void main(String[] args) 
            throws FileNotFoundException, IOException {

        String srcFile = args[0];

        Reader reader = new FileReader(srcFile);
        BufferedReader br = new BufferedReader(reader);

        String line = "";
        int lineNum = 0;

        while (line != null) {
            line = br.readLine();
            lineNum++;
            System.out.printf("%d: %s\n", lineNum, line.toUpperCase());
        }

        reader.close();
    }
    
}
