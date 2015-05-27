/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logappend;

import cmd.CMDDataReader;
import cmd.CMDProcessor;
import output.Error;
import output.Success;

/**
 *
 * @author antonio
 */
public class Logappend {

    /**
     * @param args the command line arguments
     */
    //-B C:/Users/antonio/app/test/task.txt
    //-K 123 -T 100 -A -G zaira C:\Users\antonio\app\test\log.txt
    public static void main(String[] args) {

        CMDDataReader parameter = new CMDDataReader();

        if (parameter.parse(args)) {
            String pathBatch = parameter.getDataStructure().getBatch();
            if (pathBatch == null) {
                CMDProcessor cmdProcessor = new CMDProcessor();
                cmdProcessor.process(parameter.getDataStructure());
            } else {
                Error.silent = true;
                BatchFileProcessor processoFile = new BatchFileProcessor();
                processoFile.process(pathBatch);
            }
        } else {
            Error.show(255, "invalid");
        }
        Success.show(0);
    }

}
