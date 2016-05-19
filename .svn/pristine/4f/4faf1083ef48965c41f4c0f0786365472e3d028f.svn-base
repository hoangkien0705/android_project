package org.mangosoft.leagoovn.smarthub.utils;

import com.fasterxml.jackson.jr.ob.JSON;

import java.io.IOException;
import java.util.List;

/**
 * Created by thanghx on 4/1/16.
 */
public class JsonHelper {
    public static <T> T toObject(Class<T> tClass, String jsonString) throws IOException {
        return JSON.std.beanFrom(tClass, jsonString);
    }

    public static String toJson(Object obj) throws IOException {
        return JSON.std.asString(obj);
    }

    public static <T> T[] toObjectArray(Class<T> tClass, String jsonString) throws IOException {
        return JSON.std.arrayOfFrom(tClass, jsonString);
    }

    public static <T> List<T> toObjectList(Class<T> tClass, String jsonArrayString) throws IOException {
        return JSON.std.listOfFrom(tClass, jsonArrayString);
    }
}
