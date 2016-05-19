package org.mangosoft.leagoovn.smarthub.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * Created by thanghx on 4/3/16.
 */
public class AppDataHelper {
    private static final Object lock = new Object();

    public static String readValue(Context ctx, String key) {
        // Try load properties from file
        Properties prop = new Properties();
        try {
            File dir = new File(Environment.getExternalStorageDirectory(), Constants.PATH_SETTING);
            dir.mkdirs();
            File f = new File(dir, Constants.FILE_SETTING);
            if (!f.exists()) {
                f.createNewFile();
            }
            FileInputStream in = new FileInputStream(f);
            prop.load(in);
            in.close();
            return prop.getProperty(key, null);
        } catch (Exception e) {
            // Fallback to shared preferences
            SharedPreferences sp = ctx.getSharedPreferences(Constants.SHARED_PREFERENCE, Context.MODE_PRIVATE);
            return sp.getString(key, null);
        }
    }

    public static void writeValue(Context ctx, String key, Object value) {
        synchronized (lock) {
            Properties prop = new Properties();
            try {
                File dir = new File(Environment.getExternalStorageDirectory(), Constants.PATH_SETTING);
                dir.mkdirs();
                File f = new File(dir, Constants.FILE_SETTING);
                if (!f.exists()) {
                    f.createNewFile();
                }
                FileInputStream in = new FileInputStream(f);
                prop.load(in);
                in.close();
                prop.setProperty(key, String.valueOf(value));
                FileOutputStream out = new FileOutputStream(f);
                prop.store(out, Constants.FILE_SETTING_COMMENT);
                out.close();
            } catch (Exception e) {
                // Fallback to shared preferences
                SharedPreferences sp = ctx.getSharedPreferences(Constants.SHARED_PREFERENCE, Context.MODE_PRIVATE);
                sp.edit().putString(key, String.valueOf(value));
                sp.edit().commit();
            }
        }
    }
}
