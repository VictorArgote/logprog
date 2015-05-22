/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import java.security.InvalidKeyException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import output.Error;

/**
 *
 * @author antonio
 */
public class Encrypt extends Secret {

    public String encryption(String text, String key) {
        return encryption(text, this.generateHash(key));
    }

    public String encryption(String text, SecretKeySpec key) {
        String textEncryption = "";
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(text.getBytes());
            textEncryption = Base64.getEncoder().encodeToString(encrypted);
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Error.show(255, "invalid");
        }
        return textEncryption;
    }

}
