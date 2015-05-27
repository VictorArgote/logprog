/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Log;

import cmd.CMDDataStructure;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import output.Error;
import utils.StringUtil;
import scheme.Data;
import scheme.Enums;

/**
 *
 * @author antonio
 */
public class LogQuery {

    public LogQuery() {
    }

    public Data getPosition(String user, String rol) {

        Data current;
        try {
            current = LogCache.cache.stream()
                    .filter(x -> x.getUser().compareTo(user) == 0
                            && x.getRol().compareTo(rol) == 0)
                    .max(Comparator.comparing(time -> time.getTimestamp())).get();
        } catch (Exception e) {
            return null;
        }

        if (current.isArrive() == false) {
            if (current.getRoom() >= 0) {
                current.setRoom(-1);
            } else {
                if (current.getRoom() == -1) {
                    current.setRoom(-2);
                }
            }
        }

        return current;
    }

    public boolean checkRolUser(String user, String rol) {

        long count = LogCache.cache.stream().filter(x
                -> x.getUser().compareTo(user) == 0
                && x.getRol().compareTo(rol) != 0
        ).count();

        return count <= 0;
    }

    public List<String> userSystemForRol(String rol) {

        List<String> userSystem = new ArrayList();
        try {
            List<String> users = LogCache.cache.stream()
                    .filter(x -> x.getRol().compareTo(rol) == 0)
                    .map(m -> m.getUser())
                    .distinct()
                    .collect(Collectors.toList());

            Data data;
            for (String user : users) {
                data = getPosition(user, rol);
                if (data.getRoom() >= -1) {
                    userSystem.add(data.getUser());
                }
            }

        } catch (Exception e) {
            Error.show(4);
        }
        return userSystem;
    }

    public void status() {

        List<Data> usersCurrent = new ArrayList();
        List<String> users = new ArrayList();

        Map<Integer, List<String>> mapRoom = new TreeMap<>();

        String roles[] = {
            Enums.Roles.ADMIN.toString(),
            Enums.Roles.GUEST.toString()
        }; // change

        for (String role : roles) {
            users = userSystemForRol(role);
            for (String user : users) {
                Data userData = getPosition(user, role);
                if (userData != null) {
                    usersCurrent.add(userData);
                }
            }
        }

        String outputAdmin = "";//Enums.Roles.ADMIN.toString() + ": ";
        String outputGuest = "";//Enums.Roles.GUEST.toString() + ": ";

        for (Data data : usersCurrent) {
            if (data.getRoom() >= -1) {

                if (0 == data.getRol().compareTo(Enums.Roles.ADMIN.toString())) {
                    outputAdmin = outputAdmin + data.getUser() + ",";
                } else {
                    if (data.getRol().compareTo(Enums.Roles.GUEST.toString()) == 0) {
                        outputGuest = outputGuest + data.getUser() + ",";
                    }
                }
            }

            if (data.getRoom() >= 0) {
                if (mapRoom.containsKey(data.getRoom())) {
                    List<String> userRoom = (List<String>) mapRoom.get(data.getRoom());
                    userRoom.add(data.getUser());
                    mapRoom.put(data.getRoom(), userRoom);
                } else {
                    List<String> userRoom = new ArrayList<>();
                    userRoom.add(data.getUser());
                    mapRoom.put(data.getRoom(), userRoom);
                }
            }
        }

        outputAdmin = outputAdmin.replaceAll(",$", "");
        outputGuest = outputGuest.replaceAll(",$", "");

        System.out.println(StringUtil.orderDelimiter(outputAdmin, ","));
        System.out.println(StringUtil.orderDelimiter(outputGuest, ","));

        Set s = mapRoom.entrySet();
        Iterator it = s.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            int key = (int) entry.getKey();
            List<String> value = (List<String>) entry.getValue();
            Collections.sort(value);
            System.out.println(String.valueOf(key) + ": " + StringUtil.joinDelimiter(value, ","));
        }
    }

    public void room(String user, String rol) {

        Comparator<Data> byTime = (e1, e2) -> Integer.compare(
                e1.getTimestamp(), e2.getTimestamp());

        List<String> rooms = new ArrayList();
        try {
            rooms = LogCache.cache.stream()
                    .filter(x -> x.getRol().compareTo(rol) == 0
                            && x.getUser().compareTo(user) == 0
                            && x.isArrive()
                            && x.getRoom() > -1 )
                    .sorted(byTime)
                    .map(m -> String.valueOf(m.getRoom()))
                    //.distinct()
                    .collect(Collectors.toList());
            
        } catch (Exception e) {
            Error.show(4);
        }
        //System.out.print("Room visited for " + rol + "-" + user + ": ");
        System.out.println(StringUtil.joinDelimiter(rooms, ","));

    }

    public void execute(CMDDataStructure commander) {

        if (commander.getStatus()) {
            status();
        } else {
            if (commander.getRoom()) {
                if (commander.getAdmin() != null) {
                    room(commander.getAdmin(), Enums.Roles.ADMIN.toString());
                } else {
                    room(commander.getGuest(), Enums.Roles.GUEST.toString());
                }
            }
        }

    }
}
