package com.parser.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    public void writeToFile(String string) {
        File file = new File(System.getProperty("user.home") + "\\SmartAnalyzerResult.txt");
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
