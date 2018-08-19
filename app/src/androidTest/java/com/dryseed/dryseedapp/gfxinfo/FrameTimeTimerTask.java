package com.dryseed.dryseedapp.gfxinfo;

import android.util.Log;

import com.dryseed.dryseedapp.MyJunitRunner;
import com.dryseed.dryseedapp.shell.Shell;

import java.io.IOException;
import java.util.Arrays;
import java.util.TimerTask;

public class FrameTimeTimerTask extends TimerTask {

    private int testCount = 0;

    private Shell rootShell;

    @Override
    public void run() {
        if (rootShell == null) {
            rootShell = new Shell.Builder().setRoot(true).build();
        }
        try {
            String result = rootShell.execCommands(Arrays.asList("dumpsys gfxinfo \"" +
                    MyJunitRunner.getTargetProcessName() + "\" reset", "dumpsys gfxinfo \"" +
                    MyJunitRunner.getTargetProcessName() + "\""));
            Log.d("MMM", result);
            /*Map<String, Float> resultMap = OutDataManager.computeResultData(result);
            if (resultMap == null || OutDataManager.isStopCollect) {
                return;
            }
            OutDataManager.appendSplit();
            for (Map.Entry<String, Float> entry : resultMap.entrySet()) {
                OutDataManager.appendResultData(entry.getKey(), entry.getValue());
            }*/
            testCount += 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重置每次循环的计数器
     */
    public void reset() {
        //OutDataManager.appendAvgData(testCount);
        testCount = 0;
    }
}
