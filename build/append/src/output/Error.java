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

    public static boolean silent = false;
    public static int count;

    public static void show(int code) {
        show(code, "");
    }

    public static void show(int code, String message) {
        if (silent == false) {
            System.out.println(message);
            System.exit(code);
        } else {
            count++;
        }
    }
}
