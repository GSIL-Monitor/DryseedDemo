package com.dryseed.dryseedapp.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dryseed.dryseedapp.MyApplication;
import com.dryseed.dryseedapp.R;

public class ToastUtil {
    private static Toast mToastInstance;
    private static boolean isShow;

    private synchronized static Toast createToast2(Context context) {
        if (null == mToastInstance) {
            mToastInstance = new Toast(context);
        }
        return mToastInstance;
    }

    public static void cancelToast() {
        if (isShow && mToastInstance != null) {
            isShow = false;
            mToastInstance.cancel();
        }
    }

    public static void showToast(String content) {
        if (content != null && content.length() > 500) return;
        toastBuild(MyApplication.getInstance(), content, Gravity.CENTER_VERTICAL, 0);
    }

    /**
     * 从中间的位置弹出一个Toast<br/>
     *
     * @param content Toast中的内容
     */
    public static void showToast(Context context, String content) {
        if (content != null && content.length() > 100) return;
        toastBuild(context, content, Gravity.CENTER_VERTICAL, 0);
    }

    /**
     * 使用ApplicationContext弹出toast
     *
     * @param content
     * @param gravity
     * @param yOffset
     */
    public static void toastBuild(Context context, String content, int gravity, int yOffset) {
        toastBuild(context, content, gravity, 0, yOffset, R.layout.app_toast_error);
    }


    /**
     * 使用指定的context弹出toast
     *
     * @param content
     * @param gravity
     * @param yOffset
     */
    public static void toastBuild(Context context, String content, int gravity, int xOffset, int yOffset, int resId) {
        if (TextUtils.isEmpty(content)) {
            return;
        }

        LayoutInflater inflate = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View toastView = inflate.inflate(resId, null);
        if (toastView.findViewById(R.id.message) == null) {
            toastView = inflate.inflate(R.layout.app_toast_error, null);
        }

        TextView tv = (TextView) toastView.findViewById(R.id.message);
        tv.setText(content);
        createToast2(context.getApplicationContext());
        mToastInstance.setDuration(Toast.LENGTH_SHORT);
        mToastInstance.setView(toastView);
        //控制展示的位置和y轴方向上的偏移量
        mToastInstance.setGravity(gravity, xOffset, yOffset);
        mToastInstance.show();
        isShow = true;
    }

}


