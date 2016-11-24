package com.dryseed.dryseedapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.dryseed.dryseedapp.fastjson.bean.Person;
import com.dryseed.dryseedapp.utils.JsonObjectRequest;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/25.
 */
public class TestVolleyActivity extends Activity {
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("MMM", getClass().getSimpleName() + " on create");

        jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                "http://gold.xitu.io/entry/579852601532bc0060eca77d/promote?utm_source=baidu&utm_medium=keyword&utm_content=restfulapi&utm_campaign=q3_search",
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Logger.d("MMM", response.toString());
                        JSONArray person = response.optJSONArray("person");
                        List<Person> list = JSON.parseArray(person.toString(), Person.class);
                        Logger.d("MMM", list.toString());
                        if(list != null && list.size() > 0){
                            Logger.d("MMM", list.get(0).getAddress());
                            Toast.makeText(TestVolleyActivity.this, list.get(0).getAddress(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //for test begin ================
                        JSONObject jsonObject;
                        String str = "{\n" +
                                "  \"person\": [\n" +
                                "    {\n" +
                                "      \"address\": \"广东省东莞市长安镇XXX_0\",\n" +
                                "      \"age\": 8,\n" +
                                "      \"birthday\": {\n" +
                                "        \"day\": 2008\n" +
                                "      },\n" +
                                "      \"country\": \"中国\",\n" +
                                "      \"married\": true,\n" +
                                "      \"name\": \"小布_0\",\n" +
                                "      \"nation\": \"汉族\",\n" +
                                "      \"race\": \"黄种人\",\n" +
                                "      \"salary\": 8000.0,\n" +
                                "      \"sex\": \"女\",\n" +
                                "      \"telephone\": \"1315757800\"\n" +
                                "    }\n" +
                                "  ]\n" +
                                "}";
                        jsonObject = null;
                        try {
                            jsonObject = new JSONObject(str);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //for test end ================

                        jsonObjectRequest.getSuccessListener().onResponse(jsonObject);
                    }
                }
        );
        MyApplication.getInstance().addToRequestQueue(jsonObjectRequest);
    }



}
