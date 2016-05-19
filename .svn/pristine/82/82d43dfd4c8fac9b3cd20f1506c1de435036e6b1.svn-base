package org.mangosoft.leagoovn.smarthub;

import android.app.Application;
import android.os.Environment;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.orhanobut.logger.Logger;

import org.mangosoft.leagoovn.smarthub.components.BitmapCache;
import org.mangosoft.leagoovn.smarthub.utils.Constants;
import org.mangosoft.leagoovn.smarthub.utils.Log;

import java.io.File;

public class LeagooApplication extends Application {
    public static final String LOG_TAG = LeagooApplication.class.getSimpleName();
    private static LeagooApplication mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public static synchronized LeagooApplication getInstance() {
        return mInstance;
    }

    /*
        public static void initImageLoader(Context context) {
            File cacheDir;
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
                cacheDir = new File(Environment.getExternalStorageDirectory(), "LeagooCache");
            else
                cacheDir = context.getCacheDir();
        }
    */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue, new BitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> request, String tag) {
        request.setTag(TextUtils.isEmpty(tag) ? LOG_TAG : tag);
        getRequestQueue().add(request);
    }

    public <T> void addToRequestQueue(Request<T> request) {
        request.setTag(LOG_TAG);
        getRequestQueue().add(request);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    @Override
    public void unregisterActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.unregisterActivityLifecycleCallbacks(callback);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        if (BuildConfig.DEBUG) {
            Logger.init();
        }
        // Make data dir if not exists
        try {
            File dir = new File(Environment.getExternalStorageDirectory(), Constants.PATH_SETTING);
            if (!dir.exists()) dir.mkdirs();
            File f = new File(dir, Constants.FILE_SETTING);
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (Exception ex) {
            Log.e(ex, "Cannot create application data directory/file");
        }
    }
}
