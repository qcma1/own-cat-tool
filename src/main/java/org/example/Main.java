package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        -n flag
        boolean numberEveryLine = false;
//        -b flag
        boolean numberNonBlankLinesOnly = false;
        List<String> fileNames = new ArrayList<>();

        for (String arg:args) {
            switch (arg) {
                case "-n":
                    numberEveryLine = true;
                    break;
                case "-b":
                    numberNonBlankLinesOnly = true;
                    break;
                default:
                    fileNames.add(arg);
            }
        }

        if (fileNames.isEmpty() || fileNames.contains("-")) {
            Scanner scanner = new Scanner(System.in);
            if (numberNonBlankLinesOnly) {
                int lineNumber = 1;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.isEmpty()) {
                        System.out.println(line);
                    } else {
                        System.out.printf("%d %s\n", lineNumber++, line);
                    }
                }
            } else if (numberEveryLine){
                int lineNumber = 1;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.printf("%d %s\n", lineNumber++, line);
                }
            } else {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println(line);
                }
            }
            scanner.close();
            return;
        }

        if (numberNonBlankLinesOnly) {
            int lineNumber = 1;
            for (String fileName:fileNames) {
                Path filePath = Paths.get(fileName);
                try {
                    List<String> fileContents = Files.readAllLines(filePath);
                    for (String fileLine : fileContents) {
                        if (fileLine.isEmpty()) {
                            System.out.println(fileLine);
                        } else {
                            System.out.printf("%d %s\n", lineNumber++, fileLine);
                        }
                    }
                } catch (IOException e) {
                    System.err.println("An IOException occurred: " + e.getMessage());
                }
            }
        } else if (numberEveryLine) {
            int lineNumber = 1;
            for (String fileName:fileNames) {
                Path filePath = Paths.get(fileName);
                try {
                    List<String> fileContents = Files.readAllLines(filePath);
                    for (String fileLine : fileContents) {
                        System.out.printf("%d %s\n", lineNumber++, fileLine);
                    }
                } catch (IOException e) {
                    System.err.println("An IOException occurred: " + e.getMessage());
                }
            }
        } else {
            for (String fileName:fileNames) {
                Path filePath = Paths.get(fileName);
                try {
                    List<String> fileContents = Files.readAllLines(filePath);
                    for (String fileLine : fileContents) {
                        System.out.println(fileLine);
                    }
                } catch (IOException e) {
                    System.err.println("An IOException occurred" + e.getMessage());
                }
            }
        }
    }
}