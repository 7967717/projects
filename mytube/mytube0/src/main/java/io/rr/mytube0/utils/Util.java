package io.rr.mytube0.utils;

import org.apache.commons.lang3.StringEscapeUtils;
import spark.Request;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @author roman on 10/16/16.
 */
public class Util {

    public static Map toEmptyValuesMap(String... keys) {
        Map<String, String> map = new HashMap<>();
        for (String key : keys) {
            map.put(key, "");
        }
        return map;
    }

    public static Map toQueryParamMap(Request request, String... keys) {
        Map<String, String> map = new HashMap<>();
        for (String key : keys) {
            map.put(key, StringEscapeUtils.escapeHtml4(request.queryParams(key)));
        }
        return map;
    }

    public static <String, V> Map.Entry<String, V> entry(String key, V value) {
        return new AbstractMap.SimpleEntry<>(key, value);
    }

}
