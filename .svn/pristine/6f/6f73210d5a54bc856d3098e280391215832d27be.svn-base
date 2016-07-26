package org.mangosoft.leagoovn.smarthub.utils;

import com.orhanobut.logger.Logger;

import org.mangosoft.leagoovn.smarthub.BuildConfig;

import java.util.Objects;

/**
 * Created by thanghx on 4/1/16.
 */
public class Log {
    /**
     * Log debug message with optional parameters
     *
     * @param message String message
     * @param args    parameters (Optional)
     */
    public static void d(String message, Object... args) {
        if (BuildConfig.DEBUG)
            Logger.d(message, args);
    }

    /**
     * Log error message with optional parameters
     *
     * @param message String error message
     * @param args    optional arguments
     */
    public static void e(String message, Object... args) {
        if (BuildConfig.DEBUG) Logger.e(message, args);
    }

    /**
     * Log error message with a throwable cause and optional parameters
     *
     * @param t       Throwable cause
     * @param message String error message
     * @param args    optional arguments
     */
    public static void e(Throwable t, String message, Object... args) {
        if (BuildConfig.DEBUG) Logger.e(t, message, args);
    }

    /**
     * Log warning message with optional parameters
     *
     * @param message Warning message
     * @param args    optional arguments
     */
    public static void w(String message, Object... args) {
        if (BuildConfig.DEBUG) Logger.w(message, args);
    }

    /**
     * Log verbose message with optional parameters
     *
     * @param message verbose message
     * @param args    optional arguments
     */
    public static void v(String message, Objects... args) {
        if (BuildConfig.DEBUG) Logger.v(message, args);
    }

    /**
     * Log out json String in friendly formatted
     *
     * @param json Json String
     */
    public static void json(String json) {
        if (BuildConfig.DEBUG) Logger.json(json);

    }

    public static void json(Object obj) {
        if (BuildConfig.DEBUG && obj != null) try {
            Logger.json(JsonHelper.toJson(obj));
        } catch (Exception ex) {
        }
    }

    /**
     * Log xml String in friendly formatted
     *
     * @param xml xml String
     */
    public static void xml(String xml) {
        if (BuildConfig.DEBUG) Logger.xml(xml);
    }

}
