package com.dryseed.dryseedapp.test.viewTreeObserver;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dryseed.dryseedapp.R;
import com.orhanobut.logger.Logger;

/**
 * Created by caiminming on 2017/5/26.
 * http://blog.csdn.net/liguangzhenghi/article/details/8076121
 */

public class TestViewTreeObserverActivity extends Activity implements
        View.OnClickListener,
        ViewTreeObserver.OnTouchModeChangeListener,          // 用于监听 Touch 和非 Touch 模式的转换
        ViewTreeObserver.OnGlobalLayoutListener,             // 用于监听布局之类的变化，比如某个空间消失了
        ViewTreeObserver.OnPreDrawListener,                  // 用于在屏幕上画 View 之前，要做什么额外的工作
        ViewTreeObserver.OnGlobalFocusChangeListener         // 用于监听焦点的变化
{

    private TextView tv_show;
    private ViewTreeObserver vto;
    private View all;
    private EditText ed1;
    private EditText ed2;
    private TextView tv_display;
    private Button button;
    private boolean btnClicked;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tree_ovserver);
        tv_show = (TextView) this.findViewById(R.id.tv_show);
        all = this.findViewById(R.id.full_screen);                  // 得到整个屏幕对象 ， 因为顶层 layout 的 width 和 height 都是 fill_parent
        vto = (ViewTreeObserver) all.getViewTreeObserver();         // 通过 getViewTreeObserver 获得 ViewTreeObserver 对象
        tv_display = (TextView) this.findViewById(R.id.tv_display);
        ed1 = (EditText) this.findViewById(R.id.ed_enter1);
        ed2 = (EditText) this.findViewById(R.id.ed_enter2);
        button = (Button) this.findViewById(R.id.button);
        button.setOnClickListener(this);

        vto.addOnTouchModeChangeListener(this);                      // 增加对应的 Listener
        vto.addOnGlobalFocusChangeListener(this);                    // 增加对应的 Listener
        vto.addOnPreDrawListener(this);                              // 增加对应的 Listener
        vto.addOnGlobalLayoutListener(this);                         // 增加对应的 Listener
    }


    // onTouchModeChanged 是接口 ViewTreeObserver.OnTouchModeChangeListener
    // 中定义的方法。
    @Override
    public void onTouchModeChanged(boolean isInTouchMode) {
        Log.d("MMM", "onTouchModeChanged");
        if (isInTouchMode) tv_show.setText("In touch mode");
        else tv_show.setText("Not in touch mode");
    }


    // onGlobalLayout 是接口 ViewTreeObserver.OnGlobalLayoutListener中定义的方法。
    // Callback method to be invokedwhen the global layout state or the
    // visibility of views within the view treechanges
    @Override
    public void onGlobalLayout() {
        Log.d("MMM", "onGlobalLayout");
        if (btnClicked) {
            if (!ed2.isShown())
                ed1.setText(" 第二个 EditText 不见了 ");
            else
                ed1.setText(" 第二个 EditText 出来了 ");
        }
    }


    // onPreDraw 是接口 ViewTreeObserver.OnPreDrawListener中定义的方法。
    @Override
    public boolean onPreDraw() {
        Log.d("MMM", "onPreDraw");
        // 在屏幕上画出 ed1 控件之间 ， 给它增加一个提示 ， 并改变其字体大小
        ed1.setHint(" 在 onPreDraw 方法中增加一个提示信息 ");
        ed1.setTextSize((float) 20.0);
        //return false;   // Return true to proceed with the current drawing pass, or falseto cancel.
        return true;       // 如果此处不返回 true ， 则整个界面不能完整显示。
    }

    // onGlobalFocusChanged 是接口 ViewTreeObserver.OnGlobalFocusChangeListener中定义的方法。
    // 焦点发生变化时，会触发这个方法的执行
    @Override
    public void onGlobalFocusChanged(View oldFocus, View newFocus) {
        Log.d("MMM", "onGlobalFocusChanged");
        if (oldFocus != null && newFocus != null) {
            tv_display.setText("Focus /nFROM:/t" + oldFocus.toString() + "/n    TO:/t" + newFocus.toString());
        }
    }

    @Override
    public void onClick(View v) {
        // 改变 ed2 的可见性 ， 会触发 onGlobalLayout 方法的执行
        btnClicked = true;
        if (v.getId() == R.id.button) {
            if (ed2.isShown())
                ed2.setVisibility(View.INVISIBLE);
            else
                ed2.setVisibility(View.VISIBLE);
        }
    }

}

/*
应用场景1：获取View的宽高

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final MyImageView myImageView = (MyImageView) findViewById(R.id.imageview);
    int height = 0;
    int width =  0 ;
    ViewTreeObserver vto = myImageView.getViewTreeObserver();
    vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
        public boolean onPreDraw() {
            int height = myImageView.getMeasuredHeight();
            int width = myImageView.getMeasuredWidth();
            return true;
        }
    });
}

因为回调OnPreDrawListener的onPreDraw，表示这个View准备进行绘制，在绘制之前，这个View的宽高肯定是已经测量好了，所以这个时机是可以得到view的宽高的。



@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final MyImageView myImageView = (MyImageView) findViewById(R.id.imageview);
    int height = 0;
    int width =  0 ;

    ViewTreeObserver vto = myImageView.getViewTreeObserver();
    vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            myImageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            int height = myImageView.getHeight();
            int width = myImageView.getWidth();
        }
    });
}

当view的可见状态发生变化的时候回调OnGlobalLayoutListener的onGlobalLayout()函数，这个时候View的宽高肯定也是已经测量好了，所以这个时机是可以得到view的宽高的。



应用场景2：Activity跳转动画

ViewTreeObserver还可以用来监听根布局，用来实现Activity跳转动画，核心代码如下：

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second);
    rootView = findViewById(R.id.root);
    if (savedInstanceState == null) {
        rootView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                rootView.getViewTreeObserver().removeOnPreDrawListener(this);
                startRootAnimation();
                return true;
            }
        });
    }
}

因为在绘制之前会触发OnPreDrawListener的onPreDraw()函数，这个时候可以执行跳转动画。



应用场景3：测量软键盘状态和高度

ViewTreeObserver.OnGlobalLayoutListener mListener = new ViewTreeObserver
            .OnGlobalLayoutListener() {
        public void onGlobalLayout() {
            Rect r1 = new Rect();
            root.getWindowVisibleDisplayFrame(r1);
            Log.e("TAG",r1.bottom+"") ;
        }
    };
root.getViewTreeObserver().addOnGlobalLayoutListener(mListener);

在根布局加入GlobalLayoutListener监听，通过getWindowVisibleDisplayFrame方法可以观察可见区域的变化，键盘打开后 会影响可见区域的大小，导致Rect的底部r1.bottom变小。
 */
