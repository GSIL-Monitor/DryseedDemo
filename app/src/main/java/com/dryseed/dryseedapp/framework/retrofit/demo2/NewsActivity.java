package com.dryseed.dryseedapp.framework.retrofit.demo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.framework.retrofit.demo2.adapter.NewsAdapter;
import com.dryseed.dryseedapp.framework.retrofit.demo2.api.NewsApi;
import com.dryseed.dryseedapp.framework.retrofit.demo2.entity.NewsEntity;
import com.dryseed.dryseedapp.framework.retrofit.demo2.entity.NewsResultEntity;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Retrofit + RxJava2
 */
public class NewsActivity extends BaseActivity {

    private int mCurrentPage = 1;
    private NewsAdapter mNewsAdapter;
    private List<NewsResultEntity> mNewsResultEntities = new ArrayList<>();
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_news_layout);
        initView();
    }

    private void initView() {
        Button btRefresh = (Button) findViewById(R.id.bt_refresh);
        btRefresh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                refreshArticle(++mCurrentPage);
            }
        });
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_news);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mNewsAdapter = new NewsAdapter(mNewsResultEntities);
        recyclerView.setAdapter(mNewsAdapter);
        refreshArticle(++mCurrentPage);
    }

    private void refreshArticle(int page) {
        Observable<List<NewsResultEntity>> observable =
                Observable
                        .just(page)
                        .subscribeOn(Schedulers.io())
                        .flatMap(new Function<Integer, ObservableSource<List<NewsResultEntity>>>() {

                            @Override
                            public ObservableSource<List<NewsResultEntity>> apply(Integer page) throws Exception {
                                Observable<NewsEntity> androidNews = getObservable("Android", page);
                                Observable<NewsEntity> iosNews = getObservable("iOS", page);
                                return Observable.zip(androidNews, iosNews, new BiFunction<NewsEntity, NewsEntity, List<NewsResultEntity>>() {

                                    @Override
                                    public List<NewsResultEntity> apply(NewsEntity androidEntity, NewsEntity iosEntity) throws Exception {
                                        List<NewsResultEntity> result = new ArrayList<>();
                                        result.addAll(androidEntity.getResults());
                                        result.addAll(iosEntity.getResults());
                                        return result;
                                    }
                                });
                            }
                        });
        DisposableObserver<List<NewsResultEntity>> disposable = new DisposableObserver<List<NewsResultEntity>>() {

            @Override
            public void onNext(List<NewsResultEntity> value) {
                mNewsResultEntities.clear();
                mNewsResultEntities.addAll(value);
                mNewsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof UnknownHostException) {
                    Toast.makeText(NewsActivity.this, "无网络连接", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onComplete() {

            }
        };
        observable.observeOn(AndroidSchedulers.mainThread()).subscribe(disposable);
        mCompositeDisposable.add(disposable);
    }

    private Observable<NewsEntity> getObservable(String category, int page) {
        NewsApi newsApi = RetrofitProvider.getRetrofit().create(NewsApi.class);
        return newsApi.getNews(category, 10, page);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }
}
