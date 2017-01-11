package com.dryseed.dryseedapp.canvas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dryseed.dryseedapp.R;


public class TestCanvasActivity extends Activity implements Button.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_main_layout);
    }

    @Override
    public void onClick(View v) {
        MyCanvasView.DrawMode drawMode;
        switch (v.getId()){
            case R.id.btnDrawAxis:
                drawMode = MyCanvasView.DrawMode.AXIS;
                break;
            case R.id.btnDrawARGB:
                drawMode = MyCanvasView.DrawMode.ARGB;
                break;
            case R.id.btnDrawText:
                drawMode = MyCanvasView.DrawMode.TEXT;
                break;
            case R.id.btnDrawPoint:
                drawMode = MyCanvasView.DrawMode.POINT;
                break;
            case R.id.btnDrawLine:
                drawMode = MyCanvasView.DrawMode.LINE;
                break;
            case R.id.btnDrawRect:
                drawMode = MyCanvasView.DrawMode.RECT;
                break;
            case R.id.btnDrawCircle:
                drawMode = MyCanvasView.DrawMode.CIRCLE;
                break;
            case R.id.btnDrawOval:
                drawMode = MyCanvasView.DrawMode.OVAL;
                break;
            case R.id.btnDrawArc:
                drawMode = MyCanvasView.DrawMode.ARC;
                break;
            case R.id.btnDrawPath:
                drawMode = MyCanvasView.DrawMode.PATH;
                break;
            case R.id.btnDrawBitmap:
                drawMode = MyCanvasView.DrawMode.BITMAP;
                break;
            default:
                drawMode = MyCanvasView.DrawMode.UNKNOWN;
                break;
        }
        Intent intent = new Intent(this, CanvasActivity.class);
        intent.putExtra("drawMode", drawMode.value());
        startActivity(intent);
    }
}