package huang.bling.smartbus.ui.Passenger;

import huang.bling.smartbus.bean.PassagerBean;
import huang.bling.smartbus.ui.Map.Coordinate;

public interface PassengerContract {
    interface Model{
        void requestNewData();
        void connectServer();
        PassagerBean setTakeBus(int x, int y);
        Coordinate updateLocation();
        void disconnectServer();
        PassengerData getData();
    }

    interface View{
        void updateUI(PassengerData pd);
        int[] getDefault();
    }

    interface Presenter{
        void startWork();
        void endWork();
        void setVM(View v, Model m);
    }
}

