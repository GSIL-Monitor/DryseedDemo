package com.dryseed.dryseedapp;

import android.content.Context;
import android.os.Process;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.util.Log;

import com.antfortune.freeline.FreelineCore;
import com.blankj.utilcode.util.Utils;
import com.dryseed.dryseedapp.utils.BackForegroundWatcher;
import com.luojilab.component.basiclib.BaseApplication;
import com.luojilab.component.basiclib.DPIUtil;
import com.dryseed.dryseedapp.utils.ProcessUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.luojilab.component.componentlib.router.ui.UIRouter;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;

import java.util.HashMap;

import zlc.season.rxdownload3.core.DownloadConfig;
import zlc.season.rxdownload3.extension.ApkInstallExtension;
import zlc.season.rxdownload3.extension.ApkOpenExtension;


/**
 * Created by caiminming on 2016/11/23.
 */
public class MyApplication extends BaseApplication {
    /**
     * A singleton instance of the application class for easy access in other places
     */
    private static MyApplication sInstance;

    /**
     * @return ApplicationController singleton instance
     */
    public static synchronized MyApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("MMM", " MyApplication onCreate");

        String processName = ProcessUtil.getProcessName(this, Process.myPid());
        if (!TextUtils.isEmpty(processName) && processName.equals("com.dryseed.dryseedapp")) {
            init();
        }
    }

    private void init() {
        Log.d("MMM", " MyApplication init");
        sInstance = this;

        try {
            DPIUtil.setDensity(sInstance.getResources().getDisplayMetrics().density);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        initComponent();
        Utils.init(sInstance);
        Fresco.initialize(sInstance);
        LeakCanary.install(sInstance);
        if (BuildConfig.DEBUG) {
            FreelineCore.init(this);
        }
        FreelineCore.init(sInstance);
        initX5();
        initRxDownload();
        BackForegroundWatcher.getInstance().init(MyApplication.getInstance());
        Stetho.initializeWithDefaults(sInstance);
        //initARouter();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    /**
     * 组件初始化
     */
    private void initComponent() {
        UIRouter.getInstance().registerUI("app");

        //如果isRegisterCompoAuto为false，则需要通过反射加载组件
        //Router.registerComponent("com.luck.picture.applike.PictureselectorAppLike");
        //Router.registerComponent("cn.qqtheme.framework.applike.WheelpickerAppLike");
    }

    /**
     * ARouter初始化
     */
    /*private void initARouter() {
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(sInstance); // 尽可能早，推荐在Application中初始化
    }*/

    /**
     * 初始化腾讯x5内核
     * https://x5.tencent.com/tbs/sdk.html
     */
    private void initX5() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                //Log.d("MMM", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
            }
        };
        //x5内核初始化接口
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_PRIVATE_CLASSLOADER, true);
        QbSdk.initTbsSettings(map);
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    /**
     * RxDownload初始化
     */
    private void initRxDownload() {
        DownloadConfig.Builder builder = DownloadConfig.Builder.Companion.create(this)
                //.enableDb(true)
                //.setDbActor(new CustomSqliteActor(this))
                .enableService(true)
                .enableNotification(true)
                .addExtension(ApkInstallExtension.class)
                .addExtension(ApkOpenExtension.class);

        DownloadConfig.INSTANCE.init(builder);
    }

}
