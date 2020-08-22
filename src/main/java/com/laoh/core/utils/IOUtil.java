package com.laoh.core.utils;

import java.io.*;

/**
 * @author hyd
 * @date 2020/7/24 10:54
 */
public class IOUtil {
    public static void copy(InputStream input, OutputStream output) {
        byte[] buf = new byte[4096];
        int bytesRead = 0;
        try {
            while ((bytesRead =input.read(buf)) != -1) {
                output.write(buf, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static byte[] readFileToByteArray(String fileName) {
        byte[] bytes = null;
        try(InputStream input = new BufferedInputStream(new FileInputStream(fileName))
        ;ByteArrayOutputStream output = new ByteArrayOutputStream()){
            copy(input, output);
            bytes = output.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
