/*
package com.dryseed.dryseedapp.lib.zxing.client.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dryseed.dryseedapp.R;
import com.facebook.drawee.view.SimpleDraweeView;

*/
/**
 * Created by yibin6 on 2016/3/3.
 *//*

public class CropActivity extends Activity{

    private SimpleDraweeView scan_exit,iv_crop_src;
    private TextView tv_recapture;
    private Button btn_crop_confirm;
    private CropDragView cropDragView;
    private RelativeLayout layout_root;

    //view上的坐标点
    private int mOriLeft;
    private int mOriRight;
    private int mOriTop;
    private int mOriBottom;

    //bitmap上的坐标点
    private int mBmLeft;
    private int mBmRight;
    private int mBmTop;
    private int mBmBottom;

    private int bmWidth,bmHeight;
    private int vWidth,vHeight;
    private int fitWidth,fitHeight;
    private static float ratioX,ratioY,ratio;
    private String mainBodyRectangle;

    public static boolean isFirst = true;//图片是否第一次进入圈选页剪切


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);

        initViews();

        */
/*vWidth = getResources().getDisplayMetrics().widthPixels;
        vHeight = getResources().getDisplayMetrics().heightPixels;*//*



        tv_recapture.setOnClickListener(new ViewOnClickListener());
        btn_crop_confirm.setOnClickListener(new ViewOnClickListener());
        scan_exit.setOnClickListener(new ViewOnClickListener());
    }

    private void handleIntent(Intent intent) {
        if (intent == null) {
            return;
        }
        byte[] bytes = getIntent().getByteArrayExtra("bitmap");
        if (bytes == null) {
            finish();
            ToastUtils.shortToast(CropActivity.this,"圈选失败!");
            return;
        }
        Matrix matrix = new Matrix();
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        */
/*if (!getIntent().getBooleanExtra("isAlbum",false)) {
            matrix.setRotate(90);
            bmWidth = bitmap.getHeight();
            bmHeight = bitmap.getWidth();
        }else {
            bmWidth = bitmap.getWidth();
            bmHeight = bitmap.getHeight();
        }*//*


        bmWidth = bitmap.getWidth();
        bmHeight = bitmap.getHeight();


        mainBodyRectangle = getIntent().getStringExtra("mainBodyRectangle");
        if (!TextUtils.isEmpty(mainBodyRectangle)) {
            parseMainBodyRectangle(mainBodyRectangle);
        }

        fitWH();
//        iv_crop_src.setLayoutParams(new ViewGroup.LayoutParams(fitWidth,fitHeight));
        ViewGroup.LayoutParams lp = iv_crop_src.getLayoutParams();
        lp.width = fitWidth;
        lp.height = fitHeight;
        iv_crop_src.setLayoutParams(lp);
        iv_crop_src.setImageBitmap(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true));

        cropDragView.setFitWH(fitWidth,fitHeight);
    }

    private void fitWH() {
        if (ratioX > ratioY) {
            fitHeight = vHeight;
            fitWidth = (int) (bmWidth * ratioY);
        }else {
            fitWidth = vWidth;
            fitHeight = (int) (bmHeight * ratioX);
        }
    }

    private void parseMainBodyRectangle(String mainBodyRectangle) {//"122,20|800,620"
        String[] points = mainBodyRectangle.split("\\|");
        mBmLeft = Integer.parseInt(points[0].split(",")[0]);
        mBmTop = Integer.parseInt(points[0].split(",")[1]);
        mBmRight = Integer.parseInt(points[1].split(",")[0]);
        mBmBottom = Integer.parseInt(points[1].split(",")[1]);

        ratioX = (float) vWidth / bmWidth;
        ratioY = (float) vHeight / bmHeight;
        ratio = ratioX < ratioY ? ratioX : ratioY;

        mOriLeft = (int) (mBmLeft * ratio);
        mOriTop = (int) (mBmTop * ratio);
        mOriRight = (int) (mBmRight * ratio);
        mOriBottom = (int) (mBmBottom * ratio);

        if (!isFirst) {//第一次进入圈选页cropDragView的mOriLeft, mOriTop, mOriRight, mOriBottom为默认值
            cropDragView.setmOri(mOriLeft, mOriTop, mOriRight, mOriBottom);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        */
/*vWidth = cropDragView.getWidth();
        vHeight = cropDragView.getHeight();*//*


        */
/*ratioX = (float) bmWidth / vWidth;
        ratioY = (float) bmHeight / vHeight;*//*

    }

    private void initViews() {
        iv_crop_src = (SimpleDraweeView) findViewById(R.id.iv_crop_src);
        scan_exit = (SimpleDraweeView) findViewById(R.id.scan_exit);
        tv_recapture = (TextView) findViewById(R.id.tv_recapture);
        btn_crop_confirm = (Button) findViewById(R.id.btn_crop_confirm);
        cropDragView = (CropDragView) findViewById(R.id.cropDragView);
        layout_root = (RelativeLayout) findViewById(R.id.layout_root);
        layout_root.setBackgroundColor(Color.BLACK);//修复修改字体后重启小米2s白屏问题
        layout_root.post(new Runnable() {
            @Override
            public void run() {
                vWidth = layout_root.getWidth();
                vHeight = layout_root.getHeight();
                handleIntent(getIntent());

                cropDragView.setBtn(btn_crop_confirm);
                cropDragView.setFlag(isFirst);
                isFirst = false;
            }
        });


    }

    class ViewOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.scan_exit:
                    //TODO
                    crop();
                    break;
                case R.id.tv_recapture:
                    isFirst = true;
                    Intent intent = new Intent(CropActivity.this,CaptureActivity.class);
                    intent.putExtra("position",1);
                    startActivity(intent);
                    JDMtaUtils.onClick(CropActivity.this,"CameraPurchase_ReTake","");
                    break;
                case R.id.btn_crop_confirm:
                    crop();
                    JDMtaUtils.onClick(CropActivity.this,"CameraPurchase_ConfirmPhoto","");
                    break;
                default:
                    break;
            }
        }
    }

    private void crop() {
        mOriLeft = cropDragView.getmOriLeft();
        mOriTop = cropDragView.getmOriTop();
        mOriRight = cropDragView.getmOriRight();
        mOriBottom = cropDragView.getmOriBottom();

        mBmLeft = (int) (mOriLeft / ratio);
        mBmTop = (int) (mOriTop / ratio);
        mBmRight = (int) (mOriRight / ratio);
        mBmBottom = (int) (mOriBottom / ratio);
        Intent intent = new Intent(CropActivity.this, PhotoBuyActivity.class);
        intent.putExtra("bmpByte",getIntent().getByteArrayExtra("bitmap"));
        intent.putExtra("isAlbum",getIntent().getBooleanExtra("isAlbum",false));
        intent.putExtra("mainBodyRectangle",mBmLeft + "," + mBmTop + "|" + mBmRight + "," + mBmBottom);//"122,20|800,620"
        intent.putExtra("isFromCrop", 1);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        crop();
    }
}
*/
