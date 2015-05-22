package utils;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author antonio
 */
public class FileUtil {

    public static void closeBuffer(RandomAccessFile buffer) {
        try {
            if (buffer != null) {
                buffer.close();
            }
        } catch (Exception e) {
        }
    }

    public static void closeBuffer(FileWriter buffer) {
        try {
            if (buffer != null) {
                buffer.close();
            }
        } catch (Exception e) {
        }
    }

    public static void closeBuffer(PrintWriter buffer) {
        try {
            if (buffer != null) {
                buffer.close();
            }
        } catch (Exception e) {
        }
    }

    public static void closeBuffer(BufferedReader buffer) {
        try {
            if (buffer != null) {
                buffer.close();
            }
        } catch (Exception e) {
        }
    }
}
