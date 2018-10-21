package routeplaningservice.Map;

public class Coordinate {
    public float x;
    public float y;
    public Coordinate(float x,float y){
        this.x = x;
        this.y = y;
    }
    public double countDistance(Coordinate c){
        double squar = Math.pow(x-c.x,2)+Math.pow(y-c.y,2);
        return Math.sqrt(squar);
    }
    public Coordinate minus(Coordinate cor){
        return new Coordinate(this.x-cor.x,this.y - cor.y);
    }
    public double countLength(){
        double squar = Math.pow(x,2) + Math.pow(y,2);
        return Math.sqrt(squar);
    }
}
