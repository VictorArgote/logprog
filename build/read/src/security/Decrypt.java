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
public class Decrypt extends Secret {

    public String decryption(String text, String key) {
        return decryption(text, this.generateHash(key));
    }

    public String decryption(String text, SecretKeySpec key) {

        String textDecrypted = "";
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            textDecrypted = new String(cipher.doFinal(Base64.getDecoder().decode(text)));
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Error.show(255, "invalid");
        }
        return textDecrypted;
    }
}
