/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logappend;

import utils.ValidateFile;
import utils.FileUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import output.Error;

/**
 *
 * @author antonio
 */
//input: path Archivo task
//salida: arreglo cadenas de argumentos
public class BatchFileReader {
    
    private List<String> readTask(File file) {
        
        List<String> taskList = new ArrayList<>();
        String newTask;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            newTask = br.readLine();
            while (newTask != null) {
                taskList.add(newTask.trim());
                newTask = br.readLine();
                
            }
        } catch (Exception ex) {
            Error.show(255, "invalid");
        } finally {
            FileUtil.closeBuffer(br);
        }
        
        return taskList;
    }
    
    public List<String> load(String path) {
        
        ValidateFile file = new ValidateFile();
        if (!file.checkFile(path)) {
            Error.show(255, "invalid");
            return null;
        } else {
            List<String> task = readTask(file.getFile());
            return task;
        }
        
    }
    
}
