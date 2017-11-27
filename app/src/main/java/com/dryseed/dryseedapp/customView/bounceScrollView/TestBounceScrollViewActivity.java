package com.dryseed.dryseedapp.customView.bounceScrollView;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;


public class TestBounceScrollViewActivity extends BaseActivity {

    private ListView mListView;
    private BounceScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bounce_scroll_view_layout);
        mScrollView = (BounceScrollView) findViewById(R.id.id_scrollView);
        mScrollView.setCallBack(new BounceScrollView.Callback() {

            @Override
            public void callback() {
                Toast.makeText(TestBounceScrollViewActivity.this, "you can do something!", 0)
                        .show();
                Intent intent = new Intent(TestBounceScrollViewActivity.this,
                        BounceScrollViewSecondActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        mListView = (ListView) findViewById(R.id.id_listView);
        mListView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, new ArrayList<String>(
                Arrays.asList("Hello", "World", "Welcome", "Java",
                        "Android", "Lucene", "C++", "C#", "HTML",
                        "Welcome", "Java", "Android", "Lucene", "C++",
                        "C#", "HTML"))));
    }

}
