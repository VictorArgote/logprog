/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd;

import scheme.Data;
import scheme.Enums;

/**
 *
 * @author antonio
 */


//Entrada: Comando
//Salida:  Objeto Data
public class CMDMapping {

    public CMDMapping() {
    }

    public Data process(CMDDataStructure commander) {

        Data data = new Data();

        if (commander.getAdmin() != null) {
            data.setUser(commander.getAdmin());
            data.setRol(Enums.roles.ADMIN.toString());
        } else {
            if (commander.getGuest()!= null) {
                data.setUser(commander.getGuest());
               data.setRol(Enums.roles.GUEST.toString());
            }
        }

          data.setArrive(commander.getArrive());
          data.setRoom(commander.getRoom());
          data.setTimestamp(commander.getTimestamp());

        return data;
    }

}
