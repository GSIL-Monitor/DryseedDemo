package com.dryseed.dryseedapp.widget.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.tab.fragment.MainTab01;
import com.dryseed.dryseedapp.tab.fragment.MainTab02;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by caiminming on 2017/9/15.
 * <p>
 * Fragmen在各个情况下的生命周期
 * http://blog.csdn.net/htq__/article/details/51210306
 */

public class TestFragmentLifeCycle extends BaseActivity {

    private FragmentManager fragmentManager;
    Fragment fragmentOne;
    Fragment fragmentTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment_life_cycle_layout);
        ButterKnife.bind(this);

        fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentOne = new MainTab01();
        fragmentTransaction.replace(R.id.id_content, fragmentOne, fragmentOne.getClass().getName());
        fragmentTransaction.commit();
        // FragmentOne : onAttach -> onCreate -> onCreateView -> onViewCreated -> onActivityCreated -> onStart -> onResume
    }

    @OnClick(R.id.replace_b_btn)
    protected void onReplaceBBtnClick() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragmentTwo == null) {
            fragmentTwo = new MainTab02();
            fragmentTransaction.replace(R.id.id_content, fragmentTwo, fragmentTwo.getClass().getName());
        } else {
            Fragment fragment = fragmentManager.findFragmentByTag(fragmentTwo.getClass().getName());
            fragmentTransaction.replace(R.id.id_content, fragment, fragmentTwo.getClass().getName());
        }
        fragmentTransaction.commit();

        // 情况1：replace 没有调用addToBackStack
        // FragmentOne : onPause -> onStop -> onDestroyView -> onDestroy -> onDetach
        // FragmentTow : onAttach -> onCreate -> onCreateView -> onViewCreated -> onActivityCreated -> onStart -> onResume
        // 这说明，在使用replace添加Fragment时如果没有调用addToBackStack方式的话，当FragmentManager替换Fragment时，
        // 是不保存Fragment的状态的，此时第二个Fragment将会直接替换前一个Fragment。
    }

    @OnClick(R.id.replace_b_backstack_btn)
    protected void onReplaceBWithAddToBackStackBtnClick() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragmentTwo == null) {
            fragmentTwo = new MainTab02();
            fragmentTransaction.replace(R.id.id_content, fragmentTwo, fragmentTwo.getClass().getName());
            fragmentTransaction.addToBackStack(fragmentTwo.getClass().getName()); //addToBackStack
        } else {
            Fragment fragment = fragmentManager.findFragmentByTag(fragmentTwo.getClass().getName());
            fragmentTransaction.replace(R.id.id_content, fragment, fragmentTwo.getClass().getName());
        }
        fragmentTransaction.commit();

        // 情况2：replace 调用addToBackStack
        // FragmentOne : onPause -> onStop -> onDestroyView   少了onDestroy、onDetach
        // FragmentTow : onAttach -> onCreate -> onCreateView -> onViewCreated -> onActivityCreated -> onStart -> onResume
        // 这说明fragOne仅仅只是界面被销毁onDestroyView，而fragOne对象的实例依然被保存在FragmentManager中（因为无onDestroy，onDetach），它的部分状态依然被保存在FragmentManager中。
    }

    @OnClick(R.id.replace_a_backstack_btn)
    protected void onReplaceAWithAddToBackStackBtnClick() {
        // 测试：点击replace_b_backstack_btn后，再点击replace_a_backstack_btn
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragmentOne == null) {
            fragmentOne = new MainTab02();
            fragmentTransaction.replace(R.id.id_content, fragmentOne, fragmentOne.getClass().getName());
            fragmentTransaction.addToBackStack(fragmentOne.getClass().getName()); //addToBackStack
        } else {
            Fragment fragment = fragmentManager.findFragmentByTag(fragmentOne.getClass().getName());
            fragmentTransaction.replace(R.id.id_content, fragment, fragmentOne.getClass().getName());
        }
        fragmentTransaction.commit();

        // FragmentTwo : onPause -> onStop -> onDestroyView
        // FragmentOne : onCreateView -> onViewCreated -> onActivityCreated -> onStart -> onResume  少了onAttach、onCreate
        // 可以看到fragOne的生命周期是直接从onCreateView开始的，这也刚好对应上面的fragOne被fragTwo替换时生命周期到达onDestroyView，
        // 即之前的fragOne仅仅销毁了视图，而fragOne对象的实例依然被保存在FragmentManager中，所以此时只需要创建视图，即直接从onCreateView开始。
    }

    @OnClick(R.id.add_show_a_btn)
    protected void onAddAndShowABtnClick() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragmentTwo == null) {
            fragmentTwo = new MainTab02();
            fragmentTransaction.add(R.id.id_content, fragmentTwo, fragmentTwo.getClass().getName());
            fragmentTransaction.addToBackStack(fragmentTwo.getClass().getName()); //addToBackStack
        } else {
            fragmentTransaction.show(fragmentTwo);
        }
        fragmentTransaction.commit();

        // FragmentOne : 没有
        // FragmentTow : onAttach -> onCreate -> onCreateView -> onViewCreated -> onActivityCreated -> onStart -> onResume
    }
}
