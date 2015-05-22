/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheme;

/**
 *
 * @author antonio
 */
public class Enums {

    public static enum roles {

        ADMIN {
                    public String toString() {
                        return "admin";
                    }
                },
        GUEST {
                    public String toString() {
                        return "guest";
                    }
                }
    }

}
