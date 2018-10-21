package routeplaningservice;

public abstract class Bean {
	private int cBusStopLocation = 0;
	private int nBusStopLocation = 0;
	private String Type = "Driver";
	
	public void setCBusStopLocation(int cBusStopLocation) {
		this.cBusStopLocation = cBusStopLocation;
		
	}
	public int getCBusStopLocation() {
		return cBusStopLocation;
	}
	
	public void setNBusStopLocation(int nBusStopLocation) {
		this.nBusStopLocation = nBusStopLocation;
	}
	public int getNBusStopLocation() {
		return nBusStopLocation;
	}
	
	public void setType(String Type) {
		this.Type = Type;
	}
	public String getType() {
		return Type;
	}
}
