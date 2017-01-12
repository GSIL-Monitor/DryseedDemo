package com.dryseed.dryseedapp.canvas.canvas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dryseed.dryseedapp.R;

/*

图像相关：
setARGB(int a,int r,int g,int b); 	设置绘制的颜色，a代表透明度，r，g，b代表颜色值。
setAlpha(int a);        设置绘制图形的透明度。
setColor(int color);	设置绘制的颜色，使用颜色值来表示，该颜色值包括透明度和RGB颜色。
setAntiAlias(boolean aa);	设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。
setDither(boolean dither); 	设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
setFilterBitmap(boolean filter); 	如果该项设置为true，则图像在动画进行中会滤掉对Bitmap图像的优化操作，加快显示速度，本设置项依赖于dither和xfermode的设置
setMaskFilter(MaskFilter maskfilter);	设置MaskFilter，可以用不同的MaskFilter实现滤镜的效果，如滤化，立体等
setColorFilter(ColorFilter colorfilter);	设置颜色过滤器，可以在绘制颜色时实现不用颜色的变换效果
setPathEffect(PathEffect effect);	设置绘制路径的效果，如点画线等
setShader(Shader shader);	设置图像效果，使用Shader可以绘制出各种渐变效果
setShadowLayer(float radius ,float dx,float dy,int color);	在图形下面设置阴影层，产生阴影效果，radius为阴影的角度，dx和dy为阴影在x轴和y轴上的距离，color为阴影的颜色
setStyle(Paint.Style style);	设置画笔的样式，为FILL，FILL_OR_STROKE，或STROKE
setStrokeCap(Paint.Cap cap);	当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的图形样式，如圆形样Cap.ROUND,或方形样式Cap.SQUARE
setSrokeJoin(Paint.Join join); 	设置绘制时各图形的结合方式，如平滑效果等
setStrokeWidth(float width);	当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的粗细度
setXfermode(Xfermode xfermode); 设置图形重叠时的处理方式，如合并，取交集或并集，经常用来制作橡皮的擦除效果

文字相关：
setFakeBoldText(boolean fakeBoldText); 模拟实现粗体文字，设置在小字体上效果会非常差
setSubpixelText(boolean subpixelText); 设置该项为true，将有助于文本在LCD屏幕上的显示效果
setTextAlign(Paint.Align align); 设置绘制文字的对齐方向
setTextScaleX(float scaleX);	设置绘制文字x轴的缩放比例，可以实现文字的拉伸的效果
setTextSize(float textSize); 设置绘制文字的字号大小
setTextSkewX(float skewX); 设置斜体文字，skewX为倾斜弧度
setTypeface(Typeface typeface);	设置Typeface对象，即字体风格，包括粗体，斜体以及衬线体，非衬线体等
setUnderlineText(boolean underlineText);	设置带有下划线的文字效果
setStrikeThruText(boolean strikeThruText); 设置带有删除线的效果

 */
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