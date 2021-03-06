package com.dryseed.dryseedapp.plugin.dynamicLoadDex1;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Created by caiminming on 2017/7/13.
 */

public class TestDynamicLoadDexActivity extends BaseActivity {
    final public static String TAG = "MMM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_load_dex_layout);
    }

    public void onLoadDexCLick(View v) {
        if (copyFileFromAssetsToSd(this, "plugin-dex.dex", Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "plugin-dex.dex")) {
            Log.e(TAG, "成功复制dex到SD卡");

            final File optimizedDexOutputPath = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "plugin-dex.dex");
            Log.e(TAG, Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "plugin-dex.dex"); // /storage/emulated/0/plugin-dex.dex

            File dexOutputDir = this.getDir("dex", 0);
            Log.e(TAG, "dexOutputDir:" + dexOutputDir.getAbsolutePath()); // /data/data/com.dryseed.dryseedapp/app_dex
            DexClassLoader cl = new DexClassLoader(optimizedDexOutputPath.getAbsolutePath(), dexOutputDir.getAbsolutePath(), null, getClassLoader());
            Class mLoadClass = null;

            try {
                mLoadClass = cl.loadClass("plugin.dryseed.plugin.Foo");

                /*
                    public class Foo {
                        public String foo(){
                            return "Loading dex success!";
                        }
                    }
                 */
                Method foo = mLoadClass.getDeclaredMethod("foo");// 获取方法
                foo.setAccessible(true);
                String string = (String) foo.invoke(mLoadClass.newInstance());// 调用方法
                Log.e(TAG, string);

                Toast.makeText(this, string, Toast.LENGTH_LONG).show();

            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }

    }

    public void onLoadApkCLick(View v) {
        if (copyFileFromAssetsToSd(this, "plugin-apk.apk", Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "plugin-apk.apk")) {
            Log.e(TAG, "成功复制apk到SD卡");
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "plugin-apk.apk";
            try {

                File optimizedDirectoryFile = getDir("dex", 0);
                DexClassLoader classLoader = new DexClassLoader(path, optimizedDirectoryFile.getAbsolutePath(), null, getClassLoader());

                /*
                    public class PluginClassloaderHookActivity extends AppCompatActivity {
                        @Override
                        protected void onCreate(Bundle savedInstanceState) {
                            super.onCreate(savedInstanceState);
                            setContentView(R.layout.activity_main);
                            findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    biu(PluginClassloaderHookActivity.this,"Click Button");
                                }
                            });
                        }

                        public void biu(Context context,String msg){
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                        }
                    }
                 */

                // 通过反射机制调用
                Class mLoadClass = classLoader.loadClass("plugin.dryseed.plugin.PluginClassloaderHookActivity");
                Constructor constructor = mLoadClass.getConstructor(new Class[]{});
                Object mainActivity = constructor.newInstance(new Object[]{});

                // 遍历类里所有方法
                Method[] methods = mLoadClass.getDeclaredMethods();
                for (int i = 0; i < methods.length; i++) {
                    Log.e(TAG, methods[i].toString());
                }

                Method method = mLoadClass.getDeclaredMethod("biu", Context.class, String.class);
                method.setAccessible(true);
                method.invoke(mainActivity, new Object[]{this, "Loading apk success!"});

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static boolean copyFileFromAssetsToSd(Context context, String fileName, String path) {
        boolean copyIsFinish = false;
        try {
            InputStream is = context.getAssets().open(fileName);
            File file = new File(path);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = 0;
            while ((i = is.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }
            fos.close();
            is.close();
            copyIsFinish = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return copyIsFinish;
    }
}
