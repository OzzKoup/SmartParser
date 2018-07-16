package com.parser;

import com.parser.service.SmartParser;
import com.parser.service.Writer;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        try {
            SmartParser smartParser = new SmartParser(args[0], args[1]);
            Writer writer = new Writer();
            writer.writeToFile(smartParser.outputResult());
        } catch (IOException e) {
            throw new RuntimeException("Error reading data");
        }
    }
}
