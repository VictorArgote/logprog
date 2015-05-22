/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd;

import output.Error;

/**
 *
 * @author antonio
 */
public class CMDValidate {

    private boolean valideEG(CMDDataStructure commander) {

        String name = "";
        if (commander.getAdmin() != null) {
            name = commander.getAdmin();
        } else if (commander.getGuest() != null) {
            name = commander.getGuest();
        }

        if (name.compareTo("") == 0) {
            //Error.show("You must enter the value of E or G");
            Error.show(255, "invalid");
            return false;
        }

        if (commander.getAdmin() != null && commander.getGuest() != null) {
            // Error.show("You can not specify the parameters E and G at the same time");
            Error.show(255, "invalid");
            return false;
        }

        if (!name.matches("[A-Za-z]+")) {
            // Error.show("The values of E and G can only be letters");
            Error.show(255, "invalid");
            return false;
        }

        return true;
    }

    private boolean valideT(CMDDataStructure commander) {

        if (commander.getTimestamp() == -2) {
            //Error.show("The parameter T is required");
            Error.show(255, "invalid");
            return false;
        }

        return true;
    }

    private boolean valideR(CMDDataStructure commander) {

        if (commander.getRoom() < -1 || commander.getRoom() > 1073741823) {
            // Error.show("The value must be greater than or equal to -1");
            Error.show(255, "invalid");
            return false;
        }

        return true;

    }

    private boolean validePath(CMDDataStructure commander) {

        if (commander.getBatch() == null) {
            if (commander.getPath() == null) {
                // Error.show("The path log is required");
                Error.show(255, "invalid");
                return false;
            }
        }

        return true;
    }

    private boolean valideK(CMDDataStructure commander) {

        if (commander.getKey() == null) {
            //Error.show("The parameter K is required");
            Error.show(255, "invalid");
            return false;
        }
        return true;
    }

    private boolean valideAL(CMDDataStructure commander) {

        if (commander.getArrive() == false && commander.getLeave() == false) {
            // Error.show("You must enter the value of A or L");
            Error.show(255, "invalid");
            return false;
        }

        if (commander.getArrive() == true && commander.getLeave() == true) {
            Error.show(255, "invalid");
            // Error.show("You can not specify the parameters A and L at the same time");
            return false;
        }

        return true;
    }

    public boolean check(CMDDataStructure commander) {

        if (commander.getBatch() == null) {

            if (!valideK(commander)) {
                return false;
            }

            if (!valideT(commander)) {
                return false;
            }

            if (!validePath(commander)) {
                return false;
            }

            if (!valideEG(commander)) {
                return false;
            }

            if (!valideAL(commander)) {
                return false;
            }

            if (!valideR(commander)) {
                return false;
            }

        }

        return true;

    }

}
