package routeplaningservice;

public class GPSReaction {
	
	double[] myGPSData;
	int point = 0;
	public GPSReaction(double[] gpsData) {
		this.myGPSData = gpsData;
		
	}
	public void setGPSPoint(double[] gpsData) {
		this.myGPSData = gpsData;
		
	}
	
	public double[] getGPS() {
		double[] temp = new double[2];
		temp[0]=myGPSData[point];
		temp[1] = myGPSData[point+1];
		return temp;
		
	}
	
	public void setPoint() {
		point +=2;
	}
	
	public GPSBean returnGPSBean() {
		GPSBean myBean = new GPSBean(myGPSData[point],myGPSData[point+1]);
		return myBean;
	}
	

}
