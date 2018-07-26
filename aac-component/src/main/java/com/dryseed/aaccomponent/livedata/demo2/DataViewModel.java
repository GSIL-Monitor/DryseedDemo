package com.dryseed.aaccomponent.livedata.demo2;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import com.luojilab.component.basiclib.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caiminming
 */
public class DataViewModel extends ViewModel {
    //这里创建一个 MutableLiveData，<?> 为要提供的数据类型，这里我们声明为 List。
    private MutableLiveData<List<String>> mWatcher;
    private Handler mWorkHandler;

    /**
     * 加载数据，在实际当中，加载数据的操作要放在 Repository 中进行，而不要放在 Model 中，
     * 它只是负责数据和 UI 的交互过程。
     */
    public void load() {
        if (mWorkHandler == null) {
            HandlerThread thread = new HandlerThread("DataViewModel");
            thread.start();
            mWorkHandler = new Handler(thread.getLooper());
        }
        mWorkHandler.post(new Runnable() {

            @Override
            public void run() {
                //模拟加载数据的过程。
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                List<String> result = makeResult();
                setResults(result);
            }

        });
    }

    /**
     * 获取数据的监控者。
     *
     * @return 监控者。
     */
    public MutableLiveData<List<String>> getWatcher() {
        LogUtil.d("Call getWatcher");
        if (mWatcher == null) {
            mWatcher = new MutableLiveData<>();
        }
        return mWatcher;
    }

    /**
     * 设置数据。
     *
     * @param results 设置数据。
     */
    private void setResults(List<String> results) {
        LogUtil.d("Call setResults");
        //当数据加载完以后，调用 setValue/postValue 方法设置数据。
        if (Looper.getMainLooper() == Looper.myLooper()) {
            getWatcher().setValue(results);
        } else {
            getWatcher().postValue(results);
        }
    }

    private List<String> makeResult() {
        List<String> result = new ArrayList<>();
        result.add("苹果 - 1");
        result.add("苹果 - 2");
        result.add("苹果 - 3");
        result.add("苹果 - 4");
        result.add("苹果 - 5");
        result.add("苹果 - 6");
        return result;
    }
}
