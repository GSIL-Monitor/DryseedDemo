package com.dryseed.dryseedapp.practice.location.lib;

import android.Manifest;
import android.content.res.AssetManager;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.dryseed.dryseedapp.MyApplication;
import com.luojilab.component.basiclib.utils.AppConfig;
import com.luojilab.component.basiclib.utils.PermissionUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by caiminming on 2018/1/3.
 */

public class LocationManager implements AMapLocationListener {
    public interface LocationInfoCallBack {
        void onLocationSucc();
    }

    public static LocationManager getInstance() {
        if (INSTANCE == null) {
            synchronized (LocationManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocationManager();
                }
            }
        }
        return INSTANCE;
    }

    private static LocationManager INSTANCE = null;

    private static AMapLocationClient mLocationClient = null;
    private static AMapLocationClientOption mLocationOption = null;
    private int mfailedCount = 0;
    private CityInfo mCityInfoList = null;
    private List<SoftReference<LocationInfoCallBack>> mCallBackList = new ArrayList<>();
    private LocationInfo mLastLocationInf = null;


    private LocationManager() {
        //初始化定位
        mLocationClient = new AMapLocationClient(MyApplication.getInstance().getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);

        loadCityInfo();
        //mLastLocationInf = AppPreferences.get().getLocationInfo();
    }

    /**
     * 定位回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (AppConfig.DEBUG) {
            Log.d("MMM", "onLocationChanged:" + amapLocation);
            // onLocationChanged:latitude=31.053807#longitude=121.270222#province=上海市#city=上海市#district=松江区#cityCode=021#adCode=310117#
            // address=上海市松江区中辰路655号靠近巨人网络生活区#country=中国#road=中辰路#poiName=巨人网络生活区#street=中辰路#streetNum=655号#
            // aoiName=巨人网络生活区#poiid=#floor=#errorCode=0#errorInfo=success#locationDetail= #csid:a1ae65eee93b43c696a61325aa3c4455#
            // description=在巨人网络生活区附近#locationType=5
        }

        if (amapLocation != null) {
            if (AppConfig.DEBUG) {
                Log.d("MMM", "onLocationChanged:" + amapLocation.getErrorCode());
            }
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                if (AppConfig.DEBUG) {
                    Log.d("MMM", Thread.currentThread().getName() + " " + amapLocation.getAddress());
                    // main 上海市松江区中辰路655号靠近巨人网络生活区
                }
                stopLocation();
                CityInfo info = findCityInfoByAdCode(amapLocation.getAdCode());
                Log.d("MMM", "onLocationChanged : adCode = " + amapLocation.getAdCode() + " | city = " + info.getName());
                LocationInfo locationInfo = new LocationInfo();
                locationInfo.setLatitude(amapLocation.getLatitude());
                locationInfo.setLongitude(amapLocation.getLongitude());
                if (info != null) {
                    locationInfo.setCityName(info.getLastNode() == null ? info.getName() : info.getLastNode().getName() + info.getName());
                    locationInfo.setCityId(info.getId());
                }
                locationInfo.setLocationTime(System.currentTimeMillis());
                mLastLocationInf = locationInfo;
                //AppPreferences.get().setLocationInfo(mLastLocationInf);
                notifyLocationListener();
            } else {
                mfailedCount += 1;
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("MMM", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
            if (mfailedCount > 3) {
                stopLocation();
                if (AppConfig.DEBUG) {
                    Log.d("MMM", "stopLocation");
                }
                notifyLocationListener();
            }
        }
    }

    private void stopLocation() {
        mLocationClient.stopLocation();
        mfailedCount = 0;
    }

    public CityInfo getCityInfo() {
        return mCityInfoList;
    }

    public LocationInfo getLocationInfo() {
        return mLastLocationInf;
    }

    public void addLocationListener(LocationInfoCallBack callback) {
        if (!contains(callback)) {
            mCallBackList.add(new SoftReference(callback));
        }
    }

    public void removeLocationListener(LocationInfoCallBack callback) {
        int size = mCallBackList.size();
        for (int i = size - 1; i >= 0; i--) {
            final SoftReference<LocationInfoCallBack> reference = mCallBackList.get(i);
            if (reference != null) {
                if (reference.get() == callback) {
                    mCallBackList.remove(reference);
                }
            }
        }
    }

    private void notifyLocationListener() {
        int size = mCallBackList.size();
        for (int i = size - 1; i >= 0; i--) {
            final SoftReference<LocationInfoCallBack> reference = mCallBackList.get(i);
            if (reference != null) {
                if (reference.get() != null) {
                    reference.get().onLocationSucc();
                }
            }
        }
    }

    private boolean contains(LocationInfoCallBack callback) {
        int size = mCallBackList.size();
        for (int i = 0; i < size; i++) {
            final SoftReference<LocationInfoCallBack> reference = mCallBackList.get(i);
            if (reference != null) {
                if (reference.get() == callback) {
                    return true;
                }
            }
        }
        return false;
    }

    //自动4小时一定位
    public void toggleLocation() {
        if (Math.abs(System.currentTimeMillis() - getLocationInfo().getLocationTime()) > DateUtils.HOUR_IN_MILLIS * 4) {
            startLocation();
        }
    }

    public void startLocation() {
        if (PermissionUtil.checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            Log.d("MMM", "startLocation has permission");
            mLocationClient.stopLocation();
            mfailedCount = 0;
            mLocationClient.startLocation();
        } else {
            Log.d("MMM", "startLocation no permission");
        }
    }

    /**
     * 加载城市json 并 启动定位
     * 初始化时调用
     */
    public void loadCityInfo() {
        Observable.just("city.json").subscribeOn(Schedulers.io()).map(new Function<String, CityInfo>() {
            @Override
            public CityInfo apply(String s) throws Exception {
                CityInfo values = file2Obj(s);
                Log.d("MMM", "CityInfo : " + values.getList().size());
                return values;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<CityInfo>() {
            @Override
            public void accept(CityInfo locationInfos) throws Exception {
                mCityInfoList = locationInfos;
                startLocation();
            }
        });
    }

    /**
     * json文件 转 CityInfo对象
     *
     * @param file
     * @return
     */
    private CityInfo file2Obj(String file) {
        AssetManager assetManager = MyApplication.getInstance().getAssets();
        InputStream is = null;
        try {
            is = assetManager.open(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuffer stringBuffer = new StringBuffer();
            String str;
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str);
            }
            return json2Obj(null, new JSONObject(stringBuffer.toString()));
        } catch (Exception e) {
            if (AppConfig.DEBUG) {
                Log.e("MMM", "[cached]", e);
            }
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {

                }
            }
        }
        return null;
    }

    /**
     * json对象 转 CityInfo对象
     *
     * @param last
     * @param jsonObject
     * @return
     * @throws JSONException
     */
    private CityInfo json2Obj(Object last, JSONObject jsonObject) throws JSONException {
        CityInfo info = new CityInfo();
        if (last != null && last instanceof CityInfo) {
            info.setLastNode((CityInfo) last);
        }
        info.setName(jsonObject.getString("name"));
        if (jsonObject.has("id")) {
            info.setId(jsonObject.getString("id"));
        }
        if (jsonObject.has("adcode")) {
            info.setAdcode(jsonObject.getString("adcode"));
        }
        if (jsonObject.has("cities")) {
            JSONArray array = jsonObject.getJSONArray("cities");
            int size = array.length();
            if (size > 0) {
                List<CityInfo> list = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    list.add(json2Obj(info, array.getJSONObject(i)));
                }
                info.setList(list);
            }
        }

        /*if (AppConfig.DEBUG) {
            if (info.getList() != null) {
                Log.d("MMM", "父节点:" + info.getList().size() + "  " + info.getName() + "  " + info.getAdcode());
            } else {
                Log.d("MMM", "子节点:" + info.getName() + "  " + info.getAdcode() + "  ");
            }
        }*/
        return info;
    }

    //    private Map<String, CityInfo> cacheMap = new HashMap<>();
