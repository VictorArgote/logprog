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

    @Parameter(names = {"-K"}, description = "Key  encryption")
    private String key;

    @Parameter(names = {"-S"}, description = "Status")
    private boolean status=false;

    @Parameter(names = {"-R"}, description = "Room")
    private boolean room=false;

    @Parameter(names = {"-T"}, description = "Timepend")
    private boolean timepend = false;

    @Parameter(names = {"-E"}, description = "Admin")
    private String admin;

    @Parameter(names = {"-G"}, description = "Guest")
    private String guest;

    @Parameter(description = "Log file")
    private final List<String> paths = new ArrayList<>();

    public void setPath(String path) {
        this.path = path;
    }

    public boolean getStatus() {
        return status;
    }

    public boolean getTimepend() {
        return timepend;
    }

    private String path;

    public List<String> getPaths() {
        return paths;
    }

    public boolean getRoom() {
        return room;
    }

    public String getKey() {
        return key;
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
