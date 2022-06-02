package vttp2022.day04.workshop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main {

    public static void main(String[] args) 
            throws IOException {

        String dataFile = args[0];

        ExamReporter reporter = new ExamReporter();

        Reader r = new FileReader(dataFile);
        BufferedReader br = new BufferedReader(r);

        // Remove the first line
        String data = br.readLine();

        while (null != data) {
            data = br.readLine();
            reporter.read(data);
        }

        reporter.generateReport();
    }
}