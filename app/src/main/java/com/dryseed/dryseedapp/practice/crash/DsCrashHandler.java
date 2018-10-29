package com.dryseed.dryseedapp.practice.crash;

import android.content.Context;

import com.luojilab.component.basiclib.BaseApplication;
import com.luojilab.component.basiclib.utils.LogUtil;

import java.lang.Thread.UncaughtExceptionHandler;

import static com.luojilab.component.basiclib.utils.AppConfig.DEBUG;


/**
 * Created by suxq on 2018/9/12.
 * <p>
 * Crash 处理，将 crash 信息以及设备信息写入 crash 文件，默认在 sd/{包名}/crash.log 文件中
 * 如果 sd 不可用，那么会写到 /data/data/{包名}/files/crash.log
 * <p>
 * 另外，支持以下操作：
 *
 * @see #getCrashLogs() 获取 crash 的文本信息，多个 crash 信息以数组分开
 * @see #clearCrashLogFile() 清空 crash 文件
 * @see #getCrashFilePath() 获取 crash 文件的路径
 * @see #setOnCrashListener(OnCrashListener) 扩展接口
 */

public class DsCrashHandler implements UncaughtExceptionHandler {
    private static final String TAG = "DsCrashHandler";

    private static DsCrashHandler sInstance = new DsCrashHandler();

    private UncaughtExceptionHandler mDefaultHandler;
    private OnCrashListener mOnCrashListener;
    private CrashHandlerHelper mHandlerHelper;
    private boolean mIsInit = false;

    private DsCrashHandler() {

    }

    public static DsCrashHandler getInstance() {
        return sInstance;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        mIsInit = true;
        mHandlerHelper = new CrashHandlerHelper(context);
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        LogUtil.e("uncaughtException");
        if (ex == null) {
            LogUtil.e("ex == null");
            if (mDefaultHandler != null) {
                mDefaultHandler.uncaughtException(thread, ex);
            } else {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        } else {
            ex.printStackTrace();
            StringBuffer sb = mHandlerHelper.throwable2StringBuffer(ex);
            mHandlerHelper.saveCrashInfo2File(sb);
            LogUtil.e("uncaughtException " + sb.toString() + "\n " + ex);

            if (DEBUG) {
                CrashActivity.start(BaseApplication.getInstance(), sb.toString());
            }

            if (mOnCrashListener != null) {
                mOnCrashListener.onCrash(ex);
            }
            if (mDefaultHandler != null) {
                mDefaultHandler.uncaughtException(thread, ex);
            }
        }

    }

    /**
     * 获取 crash 的文本信息，多个 crash 信息以数组分开
     *
     * @return
     */
    public String[] getCrashLogs() {
        checkInit();
        return mHandlerHelper.getCrashLogs();
    }

    /**
     * 清空 crash 文件
     */
    public void clearCrashLogFile() {
        checkInit();
        mHandlerHelper.clearCrashLogFile();
    }

    /**
     * 获取 crash 文件的路径
     *
     * @return
     */
    public String getCrashFilePath() {
        checkInit();
        return mHandlerHelper.mLogPathDir + mHandlerHelper.mCrashFileName;
    }

    public void setOnCrashListener(OnCrashListener listener) {
        mOnCrashListener = listener;
    }

    private void checkInit() {
        if (!mIsInit) {
            throw new UnsupportedOperationException("u should call init() before use CrashHandle's function...");
        }
    }

}