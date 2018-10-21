package huang.bling.smartbus.ui.Map;

import java.util.HashMap;

import huang.bling.smartbus.global.Constant;

public class Map {
    static Map map;
    HashMap<Integer,Stop> stops;
    final float[] stopsCoorX = {10,70,40,20,70,50,30,80};
    final float[] stopsCoorY = {10,20,40,60,70,80,90,80};


    public static Map getInstance(){
        if(map == null){
            map = new Map();
        }
        return map;
    }
    public Coordinate getStopCoordinate(int stopNum){
        return stops.get(stopNum).coordinate;
    }



    public boolean isCloseEnough(Coordinate c1,Coordinate c2){
        return (c1.countDistance(c2) < Constant.SmallDistance);
    }

    private Map(){
        initStops();

    }

    //todo: config stops x,y here
    private void initStops(){
        stops = new HashMap<>();
        for(int i = 0;i<stopsCoorX.length;i++){
            stops.put(i+1,new Stop(stopsCoorX[i],stopsCoorY[i]));
        }
    }
}
