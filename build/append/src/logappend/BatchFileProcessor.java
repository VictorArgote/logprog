/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logappend;

import cmd.CMDDataStructure;
import cmd.CMDProcessor;
import java.util.List;
import security.Secret;

/**
 *
 * @author antonio
 */
//Etrada: path task
// salida: resultado del procesamiento del archivo task y escritura log
public class BatchFileProcessor {

    

    public void process(String path) {
        BatchFileReader batchFileReader = new BatchFileReader();
        List<String> tasks = batchFileReader.load(path);

        ArgStringParser commader = new ArgStringParser();
        List<CMDDataStructure> commanderList = commader.loadCommanders(tasks);

        CMDProcessor processor = new CMDProcessor(new Secret());
        commanderList.stream().forEach((commander) -> {
            processor.process(commander);
        }); //Success.show(0);
    }

}
