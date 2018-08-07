package com.dryseed.dryseedapp.uiautomator;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;

import junit.framework.TestCase;

/**
 * @author caiminming
 */
public class UITest extends TestCase {
    public void testA() throws UiObjectNotFoundException {
        //获取设备对象
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        UiDevice uiDevice = UiDevice.getInstance(instrumentation);

        //获取Context
        Context context = instrumentation.getContext();

        //启动测试APP
        Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.dryseed.dryseedapp");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        /*String resourceId = "com.dryseed.dryseedapp.widget.popupWindow.PopupWindowActivity:id/CollapsingToolbarLayout";
        UiObject collapsingToolbarLayout = uiDevice.findObject(new UiSelector().resourceId(resourceId));
        collapsingToolbarLayout.click();*/

        try {
            Thread.sleep(1000);//wait 1s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 5; i++) {
            // 向上移动
            uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() - 1,
                    uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, 100);

            // 向下移动
            uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2,
                    uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() - 1, 100);
        }
    }
}
