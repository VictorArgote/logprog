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

    public static void show(int code) {
        show(code, "");
    }

    public static void show(int code, String message) {
        if (message.compareTo("") != 0) {
            System.out.println(message);
        }
        System.exit(code);

    }

}