//
//    public String getCityNameById(String id) {
//
//        CityInfo cache = cacheMap.get(id);
//        if (cache == null) {
//            CityInfo info = findCityInfoById(id);
//            if (AppConfig.DEBUG) {
//                Log.d("MMM", "??  " + info);
//            }
//            if (info == null) {
//                return "";//FBaseApplication.INSTANCE.getString(R.string.location_unknow);
//            }
//            cacheMap.put(id, info);
//            cache = info;
//        }
//
//        if (AppConfig.DEBUG) {
//            Log.d("MMM", " " + id + "  " + cache.getName());
//        }
//        if (cache.getId().equals("1001_1000")) {
//            return cache.getName();
//        }
//        return cache.getList() == null ? cache.getLastNode().getName() + cache.getName() : cache.getName();
//    }
//
//    public CityInfo findCityInfoById(String id) {
//        if (mCityInfoList == null) {
//            return null;
//        }
//        if (TextUtils.isEmpty(id)) {
//            return null;
//        }
//        if (id.equals("1001_1000")) {
//            return mCityInfoList.getList().get(mCityInfoList.getList().size() - 1);
//        } else {
//            String country;
//            String pro = null;
//            String city = null;
//            if (id.contains("_")) {
//                String[] res = id.split("_");
//
//                country = res[0];
//                pro = country + "_" + res[1];
//                if (res.length > 2) {
//                    city = pro + "_" + res[2];
//                }
//
//            } else {
//                country = id;
//            }
//            if (AppConfig.DEBUG) {
//                Log.d("MMM", "id = " + id + "country = " + country + "  pro = " + pro + "  city = " + city + "  " + mCityInfoList.getId());
//            }
//            if (!mCityInfoList.getId().equals(country)) {
//                return null;
//            }
//
//            List<CityInfo> chinaList = mCityInfoList.getList();
//            for (int i = 0; i < chinaList.size(); i++) {
//                final CityInfo province = chinaList.get(i);
//
//                if (province.getId().equals(pro)) {
//                    if (city == null) {
//                        if (AppConfig.DEBUG) {
//                            Log.d("MMM", "" + province.getAdcode());
//                        }
//
//                        return province;
//                    }
//                    for (int j = 0; j < province.getList().size(); j++) {
//                        final CityInfo cityInfo = province.getList().get(j);
//                        if (cityInfo.getId().equals(city)) {
//                            if (AppConfig.DEBUG) {
//                                Log.d("MMM", "" + cityInfo.getAdcode());
//                            }
//
//                            return cityInfo;
//                        }
//                    }
//                    return province;
//                }
//            }
//            return mCityInfoList;
//        }
//
//
//    }

    /**
     * 通过AdCode查找城市
     *
     * @param adcode
     * @return
     */
    private CityInfo findCityInfoByAdCode(String adcode) {
        if (TextUtils.isEmpty(adcode)) {
            return null;
        }
        if (adcode.length() != 6) {
            return null;
        }
        if (mCityInfoList == null) {
            return null;
        }
        if (adcode.equals("900000")) {
            return null;
        }
        String sub = adcode.substring(0, 2);
        List<CityInfo> chinaList = mCityInfoList.getList();
        for (int i = 0; i < chinaList.size(); i++) {
            final CityInfo province = chinaList.get(i);
            if (province.getAdcode().equals(sub)) {
                for (int j = 0; j < province.getList().size(); j++) {
                    final CityInfo city = province.getList().get(j);
                    if (city.getAdcode().equals(adcode)) {
                        return city;
                    }
                }
                return province;
            }
        }
        return mCityInfoList;
    }


}
