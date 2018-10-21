package huang.bling.smartbus.ui.Passenger;

import com.google.gson.Gson;

import huang.bling.smartbus.bean.PassagerBean;
import huang.bling.smartbus.global.NetworkParam;
import huang.bling.smartbus.ui.Map.Coordinate;
import huang.bling.smartbus.util.NetUtil;

public class PassengerModel implements PassengerContract.Model{
    Gson gson = new Gson();
    PassagerBean  passagerBean;
    Coordinate cd;
    PassengerModel(){

        passagerBean=new PassagerBean();
    }
    @Override
    public void requestNewData() {
        passagerBean.setType("U");
        String strToSend=gson.toJson(passagerBean);
        String result=NetUtil.sendAndReceive(strToSend,NetworkParam.ip, NetworkParam.PassengerPORT,NetworkParam.PassengerRECEIVE_PORT);
        if(result.equals("Error")) {
            System.out.println("getPassengerInfo failure, update fail");
        }else{
            passagerBean = gson.fromJson(result,PassagerBean.class);
        }

    }

    @Override
    public void connectServer() {
    }

    @Override
    public PassagerBean setTakeBus(int x, int y ) {
        passagerBean.setCBusStopLocation(x);
        passagerBean.setNBusStopLocation(y);
        passagerBean.setType("R");
        String strToSend=gson.toJson(passagerBean);
        String result=NetUtil.sendAndReceive(strToSend,NetworkParam.ip, NetworkParam.PassengerPORT,NetworkParam.PassengerRECEIVE_PORT);
        if(result.equals("Error")) {
            System.out.println("getPassengerInfo failure, update fail");
            return null;
        }
        passagerBean = gson.fromJson(result,PassagerBean.class);
        return passagerBean;

    }

    @Override
    public Coordinate updateLocation() {
        String result=NetUtil.sendAndReceive("2",NetworkParam.ip, NetworkParam.GPSSendPort,NetworkParam.GPSRecvPort);
        cd = gson.fromJson(result,Coordinate.class);
        return cd;
    }

    @Override
    public void disconnectServer() {

    }

    @Override
    public PassengerData getData() {

        return new PassengerData(cd,passagerBean.getnDriverLocation(),passagerBean.getNnDriverLocation());
    }
}
