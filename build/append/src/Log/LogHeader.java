/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Log;

import java.io.RandomAccessFile;
import output.Error;
import security.Decrypt;
import security.Encrypt;
import utils.FileUtil;

/**
 *
 * @author antonio
 */
public class LogHeader {

    private static final String mask = "1D23ASD9D1SAF0SDFDS1";
    //private static final String mask = "1323ASDSAFSDFDS1D2SFS2FDS5434SD435X4SD35435SD4F354SDF343SDFF35354SDF35354FSDD35434SDF354SDF354S5D3F35SD4F53435SDF4D35SDFSDFSF51W";

    public boolean check(String log, String key) {

        //BufferedReader br = null;
        boolean result = false;
        String header;
        RandomAccessFile br = null;
        try {
            // br = new BufferedReader(new FileReader(log));
            br = new RandomAccessFile(log, "rw");
            header = br.readLine();
            if (header == null) {
                result = createHeader(br, key);
            } else {
                result = validate(header, key);
            }

        } catch (Exception ex) {
            Error.show(255, "integrity violation");
        } finally {
            FileUtil.closeBuffer(br);
        }
        return result;
    }

    private boolean createHeader(RandomAccessFile br, String key) {
        try {
            Encrypt encrypt = new Encrypt();
            String header = encrypt.encryption(mask, key);
            String endLine = System.lineSeparator();
            br.writeBytes(header + endLine);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private boolean validate(String data, String key) {
        Decrypt decrypt = new Decrypt();
        String header = decrypt.decryption(data, key);
        return mask.compareTo(header) == 0;
    }

}
