/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Log;

import java.util.Comparator;
import scheme.LogRecord;

/**
 *
 * @author antonio
 */
public class LogQuery {

    public LogQuery() {
    }

    public int getPosition(String user, String rol) {

        LogRecord current;
        int currentRoom;
        try {
            current = LogCache.cache.stream()
                    .filter(x -> x.getData().getUser().compareTo(user) == 0
                            && x.getData().getRol().compareTo(rol) == 0)
                    .max(Comparator.comparing(time -> time.getData().getTimestamp())).get();
            currentRoom = current.getData().getRoom();
        } catch (Exception e) {
            return -2;
        }

        if (current.getData().isArrive() == false) {
            if (currentRoom >= 0) {
                currentRoom = -1;
            } else {
                if (currentRoom == -1) {
                    currentRoom = -2;
                }
            }
        }

        return currentRoom;
    }

    public boolean checkRolUser(String user, String rol) {

        long count = LogCache.cache.stream().filter(x
                -> x.getData().getUser().compareTo(user) == 0
                && x.getData().getRol().compareTo(rol) != 0
        ).count();

        return count <= 0;
    }

}
