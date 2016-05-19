package org.mangosoft.leagoovn.smarthub.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class JsonUtils {
    public static String getUrlRequest(String url, Object object) throws Exception {
        String json = JsonHelper.toJson(object);
        Map<String, String> map = new HashMap<String, String>();
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(json, new TypeReference<Map<String, String>>() {});
        return serializeUrl(url, map);
    }

    /**
     *
     * @param url
     * @param params
     * @return
     */
    public static String serializeUrl(String url, Map<String, String> params){
        for (int i = 0; i < params.size(); i++) {
            if (i==0) {
                url = new StringBuilder(url).append("?")
                        .append(params.keySet().toArray()[0]).append("=")
                        .append(params.get(params.keySet().toArray()[0]))
                        .toString();
            } else {
                url = new StringBuilder(url).append("&")
                        .append(params.keySet().toArray()[i]).append("=")
                        .append(params.get(params.keySet().toArray()[i]))
                        .toString();
            }
        }
        return url;
    }
}
