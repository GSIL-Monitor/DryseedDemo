package com.dryseed.dryseedapp.test.transition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

import java.util.ArrayList;

/**
 * Android共享元素场景切换动画的实现
 * http://blog.csdn.net/u012199331/article/details/72137112
 */
public class TestTransitionActivity extends BaseActivity {

    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_main_layout);

        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("name" + i);
        }
        ListView listView = (ListView) findViewById(R.id.lv);
        listView.setAdapter(new MyAdapter());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ready for intent
                Intent intent = new Intent(TestTransitionActivity.this, DetailActivity.class);
                intent.putExtra("name", list.get(position));

                // ready for transition options
                EasyTransitionOptions options =
                        EasyTransitionOptions.makeTransitionOptions(
                                TestTransitionActivity.this,
                                view.findViewById(R.id.iv_icon),
                                view.findViewById(R.id.tv_name),
                                findViewById(R.id.v_top_card));

                // start transition
                EasyTransition.startActivity(intent, options);
            }
        });
    }


    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            int count = 0;
            if (null != list)
                count = list.size();
            return count;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            if (null != convertView) {
                view = convertView;
            } else {
                view = LayoutInflater.from(TestTransitionActivity.this).inflate(R.layout.activity_transition_item, null, false);
            }
            TextView tvName = (TextView) view.findViewById(R.id.tv_name);
            tvName.setText(list.get(position));
            return view;
        }
    }
}
