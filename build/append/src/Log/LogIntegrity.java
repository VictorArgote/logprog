/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Log;

import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
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

    public LogIntegrity(Secret secret) {
        this.secret = secret;
        this.logQuery = new LogQuery();
        LogIntegrity.lastTimeStamp = -2;
    }

    public boolean checkMove(LogRecord logRecord) {

        String user = logRecord.getData().getUser();
        String rol = logRecord.getData().getRol();
        int pos = logQuery.getPosition(user, rol);

        int room = logRecord.getData().getRoom();
        boolean arrive = logRecord.getData().isArrive();

        if (pos == -2) {
            return arrive == true && room == -1;
        } else {
            if (pos == -1) {
                if (arrive == true) {
                    return room >= 0;
                }
 
                if (arrive == false) {
                    return room == -1;
                }
            } else {
                if (pos >= 0) {
                    return arrive == false && room >= 0;
                }
            }

        }

        return false;
    }

    public boolean checkLogRecord(LogRecord logRecord, String key) {
        return !(!checkHash(logRecord, key) || !checkLastTimeStamp(logRecord.getData().getTimestamp()));
    }

    public boolean checkHash(LogRecord logRecord, String key) {
        SecretKeySpec keyUser = secret.generateHash(logRecord.getData().encode(key));
        String encodedKey = Base64.getEncoder().encodeToString(keyUser.getEncoded());
        return logRecord.getHash().compareTo(encodedKey) == 0;
    }

    private boolean checkLastTimeStamp(int time) {
        if (time > LogIntegrity.lastTimeStamp) {
            LogIntegrity.lastTimeStamp = time;
            return true;
        } else {
            return false;
        }
    }

    public boolean checkWrite(LogRecord logRecord) {

        return !(!logQuery.checkRolUser(logRecord.getData().getUser(), logRecord.getData().getRol())
                || !checkMove(logRecord)
                || !checkLastTimeStamp(logRecord.getData().getTimestamp()));
    }

}
