package huang.bling.smartbus.ui.Passenger;

import android.os.Handler;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

import huang.bling.smartbus.bean.PassagerBean;
import huang.bling.smartbus.global.Constant;
import huang.bling.smartbus.ui.Map.Coordinate;

public class PassengerPresenter implements PassengerContract.Presenter{
    private PassengerContract.View mView;
    private PassengerContract.Model mModel;
    Timer timer;
    Handler updateHandler;
    Coordinate cd;
    PassagerBean pb;

    public PassengerPresenter(){
        updateHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {

                mView.updateUI(mModel.getData());
            }
        };
    }

    @Override
    public void startWork() {
        int[] temp = mView.getDefault();
        mModel.setTakeBus(temp[0], temp[1]);
        setTimerTask();
    }

    @Override
    public void endWork() {
        cancelTimerTask();
    }

    @Override
    public void setVM(PassengerContract.View v, PassengerContract.Model m) {
        mView = v;
        mModel = m;
    }
    private void setTimerTask(){
        TimerTask task = new PassengerTimerTask();
        timer = new Timer();
        timer.schedule(task,0, Constant.PassengerUiUpateInterval);
    }
    private void cancelTimerTask(){
        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }

    class PassengerTimerTask extends TimerTask{
        @Override
        public void run() {
            cd = mModel.updateLocation();
            mModel.requestNewData();
            updateHandler.sendMessage(null);
        }
    }
}
