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
public class Success {

    public static String identificar(int core) {
        return "Successful operation";
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

    public static void show(String message, boolean exit) {
        System.out.println(message);
        if (exit) {
            System.exit(0);
        }
    }

    public static void show(String message) {
        show(message, false);
    }

}
