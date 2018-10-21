package routeplaningservice;

public class TimeCount extends Thread {
	GPSReaction gr;
	public TimeCount(GPSReaction gr) {
		this.gr = gr;
	}
	
	public void run() {
		while(true) {
			gr.setPoint();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
