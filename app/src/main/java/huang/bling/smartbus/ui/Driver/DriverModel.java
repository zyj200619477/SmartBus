package huang.bling.smartbus.ui.Driver;

import huang.bling.smartbus.GPS;
import huang.bling.smartbus.bean.DriverBean;
import huang.bling.smartbus.global.Constant;
import huang.bling.smartbus.ui.Map.Coordinate;
import huang.bling.smartbus.ui.Map.Map;
import huang.bling.smartbus.util.DriverNetImpl;

public class DriverModel implements DriverContract.Model{

    DriverNetImpl netConnect;
    GPS gps;
    public int curStop;
    int nextStop;
    int nnStop;
    Map map;
    Coordinate coor;
    DriverModel(){
        netConnect = new DriverNetImpl();
        map = Map.getInstance();
        gps = new GPS(Constant.DriverGPSType);
        curStop = 1;
        nextStop = 2;
        nnStop = 5;
    }

    int i = 2;
    @Override
    public void updateLocation(){


        coor = gps.getLocation();
        System.out.printf("nextStop: %d, nextStop x %f y %f, cur corr x: %f  %f. distance %f\n",nextStop,map.getStopCoordinate(nextStop).x,
                map.getStopCoordinate(nextStop).y,coor.x,coor.y,
                coor.countDistance(map.getStopCoordinate(nextStop)));
        if(map.isCloseEnough(coor,map.getStopCoordinate(nextStop))){
            System.out.println("!!!!!!! update location");
            curStop = nextStop;
            requestNewData();
        }
    }

    @Override
    public void requestNewData() {

        DriverBean driverBean = netConnect.getDriverResponse(curStop);
        //DriverBean driverBean = new DriverBean();
//        driverBean.setCBusStopLocation(1);
//        driverBean.setNBusStopLocation(2);
//        driverBean.setnNBusStopLocaton(3);
        nextStop = driverBean.getnNBusStopLocaton();
        nnStop = driverBean.getnNBusStopLocaton();
    }

    @Override
    public void connectServer() {
    }

    @Override
    public void disconnectServer() {
    }

    //todo getData interface
    @Override
    public DriverData getData() {
        return new DriverData(map,coor,nextStop,nnStop);
    }

    @Override
    public int getCurrentStop() {
        return 0;
    }

}
