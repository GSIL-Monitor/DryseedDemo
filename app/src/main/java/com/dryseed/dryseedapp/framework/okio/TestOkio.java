package com.dryseed.dryseedapp.framework.okio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

public class TestOkio {
    public static void main(String args[]) {
        String srcFilePath = "app/src/main/java/com/dryseed/dryseedapp/framework/okio/test.txt";
        File srcFile = new File(srcFilePath);
        String destFilePath = "app/src/main/java/com/dryseed/dryseedapp/framework/okio/test2.txt";
        File destFile = new File(destFilePath);

        //readFile(srcFile);
        //writeFile(destFile);
        copyFile(srcFile, destFile);
    }

    private static void writeFile(File destFile) {
        try {
            if (!destFile.exists()) {
                destFile.createNewFile();
            }
            BufferedSink bufferedSink = Okio.buffer(Okio.sink(destFile));
            bufferedSink.writeString("dryseed中国", Charset.forName("UTF-8"));
            bufferedSink.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFile(File srcFile) {
        try {
            BufferedSource bufferedSource = Okio.buffer(Okio.source(srcFile));
            String read = bufferedSource.readString(Charset.forName("UTF-8"));
            System.out.println(read);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyFile(File srcFile, File destFile) {
        try {
            BufferedSource bufferedSource = Okio.buffer(Okio.source(srcFile));
            BufferedSink bufferedSink = Okio.buffer(Okio.sink(destFile));
            bufferedSink.writeAll(bufferedSource);
            bufferedSink.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
