package huang.bling.smartbus;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author allen
 * Created by allen on 10/20/18.
 */
public class DrawView extends View {
    DrawView drawView;
    Canvas canvas;
    private List<Float> x;
    private List<Float> y;
    private List<Float> radius;
    private List<Integer> circleColor;
    private List<Float> x1;
    private List<Float> y1;
    private List<Float> x2;
    private List<Float> y2;
    private List<Float> x3;
    private List<Float> y3;
    private List<Integer> lineColor;
    private boolean circle=false;
    private boolean line=false;
    private boolean clear=false;
    public DrawView(Context context) {
        super(context);


        drawView=DrawView.this;
        x=new ArrayList<Float>();
        y=new ArrayList<Float>();
        radius=new ArrayList<Float>();
        circleColor=new ArrayList<Integer>();
        x1=new ArrayList<Float>();
        y1=new ArrayList<Float>();
        x2=new ArrayList<Float>();
        y2=new ArrayList<Float>();
        x3=new ArrayList<Float>();
        y3=new ArrayList<Float>();
        lineColor=new ArrayList<Integer>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas=canvas;
        Paint mPaint = new Paint();
        mPaint.setStrokeWidth(10f);
        if(circle){
            Log.e("canvas","draw circle");
            for(int i=0;i<x.size();i++) {
                mPaint.setColor(circleColor.get(i));
                canvas.drawCircle(x.get(i), y.get(i), radius.get(i), mPaint);
            }
        }
        if(line){
            Log.e("line","draw line");
            for(int i=0;i<x1.size();i++) {
                mPaint.setColor(lineColor.get(i));
                canvas.drawLine(x1.get(i), y1.get(i), x2.get(i), y2.get(i), mPaint);
            }
        }
        if(clear){
            canvas.drawColor(getResources().getColor(R.color.white));
            this.clear=false;

        }


    }
    public void drawCircle(float X,float Y,float r,int col){
        x.add(X);
        y.add(Y);
        radius.add(r);
        circleColor.add(col);
this.circle=true;
    }
    public void removeAll(){
        x.clear();
        y.clear();
        radius.clear();
        circleColor.clear();
        x1.clear();
        x2.clear();
        x3.clear();
        y1.clear();
        y2.clear();
        y3.clear();
        lineColor.clear();
    }
    public void drawLine(float X1,float Y1,float X2,float Y2,int color){
        x1.add(X1);
        y1.add(Y1);
        x2.add(X2);
        y2.add(Y2);
       lineColor.add(color);
        this.line=true;
    }
    public void clearCanvas(){
        this.clear=true;
    }


}
