/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd;

import Log.LogCache;
import Log.LogQuery;

/**
 *
 * @author antonio
 */
 
public class CMDProcessor {
    
    public void process(CMDDataStructure commander) {
        
        LogCache logCache = new LogCache();
        logCache.generate(commander.getPath(),commander.getKey());
        
        LogQuery logQuery = new LogQuery();
        logQuery.execute(commander);        
    }
    
}
