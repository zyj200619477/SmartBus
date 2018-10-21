package routeplaningservice;

public interface AssignAlgorithm {
	
	
	public void setPassage(int startBus, int endBus);
	public int getDriveLocation();
	public int[] getNextRoute();
	public void calculateNextRoute(int currentLocation);

}
