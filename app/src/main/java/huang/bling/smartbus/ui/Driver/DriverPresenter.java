package huang.bling.smartbus.ui.Driver;

import android.os.Handler;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

import huang.bling.smartbus.global.Constant;

public class DriverPresenter  implements DriverContract.Presenter{
    DriverContract.Model mModel;
    DriverContract.View mView;
    private Timer timer;
    Handler updateHandler;

    DriverPresenter(){
        updateHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                DriverData data = mModel.getData();
                mView.updateUi(data);
            }
        };
        timer = null;
    }

    @Override
    public void startWork() {
        setTimerTask();
    }

    @Override
    public void endWork() {
        cancelTimerTask();
    }

    @Override
    public void setVM(DriverContract.View v, DriverContract.Model m) {
        mView = v;
        mModel = m;
    }

    private void setTimerTask(){
        if(timer == null){
            TimerTask task = new DriverTimerTask();
            timer = new Timer();
            timer.schedule(task,Constant.DriverUiUpdateInterval, Constant.DriverUiUpdateInterval);
        }
    }
    private void cancelTimerTask(){
        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }

    class DriverTimerTask extends TimerTask{
        @Override
        public void run() {
            mModel.updateLocation();
            updateHandler.sendMessage(new Message());
            if(mModel.getCurrentStop() == 8){
                cancelTimerTask();
            }
        }
    }
}

