package com.dryseed.dryseedapp.utils;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class JsonObjectRequest extends Request<JSONObject> {
    private Map<String, String> mMap;
    private Listener<JSONObject> mListener;
    private ParseNetworkResponseListener mParseNetworkResponseListener;
    private Map<String, String> mHeaders = new HashMap<String, String>(1);

    public interface ParseNetworkResponseListener
    {
        void onParseNetworkResponseListener(NetworkResponse response);
    }

    public JsonObjectRequest(int method, String url, Listener<JSONObject> listener, ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
    }

    public JsonObjectRequest(String url, Listener<JSONObject> listener, ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        mListener = listener;
    }

    public JsonObjectRequest(String url, Listener<JSONObject> listener, ErrorListener errorListener, Map<String, String> map) {
        super(Request.Method.POST, url, errorListener);
        mListener = listener;
        mMap = map;
    }

    public Listener<JSONObject> getSuccessListener(){
        return mListener;
    }

    public JsonObjectRequest(String url, Listener<JSONObject> listener, ErrorListener errorListener, ParseNetworkResponseListener parseNetworkResponseListener, Map<String,String> map) {
        super(Request.Method.POST, url, errorListener);
        mListener = listener;
        mMap = map;
        mParseNetworkResponseListener = parseNetworkResponseListener;
    }

    //mMap是已经按照前面的方式,设置了参数的实例
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mMap;
    }

    //此处因为response返回值需要json数据,和JsonObjectRequest类一样即可
    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        if(null != mParseNetworkResponseListener){
            mParseNetworkResponseListener.onParseNetworkResponseListener(response);
        }

        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }
    @Override
    protected void deliverResponse(JSONObject response) {
        mListener.onResponse(response);
    }

    public void setCookie() {
        /*Account account = ILogin.getActiveAccount();

        Cookie c = new Cookie();
        if (account != null) {
            c.set("uid", account.getUid());
            c.set("username", account.getUserName());
            c.set("tokenid", account.getSessonId());
        }
        mHeaders.put("Cookie", c.toString());
        mHeaders.put("Charset", "UTF-8");
//		mHeaders.put("Accept-Encoding", "gzip");*/
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        setCookie();
        return mHeaders;
    }
}