/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Log; 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import scheme.LogRecord;
import utils.FileUtil;
import utils.ValidateFile;
import output.Error;
import scheme.Data;

/**
 *
 * @author antonio
 */
public class LogCache {

    public static List<Data> cache;
    private final LogIntegrity logIntegrity;

    public LogCache() {
        LogCache.cache = new ArrayList();
        this.logIntegrity = new LogIntegrity();
    }

    public void generate(String path, String key) {

        ValidateFile validateFile = new ValidateFile();
        if (!validateFile.check(path)) {
            Error.show(255, "invalid");
        }

        LogHeader logheader = new LogHeader();
        if (!logheader.check(path, key)) {
            Error.show(255, "invalid");
        }

        List<String> logs = readLog(validateFile.getFile());
        load(logs, key);

    }

    private void load(List<String> logs, String key) {

        LogCache.cache.clear();
        logs.stream().map((log) -> {
            LogRecord logRecord = new LogRecord();
            logRecord.decode(log, key);
            return logRecord;
        }).map((logRecord) -> {
            if (!logIntegrity.checkLogRecord(logRecord, key)) {
                Error.show(255, "integrity violation");
            }
            return logRecord;
        }).forEach((logRecord) -> {
            LogCache.cache.add(logRecord.getData());
        });

    }

    private List<String> readLog(File file) {

        List<String> records = new ArrayList();
        BufferedReader br = null;
        String record;

        try {
            br = new BufferedReader(new FileReader(file));

            /* Ignore header */
            record = br.readLine();
            if (record == null) {
                return records;
            }
            /* end ignore*/

            record = br.readLine();
            while (record != null) {
                records.add(record);
                record = br.readLine();
            }
        } catch (Exception ex) {
            Error.show(255, "integrity violation");
        } finally {
            FileUtil.closeBuffer(br);
        }

        return records;
    }

}
