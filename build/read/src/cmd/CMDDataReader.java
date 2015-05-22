/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd;

import com.beust.jcommander.JCommander;
import output.Error;

/**
 *
 * @author antonio
 */
// Entrada: string de argumentos
// Salida: comando
public class CMDDataReader {

    private CMDDataStructure dataStructure;
    private CMDValidate valide;

    public CMDDataStructure getDataStructure() {
        return dataStructure;
    }

    public CMDDataReader() {
        this.dataStructure = new CMDDataStructure();
        this.valide = new CMDValidate();
    }

    public boolean parse(String[] vec) {
        if (read(vec)) {
            return valide.check(this.dataStructure);
        } else {
            return false;
        }
    }

    private boolean read(String[] vec) {

        try {
            new JCommander(this.dataStructure, vec);
                if (this.getDataStructure().getPaths().size() > 0) {
                    this.getDataStructure().setPath(this.getDataStructure().getPaths().get(0));
                } else {
                       Error.show(255, "invalid");
                    return false;
                }
            
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
