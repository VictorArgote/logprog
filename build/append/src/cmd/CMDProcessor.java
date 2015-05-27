/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd;

import scheme.Data;
import scheme.LogRecord;
import security.Secret;
import Log.LogCache;
import Log.LogIntegrity;
import Log.LogWrite;
import logappend.BatchFileProcessor;
import output.Error;

/**
 *
 * @author antonio
 */
 // entrada: Lista de comandos
// salida: ejecucion de comandos (escribir archivo y notificaar el resultado) 
public class CMDProcessor {

    public void process(CMDDataStructure commander) {

        Error.count = 0;
        Secret secret = new Secret();
        LogIntegrity logIntegrity = new LogIntegrity(secret);
        LogCache logCache = new LogCache(logIntegrity);
        CMDMapping mappingCommand = new CMDMapping();
        LogRecord recordData = new LogRecord();

        logCache.generate(commander.getPath(), commander.getKey());

        Data data = mappingCommand.process(commander);
        recordData.setData(data);
        recordData.setHash(secret.generateHashString(data.encode(commander.getKey())));

        if (!logIntegrity.checkWrite(recordData)) {
            Error.show(255, "invalid");
        }

        if (Error.count == 0) {
            LogWrite logWrite = new LogWrite();
            logWrite.writeCache(recordData, commander.getPath(), commander.getKey());
        }
    }

}
