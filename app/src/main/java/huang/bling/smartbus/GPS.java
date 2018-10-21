package huang.bling.smartbus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import huang.bling.smartbus.global.NetworkParam;
import huang.bling.smartbus.ui.Map.Coordinate;
import huang.bling.smartbus.util.NetUtil;

public class GPS {
    public int type;
    private static Gson gson = new GsonBuilder().create();
    public GPS(int tyep){
        this.type = type;
    }

    public Coordinate getLocation(){
        String s = NetUtil.sendAndReceive(String.valueOf(type),
                NetworkParam.ip,
                NetworkParam.GPSSendPort,
                NetworkParam.GPSRecvPort);
        return gson.fromJson(s,Coordinate.class);
    }
}
