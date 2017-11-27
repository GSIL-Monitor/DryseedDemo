package com.dryseed.dryseedapp.tools.xml;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import com.dryseed.dryseedapp.BaseActivity;

import java.io.InputStream;
import java.util.List;

/**
 * Created by caiminming on 2017/9/14.
 */

public class TestXmlPullParserActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AssetManager asset = getAssets();
        try {
            InputStream input = asset.open("student.xml");
            List<Student> list = ParserByPull.getStudents(input);
            for (Student stu : list) {
                Log.e("MMM", "Person ID: " + stu.getId() + ","
                        + stu.getName() + ", " + stu.getAge() + ", "
                        + stu.getSex());
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
