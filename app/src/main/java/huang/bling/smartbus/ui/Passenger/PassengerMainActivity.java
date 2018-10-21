package huang.bling.smartbus.ui.Passenger;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import huang.bling.smartbus.CoordinateConvert;
import huang.bling.smartbus.R;
import huang.bling.smartbus.ui.Map.Coordinate;

public class PassengerMainActivity extends AppCompatActivity implements PassengerContract.View, View.OnTouchListener {
    final float[] stopsCoorX = {10,70,40,20,70,50,30,80};
    final float[] stopsCoorY = {10,20,40,60,70,80,90,80};
    private Paint mPaint;
    private float canvas_height;
    private float canvas_width;
    private float unit_width;
    private float unit_height;
    private List<Coordinate> coordinates;
    private LinearLayout canvas_ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_passenger);
        initView();
    }
    private void initView(){

        Toolbar toolBar= (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        toolBar.setTitleTextColor(getResources().getColor(R.color.white));
        mPaint = new Paint();
        canvas_ll=(LinearLayout) findViewById(R.id.canvas_ll);
        unit_height=704/100;
        unit_width=720/100;
        coordinates=new ArrayList<Coordinate>();
        for(int i=0;i<8;i++){
            Coordinate coordinate=new Coordinate(0,0);
            coordinate.x=stopsCoorX[i];
            coordinate.y=stopsCoorY[i];
            coordinates.add(coordinate);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_drawer,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.start:
                break;
            case R.id.end:
                break;
            case R.id.nav_about:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * 获取点击事件
     */
    @CallSuper
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_MOVE ) {
            View view = getCurrentFocus();
            if (isShouldHideKeyBord(view, ev)) {
                hideSoftInput(view.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 判定当前是否需要隐藏
     */
    protected boolean isShouldHideKeyBord(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            return !(ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom);
        }
        return false;
    }

    /**
     * 隐藏软键盘
     */
    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private void initListener(){
        canvas_ll.setOnTouchListener(this);
    }
//    @Override
//    public void updateUi(PassagerBean data) {
//        Coordinate curCoor=data.curCoor;
//        CoordinateConvert coordinateConvert=new CoordinateConvert(canvas_width,canvas_height);
//        int nextStopNum=data.nextStopNum-1;
//        int nNStopNum=data.nnStopNum-1;
//        DrawView drawView=new DrawView(this);
//        drawView.drawCircle(unit_width*curCoor.x,unit_height*curCoor.y,20,Color.RED);
//        drawView.drawCircle(unit_width*coordinates.get(nextStopNum).x,unit_height*coordinates.get(nextStopNum).y,20,Color.GREEN);
//        drawView.drawCircle(unit_width*coordinates.get(nNStopNum).x,unit_height*coordinates.get(nNStopNum).y,20,Color.BLUE);
//        drawView.drawLine(unit_width*coordinateConvert.getOrigin(),unit_height*coordinateConvert.getOrigin(),unit_width*coordinates.get(nextStopNum).x,unit_height*coordinates.get(nextStopNum).y,Color.GREEN);
//        drawView.drawLine(unit_width*coordinates.get(nextStopNum).x,unit_height*coordinates.get(nextStopNum).y,unit_width*coordinates.get(nNStopNum).x,unit_height*coordinates.get(nNStopNum).y,Color.YELLOW);
//        canvas_ll.addView(drawView);
//        drawView.invalidate();
//        int nNBusStopLocaton=data.getnNBusStopLocaton();
//    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                float x=event.getX();
                float y=event.getY();
                CoordinateConvert coordinateConvert=new CoordinateConvert(canvas_width,canvas_height);
//                Coordinate coordinate=coordinateConvert.getStandardCoordinate(x,y);
//                for(int i=0;i<coordinates.size();i++){
//                    if(coordinate.countDistance(coordinates.get(i))<5){
//
//                    }
//                }
                break;


            default:
                break;
        }
return true;
    }

    @Override
    public void updateUI(PassengerData pd) {

    }

    @Override
    public int[] getDefault() {
        return new int[0];
    }
}
