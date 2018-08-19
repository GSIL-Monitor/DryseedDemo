package com.dryseed.dryseedapp.uiautomator;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.util.Log;

import com.dryseed.dryseedapp.MyJunitRunner;
import com.dryseed.dryseedapp.gfxinfo.DefaultRunEngine;

import junit.framework.TestCase;

/**
 * @author caiminming
 * <p>
 * adb shell am instrument -w -r -e debug false -e loopCount 1 -e procName "com.dryseed.dryseedapp" -e class 'com.dryseed.dryseedapp.uiautomator.UITest#testA' com.dryseed.dryseedapp.test/com.dryseed.dryseedapp.MyJunitRunner
 */
public class UITest extends TestCase {

    private DefaultRunEngine defaultRunEngine;
    private Context mContext;
    private UiDevice mUiDevice;

    public void testA() throws UiObjectNotFoundException {
        Log.d("MMM", "testA()");

        //获取设备对象
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        mUiDevice = UiDevice.getInstance(instrumentation);

        //获取Context
        mContext = instrumentation.getContext();

        //启动测试APP
        Intent intent = mContext.getPackageManager().getLaunchIntentForPackage("com.dryseed.dryseedapp");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mContext.startActivity(intent);

        /*String resourceId = "com.dryseed.dryseedapp.widget.popupWindow.PopupWindowActivity:id/CollapsingToolbarLayout";
        UiObject collapsingToolbarLayout = uiDevice.findObject(new UiSelector().resourceId(resourceId));
        collapsingToolbarLayout.click();*/

        try {
            //wait 1s
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //启动gxfinfo捕获线程
        defaultRunEngine = new DefaultRunEngine();
        defaultRunEngine.start();

        String processName = MyJunitRunner.getTargetProcessName();
        int loopCount = MyJunitRunner.getLoopCount();
        Log.d("MMM", String.format("[processName:%s][loopCount:%d]", processName, loopCount));

        try {
            for (int i = 0; i < loopCount; i++) {
                // 向上移动
                mUiDevice.swipe(mUiDevice.getDisplayWidth() / 2, mUiDevice.getDisplayHeight() - 1,
                        mUiDevice.getDisplayWidth() / 2, mUiDevice.getDisplayHeight() / 2, 100);

                defaultRunEngine.setIsScrolling(true);
                Thread.sleep(2000);//进入app wait 2s
                defaultRunEngine.setIsScrolling(false);
                Thread.sleep(2000);//wait 2s,保证平均值计算

                // 向下移动
                mUiDevice.swipe(mUiDevice.getDisplayWidth() / 2, mUiDevice.getDisplayHeight() / 2,
                        mUiDevice.getDisplayWidth() / 2, mUiDevice.getDisplayHeight() - 1, 100);

                defaultRunEngine.setIsScrolling(true);
                Thread.sleep(2000);//进入app wait 2s
                defaultRunEngine.setIsScrolling(false);
                Thread.sleep(2000);//wait 2s,保证平均值计算
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
