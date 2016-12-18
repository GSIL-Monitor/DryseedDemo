package com.dryseed.dryseedapp.customView.arcMenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.customView.bounceScrollView.BounceScrollView;
import com.dryseed.dryseedapp.customView.bounceScrollView.BounceScrollViewSecondActivity;

import java.util.ArrayList;
import java.util.Arrays;


public class TestArcMenuActivity extends Activity {

    private ArcMenu mArcMenuLeftTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arc_menu_layout);

        mArcMenuLeftTop = (ArcMenu) findViewById(R.id.id_arcmenu1);
        //动态添加一个MenuItem
        ImageView people = new ImageView(this);
        people.setImageResource(R.drawable.composer_with);
        people.setTag("People");
        mArcMenuLeftTop.addView(people);


        mArcMenuLeftTop.setOnMenuItemClickListener(new ArcMenu.OnMenuItemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                Toast.makeText(TestArcMenuActivity.this,
                        pos + ":" + view.getTag(), Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

}
