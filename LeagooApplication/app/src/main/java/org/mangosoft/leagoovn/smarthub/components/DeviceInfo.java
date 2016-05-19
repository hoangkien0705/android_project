package org.mangosoft.leagoovn.smarthub.components;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import org.mangosoft.leagoovn.smarthub.utils.Log;

import java.lang.reflect.Method;

/**
 * Created by thanghx on 4/6/16.
 */
public class DeviceInfo {
    public static String getDeviceModel() {
        return Build.MODEL;
    }

    public static String getFirmwareRevision() {
        return String.format("$s (Android %s)", android.os.Build.DISPLAY, Build.VERSION.RELEASE);
    }

    public static String getDisplayDensity(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return String.format("%d dpi", metrics.densityDpi);
    }

    public static String getDisplayResolution(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return String.format("%d pixels x %d pixels", metrics.heightPixels, metrics.widthPixels);
    }

    public static String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getImei1(Context context) {
        TelephonyInfo t = TelephonyInfo.getInstance(context);
        return t.getImeiSIM1();
    }

    public static String getImei2(Context context) {
        TelephonyInfo t = TelephonyInfo.getInstance(context);
        return t.getImeiSIM2();
    }

    static class TelephonyInfo {
        private static TelephonyInfo telephonyInfo;
        private String imeiSIM1;
        private String imeiSIM2;
        private boolean isSIM1Ready;
        private boolean isSIM2Ready;

        private TelephonyInfo() {
        }

        static TelephonyInfo getInstance(Context context) {
            if (telephonyInfo == null) {
                telephonyInfo = new TelephonyInfo();
                TelephonyManager telephonyManager = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
                telephonyInfo.imeiSIM1 = telephonyManager.getDeviceId();
                telephonyInfo.imeiSIM2 = null;
                try {
                    telephonyInfo.imeiSIM1 = getDeviceIdBySlot(context, "getDeviceIdGemini", 0);
                    telephonyInfo.imeiSIM2 = getDeviceIdBySlot(context, "getDeviceIdGemini", 1);
                } catch (NoSuchMethodException e) {
                    try {
                        telephonyInfo.imeiSIM1 = getDeviceIdBySlot(context, "getDeviceId", 0);
                        telephonyInfo.imeiSIM2 = getDeviceIdBySlot(context, "getDeviceId", 1);
                    } catch (NoSuchMethodException e1) {
                        try {
                            telephonyInfo.imeiSIM1 = getDeviceIdBySlot(context, "getDeviceIdDs", 0);
                            telephonyInfo.imeiSIM2 = getDeviceIdBySlot(context, "getDeviceIdDs", 1);
                        } catch (NoSuchMethodException e2) {
                            try {
                                telephonyInfo.imeiSIM1 = getDeviceIdBySlot(context, "getSimSerialNumberGemini", 0);
                                telephonyInfo.imeiSIM2 = getDeviceIdBySlot(context, "getSimSerialNumberGemini", 1);
                            } catch (NoSuchMethodException e3) {
                                Log.e(e2, "Could not get IMEI.");
                                printTelephonyManagerMethodNamesForThisDevice(context);
                            }
                        }
                    }
                }
                telephonyInfo.isSIM1Ready = telephonyManager.getSimState() == TelephonyManager.SIM_STATE_READY;
                telephonyInfo.isSIM2Ready = false;
                try {
                    telephonyInfo.isSIM1Ready = getSIMStateBySlot(context, "getSimStateGemini", 0);
                    telephonyInfo.isSIM2Ready = getSIMStateBySlot(context, "getSimStateGemini", 1);
                } catch (NoSuchMethodException e) {
                    try {
                        telephonyInfo.isSIM1Ready = getSIMStateBySlot(context, "getSimState", 0);
                        telephonyInfo.isSIM2Ready = getSIMStateBySlot(context, "getSimState", 1);
                    } catch (NoSuchMethodException e1) {
                        Log.e(e1, "Could not get multiple sim state");
                        printTelephonyManagerMethodNamesForThisDevice(context);
                    }
                }
            }
            return telephonyInfo;
        }

        static String getDeviceIdBySlot(Context context, String predictedMethodName, int slotID) throws NoSuchMethodException {
            String imei = null;
            TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            try {
                Class<?> telephonyClass = Class.forName(telephony.getClass().getName());
                Class<?>[] parameter = new Class[1];
                parameter[0] = int.class;
                Method getSimID = telephonyClass.getMethod(predictedMethodName, parameter);
                Object[] obParameter = new Object[1];
                obParameter[0] = slotID;
                Object ob_phone = getSimID.invoke(telephony, obParameter);
                if (ob_phone != null) {
                    imei = ob_phone.toString();
                }
            } catch (Exception e) {
                throw new NoSuchMethodException(predictedMethodName);
            }
            return imei;
        }

        static boolean getSIMStateBySlot(Context context, String predictedMethodName, int slotID) throws NoSuchMethodException {
            boolean isReady = false;
            TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            try {
                Class<?> telephonyClass = Class.forName(telephony.getClass().getName());
                Class<?>[] parameter = new Class[1];
                parameter[0] = int.class;
                Method getSimStateGemini = telephonyClass.getMethod(predictedMethodName, parameter);

                Object[] obParameter = new Object[1];
                obParameter[0] = slotID;
                Object ob_phone = getSimStateGemini.invoke(telephony, obParameter);

                if (ob_phone != null) {
                    int simState = Integer.parseInt(ob_phone.toString());
                    if (simState == TelephonyManager.SIM_STATE_READY) {
                        isReady = true;
                    }
                }
            } catch (Exception e) {
                throw new NoSuchMethodException(predictedMethodName);
            }
            return isReady;
        }

        static void printTelephonyManagerMethodNamesForThisDevice(Context context) {
            TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            Class<?> telephonyClass;
            try {
                telephonyClass = Class.forName(telephony.getClass().getName());
                Method[] methods = telephonyClass.getMethods();
                StringBuilder b = new StringBuilder();
                for (int idx = 0; idx < methods.length; idx++) {
                    b.append("\n" + methods[idx] + " declared by " + methods[idx].getDeclaringClass());
                }
                Log.d(b.toString());
            } catch (ClassNotFoundException e) {
                Log.e(e, "No class found");
            }
        }

        String getImeiSIM1() {
            return imeiSIM1;
        }

        String getImeiSIM2() {
            return imeiSIM2;
        }

        boolean isSIM1Ready() {
            return isSIM1Ready;
        }

        boolean isSIM2Ready() {
            return isSIM2Ready;
        }

        boolean isDualSIM() {
            return imeiSIM2 != null;
        }
    }
}
