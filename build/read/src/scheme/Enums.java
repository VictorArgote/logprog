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

    public enum Roles {

        /*
         ADMIN(
         "admin"),
         GUEST("guest");

         private final String name;

         private Roles(String s) {
         name = s;
         }

         public boolean equalsName(String otherName) {
         return (otherName == null) ? false : name.equals(otherName);
         }

         public String toString() {
         return name;
         }
         */
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
