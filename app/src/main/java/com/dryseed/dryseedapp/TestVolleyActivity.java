package com.dryseed.dryseedapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.dryseed.dryseedapp.utils.JsonObjectRequest;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

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
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //for test begin ================
                        JSONObject jsonObject;
                        String str = "{\n" +
                                "  \"data\": {\n" +
                                "    \"authorId\": \"427\",\n" +
                                "    \"countDown\": 0,\n" +
                                "    \"endTime\": 1474438324084,\n" +
                                "    \"floatImage\": \"\",\n" +
                                "    \"floatJump\": \"\",\n" +
                                "    \"h5Pull\": \"http://7609.mpull.live.lecloud.com/live/5303/desc.m3u8\",\n" +
                                "    \"hasLiked\": 0,\n" +
                                "    \"id\": \"5303\",\n" +
                                "    \"indexImage\": \"http://m.360buyimg.com/live/jfs/t3256/349/2405862371/443791/1b2eca3c/57dff401Nd9543d3b.jpg!q70.jpg\",\n" +
                                "    \"jumpURL\": \"\",\n" +
                                "    \"likeNum\": 0,\n" +
                                "    \"mPull\": \"rtmp://7609.mpull.live.lecloud.com/live/3747?tm=20160909142210&sign=5bfe4174e4fde768105c81551b076c36\",\n" +
                                "    \"miaoShaList\": [\n" +
                                "      \n" +
                                "    ],\n" +
                                "    \"operateWord\": \"\",\n" +
                                "    \"pageView\": 1645,\n" +
                                "    \"percent\": 0,\n" +
                                "    \"pin\": \"壮壮壮壮朱\",\n" +
                                "    \"playBack\": {\n" +
                                "      \"videoDuration\": 378,\n" +
                                "      \"videoImg\": \"http://m.360buyimg.com/live/jfs/t3256/349/2405862371/443791/1b2eca3c/57dff401Nd9543d3b.jpg!q70.jpg\",\n" +
                                "      \"videoType\": \"mp4\",\n" +
                                "      \"videoUnique\": \"30f105e42a\",\n" +
                                "      \"videoUrl\": \"http://play.68mtv.com:8080/play13/60595.mp4\"\n" +
                                "    },\n" +
                                "    \"position\": 0,\n" +
                                "    \"publishTime\": 1474435151000,\n" +
                                "    \"screen\": \"\",\n" +
                                "    \"selfH5Pulls\": [\n" +
                                "      \"http://play.vlive.jcloud.com/hls/5303.m3u8\"\n" +
                                "    ],\n" +
                                "    \"selfPulls\": [\n" +
                                "      \"rtmp://play.vlive.jcloud.com:2000/vlive/5303\"\n" +
                                "    ],\n" +
                                "    \"shareInfo\": {\n" +
                                "      \"avatar\": \"http://m.360buyimg.com/live/s100x100_jfs/t3256/349/2405862371/443791/1b2eca3c/57dff401Nd9543d3b.jpg!q70.jpg\",\n" +
                                "      \"content\": \"厉害了我的哥！JD Fashion正在直播：五道口拦截小鲜肉 打造最潮学生装，快来一起围观哦~\",\n" +
                                "      \"title\": \"您有一条【京东直播】邀请\",\n" +
                                "      \"url\": \"http://h5.m.jd.com/active/3Py5mBpfcJux1LmBJb2gCQB2XFb7/live.html?position=0&id=5303\"\n" +
                                "    },\n" +
                                "    \"skuNum\": 24,\n" +
                                "    \"startTime\": 1474437945874,\n" +
                                "    \"status\": 3,\n" +
                                "    \"tags\": \"\",\n" +
                                "    \"title\": \"五道口拦截小鲜肉 打造最潮学生装\",\n" +
                                "    \"type\": 17,\n" +
                                "    \"typeName\": \"美妆美装\",\n" +
                                "    \"unionId\": \"\",\n" +
                                "    \"updateTime\": 1474438563000,\n" +
                                "    \"userName\": \"JD Fashion\",\n" +
                                "    \"userPic\": \"http://m.360buyimg.com/mobilecms/jfs/t3016/170/2303544823/34500/299a1d97/57d7b0f7Na2456e6b.jpg!q70.jpg\",\n" +
                                "    \"userSynopsis\": \"京东达人精选，品味时尚生活：dr.jd.com\",\n" +
                                "    \"videoUnique\": \"30f105e42a\",\n" +
                                "    \"screen\": \"1\",\n" +
                                "    \"floatIcon\": {\n" +
                                "      \"img\": \"http://m.360buyimg.com/mobilecms/jfs/t2776/168/2839189465/253301/7a957ca5/57761814Ncf558577.jpg!q70.jpg\",\n" +
                                "      \"jump\": {\n" +
                                "        \"des\": \"m\",\n" +
                                "        \"srv\": \"\",\n" +
                                "        \"params\": {\n" +
                                "          \"url\": \"m.jd.com\"\n" +
                                "        }\n" +
                                "      }\n" +
                                "    },\n" +
                                "    \"slogan\": \"欢迎大家来看我的直播!\",\n" +
                                "    \"location\": \"巴西\",\n" +
                                "    \"buyVr\": {\n" +
                                "      \"jump\": {\n" +
                                "        \"des\": \"m\",\n" +
                                "        \"srv\": \"\",\n" +
                                "        \"params\": {\n" +
                                "          \"url\": \"m.jd.com\"\n" +
                                "        }\n" +
                                "      }\n" +
                                "    }\n" +
                                "  },\n" +
                                "  \"code\": \"0\"\n" +
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
