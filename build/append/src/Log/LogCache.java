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
import output.Error;

/**
 *
 * @author antonio
 */
public class LogCache {

    public static List<LogRecord> cache;
    private final LogIntegrity logIntegrity;

    public LogCache(LogIntegrity logIntegrity) {
        LogCache.cache = new ArrayList();
        this.logIntegrity = logIntegrity;
    }

    public void generate(String path, String key) {

        LogCache.cache.clear();
        LogHeader logheader = new LogHeader();
        if (!logheader.check(path, key)) {
            Error.show(255, "invalid");
        }

        List<String> logs = readLog(path);
        load(logs, key);

    }

    private void load(List<String> logs, String key) {

        LogCache.cache.clear();
        for (String log : logs) {

            LogRecord logRecord = new LogRecord();
            logRecord.decode(log, key);
            if (!logIntegrity.checkLogRecord(logRecord, key)) {
                Error.show(255, "integrity violation");
            }

            LogCache.cache.add(logRecord);
        }

    }

    private List<String> readLog(String path) {

        List<String> records = new ArrayList();
        BufferedReader br = null;
        String record;

        try {
            br = new BufferedReader(new FileReader(path));

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
