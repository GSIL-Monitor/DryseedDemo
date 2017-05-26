package com.dryseed.dryseedapp.test.parcelable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.dryseed.dryseedapp.test.singleTask.TestAActivity;

/**
 * Created by caiminming on 2017/5/12.
 */

public class TestParcelableBActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Parcelable
//        PersonByParcelable person = getIntent().getParcelableExtra(TestParcelableAActivity.PAR_KEY);
//        Toast.makeText(this, String.format("userName:%s nickName:%s age:%d", person.getUsername(), person.getUsername(), person.getAge()), Toast.LENGTH_SHORT).show();

        // Serializable
        PersonBySerializable person = (PersonBySerializable)getIntent().getSerializableExtra(TestParcelableAActivity.SER_KEY);
        Toast.makeText(this, String.format("userName:%s nickName:%s age:%d", person.getUsername(), person.getUsername(), person.getAge()), Toast.LENGTH_SHORT).show();

    }
}
