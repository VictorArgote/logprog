/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package output;

/**
 *
 * @author antonio
 */
public class Error {

    public static String identificar(int code) {
        if (code == -2) {
            return "The command does not pass the integrity test.";
        }

        if (code == -3) {
            return "The log file does not pass the integrity test.";
        }
        if (code == -4) {
            return "Failed to execute the command";
        }

        return "An error occurred during the process.";
    }

    public static void show(int code, boolean exit) {
        System.out.println(identificar(code));
        if (exit) {
            System.exit(-1);
        }
    }

    public static void show(int code) {
        show(code, false);
    }

    public static void show(String message) {
        show(message, false);
    }

    public static void show(String message, boolean exit) {
        System.out.println(message);
        if (exit) {
            System.exit(-1);
        }
    }

    public static void show(int code, String message) {
        System.out.println(message);
        System.exit(code);
    }

}
