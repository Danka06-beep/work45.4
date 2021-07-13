package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        String filename = "C:\\work\\patents.csv";
        Map<Integer, Set<Patent>> patentsMap = parsePatentsData(filename);
        printPatents(patentsMap);
    }


    private static Map<Integer, Set<Patent>> parsePatentsData(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        Map<Integer, Set<Patent>> patentsMap = new TreeMap<>();
        scanner.nextLine();
        while (scanner.hasNext()) {
            String[] data = scanner.nextLine().split(";");
            Patent patent = new Patent(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4]);
            if (patentsMap.get(patent.getYear()) != null) {
                Set<Patent> patentsSet = patentsMap.get(patent.getYear());
                patentsSet.add(patent);
            } else {
                Set<Patent> patentsSet = new HashSet<>();
                patentsSet.add(patent);
                patentsMap.put(patent.getYear(), patentsSet);
            }
        }
        scanner.close();
        return patentsMap;
    }

    private static void printPatents(Map<Integer, Set<Patent>> patentsMap) {
        System.out.println("Каталог патентов");
        for (Map.Entry<Integer, Set<Patent>> entry : patentsMap.entrySet()) {
            System.out.printf("Год: %d\n", entry.getKey());
            for (Patent patent : entry.getValue()) {
                System.out.printf("\t\t%s\n", patent.toString());
            }
        }
    }
}
