package com.dryseed.dryseedapp.application;

import android.os.StrictMode;

import com.dryseed.dryseedapp.BuildConfig;
import com.dryseed.dryseedapp.practice.crash.CrashHandler;
import com.dryseed.timecost.TimeCostCanary;
import com.dryseed.timecost.TimeCostConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.luojilab.component.basiclib.utils.BackForegroundWatcher;
import com.luojilab.component.basiclib.utils.CustomLogCatStrategy;
import com.luojilab.component.basiclib.utils.LogUtil;
import com.luojilab.component.componentlib.router.Router;
import com.luojilab.component.componentlib.router.ui.UIRouter;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;

import java.util.HashMap;

import zlc.season.rxdownload3.core.DownloadConfig;
import zlc.season.rxdownload3.extension.ApkInstallExtension;
import zlc.season.rxdownload3.extension.ApkOpenExtension;

/**
 * @author caiminming
 */
class MainApplication extends BaseApplicationProxy {
    @Override
    public void initWithoutPermission() {
        super.initWithoutPermission();

        initComponent();
        Fresco.initialize(mContext);
        LeakCanary.install(mContext);
        initX5();
        initRxDownload();
        BackForegroundWatcher.getInstance().init(MyApplication.getInstance());
        Stetho.initializeWithDefaults(mContext);
        //initARouter();
        initCrashHandler();
        //initStrictMode();

        //TimeCost耗时统计
        initTimeCost();

        //Logger初始化
        initLogger();
    }

    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(5)        // (Optional) Hides internal method calls up to offset. Default 5
                .logStrategy(new CustomLogCatStrategy()) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag(LogUtil.TAG)   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }

    /**
     * 组件初始化
     */
    private void initComponent() {
        //App组件Router初始化
        UIRouter.getInstance().registerUI("app");
        UIRouter.getInstance().registerUI("aac");
        UIRouter.getInstance().registerUI("wheelpicker");
        UIRouter.getInstance().registerUI("blur");

        //如果isRegisterCompoAuto为false，则需要通过反射加载组件
        Router.registerComponent("com.luck.picture.applike.PictureselectorAppLike");
        Router.registerComponent("cn.qqtheme.framework.applike.WheelpickerAppLike");
        Router.registerComponent("com.dryseed.aaccomponent.applike.AacComponentAppLike");
        Router.registerComponent("com.dryseed.blurcomponent.applike.BlurComponentAppLike");
    }

    /**
     * 初始化腾讯x5内核
     * https://x5.tencent.com/tbs/sdk.html
     */
    private void initX5() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
            }

            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                //Log.d("MMM", " onViewInitFinished is " + arg0);
            }
        };
        //x5内核初始化接口
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_PRIVATE_CLASSLOADER, true);
        QbSdk.initTbsSettings(map);
        QbSdk.initX5Environment(mContext, cb);
    }

    /**
     * RxDownload初始化
     */
    private void initRxDownload() {
        DownloadConfig.Builder builder = DownloadConfig.Builder.Companion.create(mContext)
                //.enableDb(true)
                //.setDbActor(new CustomSqliteActor(this))
                .enableService(true)
                .enableNotification(true)
                .addExtension(ApkInstallExtension.class)
                .addExtension(ApkOpenExtension.class);

        DownloadConfig.INSTANCE.init(builder);
    }

    /**
     * CrashHandler初始化
     */
    private void initCrashHandler() {
        if (BuildConfig.DEBUG) {
            CrashHandler.getInstance(mContext).init();
        }
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
     * 严格模式初始化
     */
    private void initStrictMode() {
        if (BuildConfig.DEBUG) {
            //线程策略
            StrictMode.setThreadPolicy(
                    new StrictMode.ThreadPolicy.Builder()
                            .detectDiskReads() //检测是否存在磁盘读操作
                            .detectDiskWrites()//检测是否存在磁盘写读操作
                            .detectNetwork()//检测是否存在网络操作
                            .penaltyLog()//表示在Logcat中打印日志
                            .build()
            );

            //虚拟机策略
            StrictMode.setVmPolicy(
                    new StrictMode.VmPolicy.Builder()
                            .detectActivityLeaks()//检测是否存在Activity泄露
                            .detectLeakedSqlLiteObjects()//检测是否存在Sqlite对象泄露
                            .detectLeakedClosableObjects()//检测是否存在未关闭的Closable对象泄露
                            .penaltyLog()
                            .build()
            );
        }
    }

    /**
     * TimeCost初始化
     */
    private void initTimeCost() {
        TimeCostCanary.install(mContext).config(
                new TimeCostConfig.Builder()
                        .setExceedMilliTime(200L)
                        .setThreadExceedMilliTime(200L)
                        .setMonitorOnlyMainThread(true)
                        .setShowDetailUI(true)
                        .build()
        );
    }
}
