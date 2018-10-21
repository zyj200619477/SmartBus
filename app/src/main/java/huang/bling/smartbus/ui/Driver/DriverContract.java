package huang.bling.smartbus.ui.Driver;

public interface DriverContract {
    interface Model{
        void requestNewData();
        void connectServer();
        void disconnectServer();
        void updateLocation();
        DriverData getData();
        int getCurrentStop();
    }

    interface View{
        void updateUi(DriverData data);
    }

    interface Presenter{
        void startWork();
        void endWork();
        void setVM(View v, Model m);
    }
}
