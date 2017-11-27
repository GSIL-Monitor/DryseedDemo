package com.dryseed.dryseedapp.widget.graphicDetailsLayout.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.widget.graphicDetailsLayout.widget.GraphicDetailsLayout;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic_details_layout);

        GraphicDetailsLayout gdLayout = (GraphicDetailsLayout) findViewById(R.id.gdlayout);
        gdLayout.addFragment(new Fragment[] {new SpFragment(), new DeFragment()}, getSupportFragmentManager());
    }
}
