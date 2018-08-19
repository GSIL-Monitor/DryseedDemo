package com.dryseed.dryseedapp.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StreamUtils {

    public static void writeToFile(String input, File file) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(input);
        closeSilently(bufferedWriter);
    }

    public static String readFromFile(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            builder.append(line).append('\n');
        }
        closeSilently(bufferedReader);
        return builder.toString();
    }

    public static void closeSilently(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
