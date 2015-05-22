/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Log;

import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
import scheme.Data;
import security.Secret;
import scheme.LogRecord;

/**
 *
 * @author antonio
 */
public class LogIntegrity {

    private final Secret secret;
    public static int lastTimeStamp;
    private final LogQuery logQuery;

    public LogIntegrity() {
        this.secret = new Secret();
        this.logQuery = new LogQuery();
        LogIntegrity.lastTimeStamp = -2;
    }

    public boolean checkMove(Data data) {

        String user = data.getUser();
        String rol = data.getRol();
        Data current = logQuery.getPosition(user, rol);

        int room = data.getRoom();
        boolean arrive = data.isArrive();

        if (current == null) {
            return arrive == true && room == 0;
        } else {
            int pos = current.getRoom();

            //pos -1
            if (pos == -1) {
                return arrive == true && room == 0;
            }

            //pos =0
            if (pos == 0) {
                if (arrive == true) {
                    return room > 0;
                } else {
                    if (arrive == false) {
                        return room == -1;
                    }
                }
            }

            //pos > 0
            if (pos > 0) {
                return arrive == false && room == 0;
            }
        }

        return false;
    }

    private boolean checkLastTimeStamp(int time) {
        if (time > LogIntegrity.lastTimeStamp) {
            LogIntegrity.lastTimeStamp = time;
            return true;
        } else {
            return false;
        }
    }

    public boolean checkLogRecord(LogRecord logRecord, String key) {
        return !(!checkHash(logRecord, key) || !checkLastTimeStamp(logRecord.getData().getTimestamp()));
    }

    public boolean checkHash(LogRecord logRecord, String key) {
        SecretKeySpec keyUser = secret.generateHash(logRecord.getData().encode(key));
        String encodedKey = Base64.getEncoder().encodeToString(keyUser.getEncoded());
        return logRecord.getHash().compareTo(encodedKey) == 0;
    }

    public boolean checkWrite(Data data) {
        return !(!logQuery.checkRolUser(data.getUser(), data.getRol())
                || !checkMove(data)
                || !checkLastTimeStamp(data.getTimestamp()));
    }

}
