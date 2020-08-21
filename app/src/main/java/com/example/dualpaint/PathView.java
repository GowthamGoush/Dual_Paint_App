package com.example.dualpaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class PathView extends View {

    private Path path = new Path();
    private Paint brush = new Paint();
    private OnCoordinateUpdate mCoordinatesListener;
    public Canvas canvas2;

    public PathView(Context context) {
        super(context);
        setWillNotDraw(false);
        init(null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        init(attrs);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        init(attrs);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setWillNotDraw(false);
        init(attrs);
    }

    public void init(AttributeSet set){
        brush.setAntiAlias(true);
        brush.setColor(Color.CYAN);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeWidth(8f);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(pointX,pointY);
                if(mCoordinatesListener != null) {
                    mCoordinatesListener.onUpdate(pointX,pointY,true);
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(pointX,pointY);
                if(mCoordinatesListener != null) {
                    mCoordinatesListener.onUpdate(pointX,pointY,false);
                }
                break;
            default:
                return false;
        }
        postInvalidate();
        return false;
    }

    public void setPathMove(float x , float y) {
        path.moveTo(x,y);
        invalidate();
    }

    public void setPathLine(float x , float y) {
        path.lineTo(x,y);
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas2 = canvas;
        canvas.drawPath(path,brush);
    }

    public void setCoordinatesListener(OnCoordinateUpdate listener) {
        mCoordinatesListener = listener;
    }

    public interface OnCoordinateUpdate {
        void onUpdate(float X,float Y,boolean isMove);
    }


}
