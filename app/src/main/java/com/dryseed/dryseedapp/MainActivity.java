package com.dryseed.dryseedapp;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.dryseed.dryseedapp.canvas.canvas.TestCanvasActivity;
import com.luojilab.component.basiclib.utils.ActivityUtil;
import com.luojilab.component.basiclib.utils.LogUtil;
import com.luojilab.component.basiclib.utils.ToastUtil;
import com.dryseed.dryseedapp.utils.dao.DaoManager;
import com.dryseed.dryseedapp.widget.dialog.DsAlertDialog;
import com.dryseed.dryseedapp.widget.floatView.FloatViewManager;
import com.orhanobut.logger.Logger;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends ListActivity {

    private CountDownLatch mCountDownLatch;
    private DsAlertDialog mDialog;
    private DaoManager mDaoManager;

    private final static Comparator<Map<String, Object>> NAME_COMPARATOR =
            new Comparator<Map<String, Object>>() {
                private final Collator collator = Collator.getInstance();

                @Override
                public int compare(Map<String, Object> map1, Map<String, Object> map2) {
                    return collator.compare(map1.get("title"), map2.get("title"));
                }
            };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme); //启动加速之主题切换

        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ToastUtil.cancelToast();
            }
        }, 1000);

        Intent intent = getIntent();
        String path = intent.getStringExtra("com.dryseed.dryseedapp.Path");

        if (path == null) {
            path = "";
        }

        LogUtil.d("app path : " + path);

        setListAdapter(new SimpleAdapter(this, getData(path),
                android.R.layout.simple_list_item_1, new String[]{"title"},
                new int[]{android.R.id.text1}));
        getListView().setTextFilterEnabled(true);


        //升级数据库
        Observable
                .create(
                        new ObservableOnSubscribe<Integer>() {
                            @Override
                            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                                //Log.d("MMM", "MainActivity =======" + Thread.currentThread().getName());
                                mCountDownLatch = new CountDownLatch(1);
                                upgradeDatabase();
                                try {
                                    mCountDownLatch.await();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                emitter.onNext(1);
                            }
                        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        //Log.d("MMM", String.format("MainActivity Task%d begin thread : %s", integer, Thread.currentThread().getName()));
                        //dispath activity
                        //startActivity(new Intent(MainActivity.this, TestCanvasActivity.class));
                    }
                });


        //scheme : 在Test/WebView中打开html
        String scheme = intent.getScheme();//获得Scheme名称
        if ("dryseed".equals(scheme)) {
            Toast.makeText(this, "scheme", Toast.LENGTH_SHORT).show();
            Uri uri = intent.getData();
            String name = uri.getQueryParameter("name");
            Log.d("MMM", "scheme: " + uri.getScheme());
            Log.d("MMM", "host: " + uri.getHost());
            Log.d("MMM", "port: " + uri.getPort());
            Log.d("MMM", "path: " + uri.getPath());
            Log.d("MMM", "queryString: " + uri.getQuery());
            Log.d("MMM", "queryParameter - name: " + uri.getQueryParameter("name"));
            if (null != name && name.equals("canvas")) {
                startActivity(new Intent(this, TestCanvasActivity.class));
            }
        }

    }

    /**
     * 数据库升级提示
     * 测试：
     * DaoManager.java中默认加了10s的升级延时；
     * 通过修改app/build.gradle中的schemaVersion测试
     */
    private void upgradeDatabase() {
        if (DaoManager.isUpdating) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mDialog = new DsAlertDialog(MainActivity.this);
                    mDialog.setTitle(R.string.error_title);
                    mDialog.setMessage("正在更新数据库，请稍等");
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                }
            });
            while (!DaoManager.isUpdating) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mDialog != null) {
                        mDialog.dismiss();
                    }
                }
            });
        }

        if (null != mCountDownLatch) {
            mCountDownLatch.countDown();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected List<Map<String, Object>> getData(String prefix) {
        List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory("com.dryseed.dryseedapp.TEST");

        PackageManager pm = getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);

        if (null == list) {
            return myData;
        }

        String[] prefixPath;
        String prefixWithSlash = prefix;

        if (prefix.equals("")) {
            prefixPath = null;
        } else {
            prefixPath = prefix.split("/");
            prefixWithSlash = prefix + "/";
        }

        int len = list.size();

        Map<String, Boolean> entries = new HashMap<String, Boolean>();

        for (int i = 0; i < len; i++) {
            ResolveInfo info = list.get(i);
            CharSequence labelSeq = info.loadLabel(pm);
            String label = labelSeq != null
                    ? labelSeq.toString()
                    : info.activityInfo.name;

            if (prefixWithSlash.length() == 0 || label.startsWith(prefixWithSlash)) {
                String[] labelPath = label.split("/");

                String nextLabel = prefixPath == null ? labelPath[0] : labelPath[prefixPath.length];

                if ((prefixPath != null ? prefixPath.length : 0) == labelPath.length - 1) {
                    addItem(myData, nextLabel, activityIntent(
                            info.activityInfo.applicationInfo.packageName,
                            info.activityInfo.name));
                } else {
                    if (entries.get(nextLabel) == null) {
                        addItem(myData, nextLabel, browseIntent(prefix.equals("") ? nextLabel : prefix + "/" + nextLabel));
                        entries.put(nextLabel, true);
                    }
                }
            }
        }

        Collections.sort(myData, NAME_COMPARATOR);

        return myData;
    }

    protected Intent activityIntent(String pkg, String componentName) {
        Intent result = new Intent();
        result.setClassName(pkg, componentName);
        return result;
    }

    protected Intent browseIntent(String path) {
        Intent result = new Intent();
        result.setClass(this, MainActivity.class);
        result.putExtra("com.dryseed.dryseedapp.Path", path);
        return result;
    }

    protected void addItem(List<Map<String, Object>> data, String name, Intent intent) {
        Map<String, Object> temp = new HashMap<String, Object>();
        temp.put("title", name);
        temp.put("intent", intent);
        data.add(temp);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Map<String, Object> map = (Map<String, Object>) l.getItemAtPosition(position);

        Intent intent = (Intent) map.get("intent");
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 全局悬浮窗，在FloatViewActivity开启
        if (showFloatView()) {
            FloatViewManager.getInstance().showFloatView(this);
        }

        //ActivityUtil.printTaskInfo(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 全局悬浮窗，在FloatViewActivity开启
        if (showFloatView()) {
            FloatViewManager.getInstance().hideFloatView();
        }
    }

    protected boolean showFloatView() {
        return true;
    }
}
