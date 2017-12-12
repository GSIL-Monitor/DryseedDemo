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
import com.dryseed.dryseedapp.utils.ToastUtil;
import com.dryseed.dryseedapp.widget.floatView.FloatViewManager;
import com.orhanobut.logger.Logger;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ListActivity {

    private final static Comparator<Map<String, Object>> NAME_COMPARATOR =
            new Comparator<Map<String, Object>>() {
                private final Collator collator = Collator.getInstance();

                public int compare(Map<String, Object> map1, Map<String, Object> map2) {
                    return collator.compare(map1.get("title"), map2.get("title"));
                }
            };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme); //启动加速之主题切换

        super.onCreate(savedInstanceState);

        Log.d("MMM", "MainActivity onCreate");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ToastUtil.cancelToast();
            }
        }, 1000);

        Logger.init().setMethodCount(0).hideThreadInfo();

        //test
        //startActivity(new Intent(this, DispatchEventDemoActivity3.class));

        Intent intent = getIntent();
        String path = intent.getStringExtra("com.dryseed.dryseedapp.Path");

        if (path == null) {
            path = "";
        }

        setListAdapter(new SimpleAdapter(this, getData(path),
                android.R.layout.simple_list_item_1, new String[]{"title"},
                new int[]{android.R.id.text1}));
        getListView().setTextFilterEnabled(true);

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
            if (name.equals("canvas")) {
                startActivity(new Intent(this, TestCanvasActivity.class));
            }
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

        if (null == list)
            return myData;

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
