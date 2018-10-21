package routeplaningservice;

public class Service {
	
	
	public static void main(String[] args) {
		
		DriverService ds = new DriverService(9001,3000,new DriverReaction(new IAssignAlgorithm()));
		
		GPSSimulater gs = new GPSSimulater();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				SentGpsData sgd = new SentGpsData(9003,3001,gs);
				sgd.start();
			}
			
		}).start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				
				PassagerService ps = new PassagerService(9000,3000,new PassagerReaction(new IAssignAlgorithm()));
				ps.start();
				
			}
			
		}).start();
		
		ds.start();
	}

}
