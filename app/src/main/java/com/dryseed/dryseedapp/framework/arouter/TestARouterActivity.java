//package com.dryseed.dryseedapp.framework.arouter;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//
//import com.alibaba.android.arouter.facade.annotation.Route;
//import com.alibaba.android.arouter.launcher.ARouter;
//import com.dryseed.dryseedapp.BaseActivity;
//import com.dryseed.dryseedapp.R;
//
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
//@Route(path = "/framework/TestARouterActivity")
//public class TestARouterActivity extends BaseActivity {
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_arouter_layout);
//        ButterKnife.bind(this);
//    }
//
//    @OnClick(R.id.btn1)
//    void goTestButterKnifeActivity() {
//        ARouter.getInstance().build("/framework/TestButterKnifeActivity").navigation();
//    }
//
//    @OnClick(R.id.btn2)
//    void goTestEventBusActivity() {
//        ARouter.getInstance().build("/framework/TestARouterWithParamActivity")
//                .withLong("key1", 666L)
//                .withString("key2", "888")
//                //.withObject("key4", new Test("Jack", "Rose"))
//                .navigation();
//    }
//}
