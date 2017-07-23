package com.dryseed.dryseedapp.framework.fastjson;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.framework.fastjson.bean.Person;
import com.dryseed.dryseedapp.framework.fastjson.model.DataManager;
import com.dryseed.dryseedapp.framework.fastjson.parser.FastJsonParser;
import com.dryseed.dryseedapp.framework.fastjson.parser.GsonParser;
import com.dryseed.dryseedapp.framework.fastjson.parser.IJsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

//import com.dryseed.dryseedapp.framework.fastjson.parser.JacksonParser;

/**
 * Created by caiminming on 2016/11/24.
 */
public class TestFastJsonActivity extends Activity {
    final int CYCLE_COUNT = 100;
    private int mExecuteCount = 1;

    private EditText mCountEditText;
    private TextView mCostTimeTextView;
    private IJsonParser mJsonParser;
    private Executor executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fastjson_layout);
        mCountEditText = (EditText) findViewById(R.id.countEditTextId);
        mCostTimeTextView = (TextView) findViewById(R.id.costTimeTxtId);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gsonBtnId: {
                clearTimeTextView();
                gsonParser();
            }
            break;
            case R.id.fastJsonBtnId: {
                clearTimeTextView();
                fastJsonParser();
            }
            break;
            case R.id.jacksonBtnId: {
                clearTimeTextView();
                jacksonParser();
            }
            break;
            case R.id.clearBtnId: {
                clearCountEditText();
            }
            break;
            case R.id.executeBtnId: {
                mExecuteCount = Integer.parseInt(mCountEditText.getText().toString());
                clearTimeTextView();
            }
            break;
            default: {

            }
            break;
        }
    }

    private void clearTimeTextView() {
        mCostTimeTextView.setText("");
    }

    private void clearCountEditText() {
        mCountEditText.setText("");
    }

    private void jacksonParser() {
        /*executor.execute(new Runnable(){
            @Override
            public void run(){
                long toJsonCostTime = 0;
                long fromJsonCostTime = 0;
                long toJsonListCostTime = 0;
                long fromJsonListCostTime = 0;
                long toJsonMapCostTime = 0;
                long fromJsonMapCostTime = 0;

                Person one_person = DataManager.newPerson();
                ArrayList<Person> arrayList = DataManager.newPersonList(mExecuteCount);
                Map<String, Person> map = DataManager.newPersonMap(mExecuteCount);

                long startTime = System.currentTimeMillis();
                for(int index = 0; index < CYCLE_COUNT; index++){
                    mJsonParser = new JacksonParser();
                    String jsonString = mJsonParser.toJson(one_person);
                    toJsonCostTime += (System.currentTimeMillis()-startTime);
                    startTime = System.currentTimeMillis();

                    Person person = mJsonParser.fromJson(jsonString, Person.class);
                    fromJsonCostTime += (System.currentTimeMillis()-startTime);
                    startTime = System.currentTimeMillis();

                    String jsonArrayString = mJsonParser.toJson(arrayList);
                    toJsonListCostTime += (System.currentTimeMillis()-startTime);
                    startTime = System.currentTimeMillis();

                    List<Person> personArrayList = mJsonParser.listFromJson(jsonArrayString, Person.class);
                    fromJsonListCostTime += (System.currentTimeMillis()-startTime);
                    startTime = System.currentTimeMillis();

                    String jsonMapString = mJsonParser.toJson(map);
                    toJsonMapCostTime += (System.currentTimeMillis()-startTime);
                    startTime = System.currentTimeMillis();

                    Map<String, Person> stringPersonMap = mJsonParser.mapFromJson(jsonMapString, Person.class);
                    fromJsonMapCostTime += (System.currentTimeMillis()-startTime);
                    startTime = System.currentTimeMillis();
                }

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Jackson解析：").append("\n");
                stringBuilder.append("bean→String耗时：" + toJsonCostTime/CYCLE_COUNT).append("\n");
                stringBuilder.append("String→bean耗时：" + fromJsonCostTime/CYCLE_COUNT).append("\n");
                stringBuilder.append("List→String耗时：" + toJsonListCostTime/CYCLE_COUNT).append("\n");
                stringBuilder.append("String→List耗时：" + fromJsonListCostTime/CYCLE_COUNT).append("\n");
                stringBuilder.append("Map→String耗时：" + toJsonMapCostTime/CYCLE_COUNT).append("\n");
                stringBuilder.append("String→Map耗时：" + fromJsonMapCostTime/CYCLE_COUNT).append("\n");
                showCostTime(stringBuilder.toString());
            }
        });*/
    }

    private void fastJsonParser() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                long toJsonCostTime = 0;
                long fromJsonCostTime = 0;
                long toJsonListCostTime = 0;
                long fromJsonListCostTime = 0;
                long toJsonMapCostTime = 0;
                long fromJsonMapCostTime = 0;

                Person one_person = DataManager.newPerson();
                ArrayList<Person> arrayList = DataManager.newPersonList(mExecuteCount);
                Map<String, Person> map = DataManager.newPersonMap(mExecuteCount);

                long startTime = System.currentTimeMillis();
                for (int index = 0; index < CYCLE_COUNT; index++) {
                    mJsonParser = new FastJsonParser();
                    String jsonString = mJsonParser.toJson(one_person);
                    toJsonCostTime += (System.currentTimeMillis() - startTime);
                    startTime = System.currentTimeMillis();

                    Person person = mJsonParser.fromJson(jsonString, Person.class);
                    fromJsonCostTime += (System.currentTimeMillis() - startTime);
                    startTime = System.currentTimeMillis();

                    String jsonArrayString = mJsonParser.toJson(arrayList);
                    toJsonListCostTime += (System.currentTimeMillis() - startTime);
                    startTime = System.currentTimeMillis();

                    List<Person> personArrayList = mJsonParser.listFromJson(jsonArrayString, Person.class);
                    fromJsonListCostTime += (System.currentTimeMillis() - startTime);
                    startTime = System.currentTimeMillis();

                    String jsonMapString = mJsonParser.toJson(map);
                    toJsonMapCostTime += (System.currentTimeMillis() - startTime);
                    startTime = System.currentTimeMillis();

                    Map<String, Person> stringPersonMap = mJsonParser.mapFromJson(jsonMapString, Person.class);
                    fromJsonMapCostTime += (System.currentTimeMillis() - startTime);
                    startTime = System.currentTimeMillis();
                }

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("FastJson解析：").append("\n");
                stringBuilder.append("bean→String耗时：" + toJsonCostTime / CYCLE_COUNT).append("\n");
                stringBuilder.append("String→bean耗时：" + fromJsonCostTime / CYCLE_COUNT).append("\n");
                stringBuilder.append("List→String耗时：" + toJsonListCostTime / CYCLE_COUNT).append("\n");
                stringBuilder.append("String→List耗时：" + fromJsonListCostTime / CYCLE_COUNT).append("\n");
                stringBuilder.append("Map→String耗时：" + toJsonMapCostTime / CYCLE_COUNT).append("\n");
                stringBuilder.append("String→Map耗时：" + fromJsonMapCostTime / CYCLE_COUNT).append("\n");
                showCostTime(stringBuilder.toString());
            }
        });
    }

    private void gsonParser() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                long toJsonCostTime = 0;
                long fromJsonCostTime = 0;
                long toJsonListCostTime = 0;
                long fromJsonListCostTime = 0;
                long toJsonMapCostTime = 0;
                long fromJsonMapCostTime = 0;

                Person one_person = DataManager.newPerson();
                ArrayList<Person> arrayList = DataManager.newPersonList(mExecuteCount);
                Map<String, Person> map = DataManager.newPersonMap(mExecuteCount);

                long startTime = System.currentTimeMillis();
                for (int index = 0; index < CYCLE_COUNT; index++) {
                    mJsonParser = new GsonParser();
                    String jsonString = mJsonParser.toJson(one_person);
                    toJsonCostTime += (System.currentTimeMillis() - startTime);
                    startTime = System.currentTimeMillis();

                    Person person = mJsonParser.fromJson(jsonString, Person.class);
                    fromJsonCostTime += (System.currentTimeMillis() - startTime);
                    startTime = System.currentTimeMillis();

                    String jsonArrayString = mJsonParser.toJson(arrayList);
                    toJsonListCostTime += (System.currentTimeMillis() - startTime);
                    startTime = System.currentTimeMillis();

                    List<Person> personArrayList = mJsonParser.listFromJson(jsonArrayString, Person.class);
                    fromJsonListCostTime += (System.currentTimeMillis() - startTime);
                    startTime = System.currentTimeMillis();

                    String jsonMapString = mJsonParser.toJson(map);
                    toJsonMapCostTime += (System.currentTimeMillis() - startTime);
                    startTime = System.currentTimeMillis();

                    Map<String, Person> stringPersonMap = mJsonParser.mapFromJson(jsonMapString, Person.class);
                    fromJsonMapCostTime += (System.currentTimeMillis() - startTime);
                    startTime = System.currentTimeMillis();
                }

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Gson解析：").append("\n");
                stringBuilder.append("bean→String耗时：" + toJsonCostTime / CYCLE_COUNT).append("\n");
                stringBuilder.append("String→bean耗时：" + fromJsonCostTime / CYCLE_COUNT).append("\n");
                stringBuilder.append("List→String耗时：" + toJsonListCostTime / CYCLE_COUNT).append("\n");
                stringBuilder.append("String→List耗时：" + fromJsonListCostTime / CYCLE_COUNT).append("\n");
                stringBuilder.append("Map→String耗时：" + toJsonMapCostTime / CYCLE_COUNT).append("\n");
                stringBuilder.append("String→Map耗时：" + fromJsonMapCostTime / CYCLE_COUNT).append("\n");
                showCostTime(stringBuilder.toString());
            }
        });
    }

    private void showCostTime(final String costTime) {
        TestFastJsonActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCostTimeTextView.append(costTime);
            }
        });
    }
}
