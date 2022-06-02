package vttp2022.day04.workshop;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ExamReporter {

    private Map<String, List<List<Integer>>> dataSet = new HashMap<>(); 

    public void read(String data) {

        if ((null == data) || (data.trim().length() <= 0))
            return;

        String[] fields = data.split(",");
        String group = removeQuotes(fields[1]);
        String mathScore = removeQuotes(fields[5]);
        String readScore = removeQuotes(fields[6]);
        String writeScore = removeQuotes(fields[7]);

        //System.out.printf("%s: %s, %s, %s\n", group, mathScore, readScore, writeScore);

        addToReport(group, toInt(mathScore), toInt(readScore), toInt(writeScore)); 
    }

    public void generateReport() {
        for (String group: dataSet.keySet()) {
            List<List<Integer>> entry = dataSet.get(group);
            float mathAvg = calculateAvg(entry.get(0));
            float readAvg = calculateAvg(entry.get(1));
            float writeAvg = calculateAvg(entry.get(2));
            System.out.printf("Group: %s\n", group.toUpperCase());
            System.out.printf("\tMath: %.3f\n", mathAvg);
            System.out.printf("\tReading: %.3f\n", readAvg);
            System.out.printf("\tWriting: %.3f\n\n", writeAvg);
        }
    }

    private float calculateAvg(List<Integer> dataPt) {
        float total = 0f;
        for (int i = 0; i < dataPt.size(); i++) {
            total += dataPt.get(i);
        }
        return total / dataPt.size();
    }

    private void addToReport(String group, int math, int read, int write) {
        if (!dataSet.containsKey(group)) {
            dataSet.put(group, mkEntry());
        }
        List<List<Integer>> entry = dataSet.get(group);
        entry.get(0).add(math);
        entry.get(1).add(read);
        entry.get(2).add(write);
    }

    private List<List<Integer>> mkEntry() {
        List<List<Integer>> catList = new LinkedList<>();
        // 0 - math, 1 - read, 2 -write
        for (int i = 0; i < 3; i++)
            catList.add(new LinkedList<Integer>());
        return catList;
    }

    private Integer toInt(String i) {
        return Integer.parseInt(i);
    }

    private String removeQuotes(String s) {
        return s.replaceAll("\"", "");
    }
}
