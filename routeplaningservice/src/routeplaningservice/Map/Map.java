package routeplaningservice.Map;

import java.util.HashMap;


public class Map {
    // todo: define map coordinate
    //Constant
    final float[] stopsCoorX = {10,70,40,20,70,50,30,80};
    final float[] stopsCoorY = {10,20,40,60,70,80,90,80};


    static Map map;
    HashMap<Integer, Stop> stops;

    public static Map getInstance(){
        if(map == null){
            map = new Map();
        }
        return map;
    }
    public Coordinate getStopCoordinate(int stopNum){
        return stops.get(stopNum).coordinate;
    }



    private Map(){
        initStops();
    }

    //todo: config stops x,y here
    private void initStops(){
        stops = new HashMap<>();
        for(int i = 0;i<8;i++){
            stops.put(i+1,new Stop(stopsCoorX[i],stopsCoorY[i]));
        }
    }
}
