package com.dryseed.dryseedapp.customView.customViewGroup01;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.dryseed.dryseedapp.R;

import java.util.Arrays;
import java.util.List;

public class TestCustomViewGroup01Activity extends Activity
{

    private List<String> mDatas = Arrays.asList("Android", "Java");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_custom_view_group_01);

        // setContentView(R.layout.activity_main);
//		setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_main,
//				R.id.id_txt, mDatas));

    }

}
