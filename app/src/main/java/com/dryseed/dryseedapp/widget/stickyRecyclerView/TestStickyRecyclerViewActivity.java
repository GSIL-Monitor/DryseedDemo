package com.dryseed.dryseedapp.widget.stickyRecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dryseed.dryseedapp.R;


public class TestStickyRecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_recyclerview_main);
    }

    public void toSticky(View view) {
        startActivity(new Intent(this, StickyRecyclerViewActivity.class));
    }

    public void toPowerfulSticky(View view) {
        startActivity(new Intent(this, PowerfulStickyRecyclerViewActivity.class));
    }

    public void toPowerfulSticky2(View view) {
        startActivity(new Intent(this, BeautifulRecyclerViewActivity.class));
    }
}
