/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author antonio
 */
public class StringUtil {

    public static String joinDelimiter(List<String> list, String delimiter) {

        String join = "";
        for (String item : list) {
            join = join + item + delimiter;
        }
        join = join.replaceAll(delimiter + "$", "");

        return join;
    }

    public static String orderDelimiter(String text, String delimiter) {

        if (!text.contains(delimiter)) {
            return text;
        }

        String[] vec = text.split(delimiter);
        List<String> elements = new ArrayList<>(Arrays.asList(vec));
        Collections.sort(elements);
        return joinDelimiter(elements, delimiter);
    }

}
