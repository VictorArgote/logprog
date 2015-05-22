/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheme;

import security.Decrypt;
import security.Encrypt;
import output.Error;

/**
 *
 * @author antonio
 */
public class Data {

    private String rol;
    private String user;
    private int room;
    private int timestamp;
    private boolean arrive;

    public Data() {
        this.rol = "";
        this.user = "";
        this.room = -2;
        this.timestamp = -2;
        this.arrive = false;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public boolean isArrive() {
        return arrive;
    }

    public void setArrive(boolean arrive) {
        this.arrive = arrive;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String encode(String key) {
        String data = "";
        try {
            data = this.rol + "|" + this.user + "|" + this.arrive + "|" + this.room + "|" + this.timestamp;
            Encrypt encrypt = new Encrypt();
            String encodeString = encrypt.encryption(data, key);
            return encodeString;
        } catch (Exception e) {
            Error.show(255, "integrity violation");
        }
        return data;
    }

    public void decode(String query, String key) {
        try {
            Decrypt decrypt = new Decrypt();
            String decodeString = decrypt.decryption(query, key);
            String vec[] = decodeString.split("\\|");
            this.rol = vec[0];
            this.user = vec[1];
            this.arrive = vec[2].compareTo("true") == 0;

            this.room = Integer.parseInt(vec[3]);
            this.timestamp = Integer.parseInt(vec[4]);

        } catch (Exception ex) {
            Error.show(255, "integrity violation");
        }
    }

}
