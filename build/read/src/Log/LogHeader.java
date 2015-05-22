/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Log;

import java.io.RandomAccessFile;
import security.Decrypt;
import utils.FileUtil;

/**
 *
 * @author antonio
 */
public class LogHeader {

    private static final String mask = "1D23ASD9D1SAF0SDFDS1";

    public boolean check(String log, String key) {

        boolean result = false;
        String header;
        RandomAccessFile br = null;
        try {

            br = new RandomAccessFile(log, "r");
            header = br.readLine();
            if (header == null) {
                result = false;
            } else {
                result = validate(header, key);
            }

        } catch (Exception ex) {
            output.Error.show(255, "integrity violation");
        } finally {
            FileUtil.closeBuffer(br);
        }
        return result;
    }

    private boolean validate(String data, String key) {
        Decrypt decrypt = new Decrypt();
        String header = decrypt.decryption(data, key);
        return mask.compareTo(header) == 0;
    }

}
