package com.dryseed.dryseedapp.practice.checkEmulator;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.practice.checkEmulator.lib.EmuCheckUtil;
import com.dryseed.dryseedapp.utils.ToastUtil;

/**
 * 参考了https://github.com/happylishang/CacheEmulatorChecker
 * 只保留了简易的测试方法。Native层的检测方法没有引入，如有需要，可以扩展。
 */
public class CheckEmulatorActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isEmulator = EmuCheckUtil.mayOnEmulator(this);
        ToastUtil.showToast("是否为模拟器：" + isEmulator);
    }
}
