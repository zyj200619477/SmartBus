package huang.bling.smartbus.ui.Driver;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import huang.bling.smartbus.CoordinateConvert;
import huang.bling.smartbus.DrawView;
import huang.bling.smartbus.R;
import huang.bling.smartbus.ui.Map.Coordinate;

public class DriverMainActivity extends AppCompatActivity implements DriverContract.View{
    DriverContract.Model mModel;
    DriverContract.Presenter mPresenter;
    private Paint mPaint;
    private TextView nextstop;
    final float[] stopsCoorX = {10,70,40,20,70,50,30,80};
    final float[] stopsCoorY = {10,20,40,60,70,80,90,80};
    private float canvas_height;
    private float canvas_width;
    private float unit_width;
    private float unit_height;
    private List<Coordinate> coordinates;
    private LinearLayout canvas_ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_driver);
        nextstop=((TextView)findViewById(R.id.nextstop_tv));
        mModel = new DriverModel();
        mPresenter = new DriverPresenter();
        mPresenter.setVM(this,mModel);
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
                //updateUi(new DriverData(Map.getInstance(),new Coordinate(10,10),2,3));
                mPresenter.startWork();
                return true;
            case R.id.end:
                //updateUi(new DriverData(Map.getInstance(),new Coordinate(50,50),2,3));

                mPresenter.endWork();
                return true;
            case R.id.nav_about:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void updateUi(DriverData data) {
        //uiUtil.makeShortToast(this,"update Driver ui");
        System.out.printf(" %f %f\n",data.curCoor.x,data.curCoor.y);
        nextstop.setText(String.valueOf(data.nextStopNum));
        Coordinate curCoor=data.curCoor;
        CoordinateConvert coordinateConvert=new CoordinateConvert(canvas_width,canvas_height);
        int nextStopNum=data.nextStopNum-1;
        int nNStopNum=data.nnStopNum-1;
        //drawView.removeAll();
        DrawView drawView=new DrawView(this);


        drawView.drawCircle(unit_width*curCoor.x,unit_height*curCoor.y,20,Color.RED);
        if(nextStopNum >=0){
            drawView.drawCircle(unit_width*coordinates.get(nextStopNum).x,unit_height*coordinates.get(nextStopNum).y,20,Color.GREEN);
            drawView.drawLine(unit_width*curCoor.x,unit_height*curCoor.y,unit_width*coordinates.get(nextStopNum).x,unit_height*coordinates.get(nextStopNum).y,Color.GREEN);
        }
        if(nNStopNum >= 0){
            drawView.drawCircle(unit_width*coordinates.get(nNStopNum).x,unit_height*coordinates.get(nNStopNum).y,20,Color.BLUE);
            drawView.drawLine(unit_width*coordinates.get(nextStopNum).x,unit_height*coordinates.get(nextStopNum).y,unit_width*coordinates.get(nNStopNum).x,unit_height*coordinates.get(nNStopNum).y,Color.YELLOW);
        }
         canvas_ll.removeAllViews();
        canvas_ll.addView(drawView);
        //canvas_ll.invalidate();
        drawView.invalidate();


//        int nNBusStopLocaton=data.getnNBusStopLocaton();
    }


}
