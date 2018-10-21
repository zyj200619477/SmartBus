package huang.bling.smartbus.ui.Driver;

import huang.bling.smartbus.ui.Map.Coordinate;
import huang.bling.smartbus.ui.Map.Map;

public class DriverData {
    public Map map;
    public Coordinate curCoor;
    public int nextStopNum;
    public int nnStopNum;
    DriverData(Map map, Coordinate coor, int nextStopNum, int nnStopNum){
        this.map = map;
        this.curCoor = coor;
        this.nextStopNum = nextStopNum;
        this.nnStopNum = nnStopNum;
    }
}
