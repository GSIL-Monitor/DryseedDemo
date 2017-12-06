package com.dryseed.dryseedapp.plugin.classloaderHook.classloaderHook;

import dalvik.system.DexClassLoader;

/**
 * 自定义的ClassLoader, 用于加载"插件"的资源和代码
 * @author weishu
 * @date 16/3/29
 */
public class CustomClassLoader extends DexClassLoader {

    public CustomClassLoader(String dexPath, String optimizedDirectory, String libraryPath, ClassLoader parent) {
        super(dexPath, optimizedDirectory, libraryPath, parent);
    }
}
