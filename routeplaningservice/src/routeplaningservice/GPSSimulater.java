package routeplaningservice;

import routeplaningservice.Map.Coordinate;
import routeplaningservice.Map.Map;
import routeplaningservice.Map.Stop;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class GPSSimulater {
    // todo
    // Constant
    private final int userStartStop = 2;
    private final int userEndStop = 4;
    private final int[] stopsSequence = {1,2,3,5,4,6,7};
    private final long GPSRefreshInterval = 100;
    private final float driverMovingSpeed = (float)0.6; //per second
    private final float smallDistance = (float) 0.1;


    Map map;
    Timer timer;


    Coordinate userCoor;
    Coordinate driverCoor;
    long lastTime;
    int targetStopNum;
    int targetStopIndex;
    boolean userOnBus;


    GPSSimulater(){
        map = Map.getInstance();
        timer = null;
    }

    public void startSimulate(){
        userCoor = map.getStopCoordinate(userStartStop);
        driverCoor = map.getStopCoordinate(stopsSequence[0]);
        targetStopIndex = 1;
        targetStopNum = stopsSequence[targetStopIndex];
        lastTime = new Date().getTime();

        timer = new Timer();
        timer.schedule(new MyTimerTask(),GPSRefreshInterval,GPSRefreshInterval);
    }

    public Coordinate getUserCoordinate(){
        return userCoor;
    }
    public Coordinate getDriverCoordinate(){
        return driverCoor;
    }

    private void endSimulation(){
        timer.cancel();
        timer = null;
    }


    class MyTimerTask extends TimerTask{

        @Override
        public void run() {
            moveDriverTO(targetStopNum);
//            System.out.printf("%f %f",driverCoor.x,driverCoor.y);

            if(isCloseEnough(driverCoor,map.getStopCoordinate(targetStopNum))){
                targetStopIndex++;
                if(targetStopIndex<stopsSequence.length){
                    targetStopNum = stopsSequence[targetStopIndex];
                }
                else{
                    endSimulation();
                }
            }
            if(isCloseEnough(driverCoor,map.getStopCoordinate(userStartStop))){
                userOnBus = true;
            }

            if(isCloseEnough(driverCoor,map.getStopCoordinate(userEndStop))){
                userOnBus = false;
            }

            if(userOnBus){
                userCoor = driverCoor;
            }
        }
    }

    private void moveDriverTO(int stopNum){
        Coordinate stopCoor = map.getStopCoordinate(stopNum);
        double timeInterval = (new Date().getTime()*1.0 - lastTime)/1000;
        if(driverCoor.countDistance(stopCoor) < timeInterval*driverMovingSpeed){
            driverCoor = stopCoor;
        }
        else{
            Coordinate shift = stopCoor.minus(driverCoor);
            double length = shift.countLength();
            double xShift = driverMovingSpeed *( shift.x / length);
            double yShift = driverMovingSpeed * (shift.y / length);
            driverCoor.x = (float)(driverCoor.x + xShift);
            driverCoor.y = (float)(driverCoor.y + yShift);
        }
    }



    public boolean isCloseEnough(Coordinate c1, Coordinate c2){
        return (c1.countDistance(c2) < smallDistance);
    }
}
