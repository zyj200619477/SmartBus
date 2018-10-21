package huang.bling.smartbus.ui.Passenger;

import huang.bling.smartbus.ui.Map.Coordinate;

public class PassengerData {


    public Coordinate curCoor;
    public int nextStopNum;
    public int nnStopNum;
    PassengerData( Coordinate coor, int nextStopNum, int nnStopNum){

        this.curCoor = coor;
        this.nextStopNum = nextStopNum;
        this.nnStopNum = nnStopNum;
    }
}
