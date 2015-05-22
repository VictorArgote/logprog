/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logread;

import cmd.CMDDataReader;
import cmd.CMDProcessor;
import output.Error;
/**
 *
 * @author antonio
 */
public class Logread {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        CMDDataReader parameter = new CMDDataReader();

        if (parameter.parse(args)) {
                CMDProcessor cmdProcessor = new CMDProcessor();
                cmdProcessor.process(parameter.getDataStructure());
        } else {
            Error.show(255, "invalid");
        }
    }

}
