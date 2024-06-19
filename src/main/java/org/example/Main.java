package org.example;

import java.io.*;
import java.nio.*;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0 ) {
            System.err.println("No argument for cat is given.");
            return;
        } else if (args[0].equals("-")) {
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
            scanner.close();
            return;
        }
        String fileName = args[0];
        Path filePath = Paths.get(fileName);

        try {
            List<String> fileContents = Files.readAllLines(filePath);
            for (String fileLine: fileContents) {
                System.out.println(fileLine);
            }
        } catch (IOException e) {
            System.err.println("An IOException occurred: " + e.getMessage());
        }

    }
}