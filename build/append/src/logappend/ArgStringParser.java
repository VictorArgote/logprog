/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logappend;

import cmd.CMDDataStructure;
import java.util.ArrayList;
import java.util.List;
import cmd.CMDDataReader;

/**
 *
 * @author antonio
 */
  // input:  lista de argumentos
//  output: lista comandos 
public class ArgStringParser {

    public List<CMDDataStructure> loadCommanders(List<String> taskList) {

        List<CMDDataStructure> commanderList = new ArrayList<>();

        taskList.stream().forEach((task) -> {
            CMDDataReader parser = new CMDDataReader();
            if (parser.parse(task.split("\\s+"))) {
                commanderList.add(parser.getDataStructure());
            }
        });
        return commanderList;
    }

}
