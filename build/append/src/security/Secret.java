/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import output.Error;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author antonio
 */
public class Secret {

    protected Cipher cipher;

    public Secret() {
        try {
            this.cipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
            Error.show(255, "invalid");
        }
    }

    public String generateHashString(String value) {
        SecretKeySpec secretKey = generateHash(value);
        if (secretKey != null) {
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } else {
            return "";
        }
    }

    public SecretKeySpec generateHash(String value) {
        SecretKeySpec secretKeySpec = null;
        try {
            byte[] key = (value).getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);

            secretKeySpec = new SecretKeySpec(key, "AES");
            return secretKeySpec;

        } catch (Exception ex) {
            Error.show(255, "invalid");
        }
        return secretKeySpec;
    }

    public boolean checkIntegrity(String hash, SecretKeySpec key, String text) {
        SecretKeySpec keyUser = this.generateHash(text);
        return keyUser.equals(key);
    }
}
