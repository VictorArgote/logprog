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

    private boolean valideEGR(CMDDataStructure commander) {

        String name = "";
        if (commander.getAdmin() != null) {
            name = commander.getAdmin();
        } else if (commander.getGuest() != null) {
            name = commander.getGuest();
        }

        if (commander.getAdmin() != null && commander.getGuest() != null) {
            Error.show(255, "invalid");
            return false;
        }

        if (!name.matches("[A-Za-z]+") && name.compareTo("") != 0) {
           Error.show(255, "invalid");
            return false;
        }

        if ((name.compareTo("") != 0 && !commander.getRoom())
                || (name.compareTo("") == 0 && commander.getRoom())) {
            Error.show(255, "invalid");
            return false;
        }

        return true;
    }

    private boolean validePath(CMDDataStructure commander) {

        if (commander.getPath() == null) {
            Error.show(255, "invalid");
            return false;

        }

        return true;
    }

    private boolean valideK(CMDDataStructure commander) {

        if (commander.getKey() == null) {
            Error.show(255, "invalid");
            return false;
        }
        return true;
    }

    private boolean validaRS(CMDDataStructure commander) {
        if (commander.getStatus() && (commander.getAdmin() != null || commander.getGuest() != null)) {
            Error.show(255, "invalid");
            return false;
        } else {
            return true;
        }
    }

    public boolean check(CMDDataStructure commander) {

        if (!valideK(commander)) {
            return false;
        }

        if (!valideEGR(commander)) {
            return false;
        }

        if (!validaRS(commander)) {
            return false;
        }

        if (!validePath(commander)) {
            return false;
        }

        return true;

    }

}
