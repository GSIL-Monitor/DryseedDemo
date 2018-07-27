package com.dryseed.dryseedapp.test.parseJson;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.application.MyApplication;
import com.luojilab.component.basiclib.utils.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by caiminming on 2017/12/19.
 */

public class ParseJsonActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String file = "error.json";
        AssetManager assetManager = MyApplication.getInstance().getAssets();
        InputStream is = null;
        BufferedReader br = null;
        try {
            is = assetManager.open(file);
            br = new BufferedReader(new InputStreamReader(is));
            StringBuffer sb = new StringBuffer();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }

            JSONArray jsonArray = new JSONObject(sb.toString()).getJSONArray("server_code");
            Log.d("MMM", jsonArray.toString());
            ToastUtil.showToast(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
