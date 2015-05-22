/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd;

import com.beust.jcommander.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author antonio
 */
public class CMDDataStructure {

    @Parameter(names = {"-E"}, description = "Admin")
    private String admin;

    @Parameter(names = {"-G"}, description = "Guest")
    private String guest;

    @Parameter(names = {"-K"}, description = "Key  encryption")
    private String key;

    @Parameter(names = {"-B"}, description = "Batch processing")
    private String batch;

    @Parameter(names = {"-R"}, description = "Room")
    private int room = -1;
    
    @Parameter(names = {"-T"}, description = "Timestamp")
    private int timestamp = -2;

    @Parameter(names = {"-A"}, description = "Arrive")
    private boolean arrive = false;

    @Parameter(names = {"-L"}, description = "Arrive")
    private boolean leave = false;

    @Parameter(description = "Log file")
    private final List<String> paths = new ArrayList<>();

    public void setPath(String path) {
        this.path = path;
    }

    private String path;

    public List<String> getPaths() {
        return paths;
    }

    public int getRoom() {
        return room;
    }

    public int setRoom(int room) {
        return this.room = room;
    }

    public boolean getLeave() {
        return leave;
    }

    public boolean getArrive() {
        return arrive;
    }

    public String getKey() {
        return key;
    }

    public String getBatch() {
        return batch;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getPath() {
        return path;
    }

    public String getAdmin() {
        return admin;
    }

    public String getGuest() {
        return guest;
    }

}
