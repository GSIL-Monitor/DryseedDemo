package com.dryseed.dryseedapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

import com.dryseed.dryseedapp.MyApplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetWorkUtil {

    /**
     * 检测网络是否连接
     */
    public static boolean isNetConnected() {

        ConnectivityManager cm = (ConnectivityManager) MyApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Network[] networks = cm.getAllNetworks();
                if (networks != null) {
                    NetworkInfo networkInfo;
                    for (Network mNetwork : networks) {
                        networkInfo = cm.getNetworkInfo(mNetwork);
                        if (networkInfo != null && networkInfo.isConnected()) {
                            return true;
                        }
                    }
                }
            } else {
                NetworkInfo[] infos = cm.getAllNetworkInfo();
                if (infos != null) {
                    for (NetworkInfo ni : infos) {
                        if (ni != null && ni.isConnected()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * 检测wifi是否连接
     */
    public static boolean isWifiConnected() {
        ConnectivityManager cm = (ConnectivityManager) MyApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测3G是否连接
     */
    public static boolean is3gConnected() {
        ConnectivityManager cm = (ConnectivityManager) MyApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return true;
            }
        }
        return false;
    }

    public static int getConnectionType() {
        ConnectivityManager cm = (ConnectivityManager) MyApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null) {
                try {
                    switch (networkInfo.getType()) {
                        case ConnectivityManager.TYPE_MOBILE:
                            return 1;
                        case ConnectivityManager.TYPE_WIFI:
                            return 2;
                        case ConnectivityManager.TYPE_WIMAX:
                            return 3;
                        case ConnectivityManager.TYPE_ETHERNET:
                            return 4;
                        case ConnectivityManager.TYPE_BLUETOOTH:
                            return 5;

                    }
                } catch (Throwable e) {
                    Log.d("MMM", "error : " + e);

                }
            }

        }
        return 0;
    }

    /**
     * 判断网址是否有效
     */
    public static boolean isLinkAvailable(String link) {
        Pattern pattern = Pattern.compile("^(http://|https://)?((?:[A-Za-z0-9]+-[A-Za-z0-9]+|[A-Za-z0-9]+)\\.)+([A-Za-z]+)[/\\?\\:]?.*$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(link);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }


}
