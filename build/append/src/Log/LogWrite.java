/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Log;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import output.Error;
import scheme.LogRecord;
import utils.FileUtil;
import utils.ValidateFile;

/**
 *
 * @author antonio
 */
public class LogWrite {

    public void writeCache(LogRecord record, String path, String key) {
        ValidateFile validateFile = new ValidateFile();
        if (validateFile.checkFile(path)) {
            writeCache(record, validateFile.getFile(), key);
        } else {
            Error.show(255, "invalid");
        }
    }

    public void writeCache(LogRecord record, File file, String key) {

        boolean error = false;
        FileWriter fw = null;
        PrintWriter write = null;

        try {
            fw = new FileWriter(file, true);
            write = new PrintWriter(fw);
            write.println(record.encode(key));
        } catch (Exception ex) {
            error = true;
        } finally {
            FileUtil.closeBuffer(write);
            FileUtil.closeBuffer(fw);
        }

        if (error) {
            Error.show(255, "invalid");
        }

    }

}
