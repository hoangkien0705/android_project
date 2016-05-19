package org.mangosoft.leagoovn.smarthub.utils;

/**
 * Created by kienhv on 3/31/2016.
 */
public class Constants {
    public static final String NEW_LINE = System.getProperty("line.separator");
    public static final String FILE_SETTING = "leagoo_warranty.txt";
    public static final String SHARED_PREFERENCE = "system_data";
    public static final String INTENT_FILTER_HTC_QUICKBOOT_POWER_ON = "android.intent.action.QUICKBOOT_POWERON";
    public static final String INTENT_FILTER_SMS_DELIVERED = "org.mangosoft.leagoovn.smarthub.SMS_DELIVERED";
    public static final String INTENT_FILTER_SMS_SENT = "org.mangosoft.leagoovn.smarthub.SMS_SENT";
    public static final int LIMIT_POWER_CYCLE_COUNT = 4;
    public static final String PATH_SETTING = "LeagooData";
    public static final String FILE_SETTING_COMMENT = "Leagoo Warranty Service Settings";
    public static final String SMS_ACTIVATION_FORMAT = "KHBH LEAGOO %s %s";
    public static final String SMS_QUERY_FORMAT = "TCBH LEAGOO %s %s";
    public static final String SHORT_CODE = "0913505583";
    public static final int SHORT_CODE_FEE = 2000; //VND
    public static final CharSequence SMS_KEYWORD_BAO_HANH = "bao hanh";
    public static final CharSequence SMS_KEYWORD_KICH_HOAT = "kich hoat";
    public static final CharSequence SMS_KEYWORD_LEAGOO = "leagoo";
    public static final CharSequence SMS_KEYWORD_SUCCESS = "da kich hoat bao hanh";
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss z";
    public static final String SMS_BODY = "warrantyActivationSmsBody";
    public static final long WARRANTY_DUE_DATE = 20;
    public static final String LOG_TAG = "kienhv";

    // Network

    public static final String ROOT_URL = "http://192.168.1.18:8080/leagoo/public";
    public static final String URL_PRODUCT = ROOT_URL + "/rest/content/product";
    public static final String URL_STORE_AGENCY = ROOT_URL + "/rest/content/storeAgency";
    public static final String URL_PROMOTION = ROOT_URL + "/rest/content/promotion";

    public static final String NONCE = "n";
    public static final String TIMESTAMPS = "t";
    public static final String OPERATION = "o";
}
