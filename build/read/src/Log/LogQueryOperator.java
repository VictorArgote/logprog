/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Log;

import java.util.Comparator;
import scheme.LogRecord;

/**
 *
 * @author antonio
 */
public class LogQueryOperator {

    static Comparator<LogRecord> ordeByTime = (e1, e2) -> Integer.compare(
            e1.getData().getTimestamp(), e2.getData().getTimestamp());

}
