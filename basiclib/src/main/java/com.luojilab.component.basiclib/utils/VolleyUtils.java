package com.luojilab.component.basiclib.utils;

import android.text.TextUtils;

import com.luojilab.component.basiclib.volley.Request;
import com.luojilab.component.basiclib.volley.RequestQueue;
import com.luojilab.component.basiclib.volley.VolleyLog;
import com.luojilab.component.basiclib.volley.toolbox.Volley;
import com.luojilab.component.basiclib.BaseApplication;

/**
 * Created by caiminming on 2016/11/24.
 */
public class VolleyUtils {
    /**
     * Log or request TAG
     */
    public static final String TAG = "VolleyPatterns";

    /**
     * Global request queue for Volley
     */
    private static RequestQueue mRequestQueue;

    /**
     * @return The Volley Request queue, the queue will be created if it is null
     */
    public static RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(BaseApplication.getInstance().getApplicationContext());
        }

        return mRequestQueue;
    }

    /**
     * Adds the specified request to the global queue, if tag is specified
     * then it is used else Default TAG is used.
     *
     * @param req
     * @param tag
     */
    public static <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

        VolleyLog.d("Adding request to queue: %s", req.getUrl());

        getRequestQueue().add(req);
    }

    /**
     * Adds the specified request to the global queue using the Default TAG.
     *
     * @param req
     */
    public static <T> void addToRequestQueue(Request<T> req) {
        // set the default tag if tag is empty
        req.setTag(TAG);

        getRequestQueue().add(req);
    }

    /**
     * Cancels all pending requests by the specified TAG, it is important
     * to specify a TAG so that the pending/ongoing requests can be cancelled.
     *
     * @param tag
     */
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
