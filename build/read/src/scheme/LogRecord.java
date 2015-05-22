/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheme;

import output.Error;

/**
 *
 * @author antonio
 */
public class LogRecord {
    
    private Data data;
    private String hash;
    
    public LogRecord() {
        this.data = new Data();
        this.hash = "";
    }
    
    public Data getData() {
        return data;
    }
    
    public void setData(Data data) {
        this.data = data;
    }
    
    public String getHash() {
        return hash;
    }
    
    public void setHash(String hash) {
        this.hash = hash;
    }
    
      public String encode(String key) {
        return this.data.encode(key) + "." + this.hash;
    }

    public void decode(String query, String key) {
        try {
            String vec[] = query.split("\\.");
            this.data.decode(vec[0], key);
            this.hash = vec[1];
        } catch (Exception ex) {
            Error.show(255, "integrity violation");
        }
    }
    
}
