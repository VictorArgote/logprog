package utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import output.Error;

/**
 *
 * @author antonio
 */
public class ValidateFile {

    public File getFile() {
        return log;
    }
    private File log = null;

    public boolean check(String path) {

        this.log = new File(path);

        if (!log.exists()) {
            Error.show(255, "invalid");
            return false;
        }

        if (!log.isFile()) {
            Error.show(255, "invalid");
            return false;
        }

        if (!log.canRead()) {
            Error.show(255, "invalid");
            return false;
        }

        return true;
    }

}
