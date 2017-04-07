package com.dryseed.dryseedapp.dispatchEvent;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.dispatchEvent.ui.HorizontalScrollViewEx;
import com.dryseed.dryseedapp.utils.DPIUtil;

/**
 * 外部拦截法：
 * 重写父容器的onInterceptTouchEvent方法 -- 详见HorizontalScrollViewEx
 *
 * 父容器：HorizontalScrollViewEx
 * 子容器：ListView
 */
public class DispatchEventDemoActivity1 extends Activity {
    private static final String TAG = "DemoActivity_1";

    private HorizontalScrollViewEx mListContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_event_demo1_layout);
        Log.d(TAG, "onCreate");
        initView();
    }

    private void initView() {
        LayoutInflater inflater = getLayoutInflater();
        //父容器
        mListContainer = (HorizontalScrollViewEx) findViewById(R.id.container);
        final int screenWidth = DPIUtil.getWidth();
        final int screenHeight = DPIUtil.getHeight();
        for (int i = 0; i < 3; i++) {
            //子容器
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.activity_dispatch_event_content_layout, mListContainer, false);
            layout.getLayoutParams().width = screenWidth;
            TextView textView = (TextView) layout.findViewById(R.id.title);
            textView.setText("page " + (i + 1));
            layout.setBackgroundColor(Color.rgb(255 / (i + 1), 255 / (i + 1), 0));
            createList(layout);
            mListContainer.addView(layout);
        }
    }

    private void createList(ViewGroup layout) {
        ListView listView = (ListView) layout.findViewById(R.id.list);
        ArrayList<String> datas = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            datas.add("name " + i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_dispatch_event_content_list_item, R.id.name, datas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DispatchEventDemoActivity1.this, "click item", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
