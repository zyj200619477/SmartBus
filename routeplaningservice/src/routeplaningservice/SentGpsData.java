package routeplaningservice;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import com.google.gson.Gson;

public class SentGpsData {
	
	
	int servicePort = 9000;
	int clientPort = 3000;
	
	Gson gson =new Gson();
	GPSSimulater gs;
	


	public SentGpsData() {}
	public SentGpsData(int port,int cPort,GPSSimulater gs) {
		this.servicePort = port;
		this.gs = gs;
		this.clientPort = cPort;
		
	}
	
	public void start() {
		
		byte[] buf = new byte[2048];
		DatagramSocket ds = null;
		try {
			ds = new DatagramSocket(servicePort);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DatagramPacket dp_receive = new DatagramPacket(buf,2048);
		boolean flag = true;
		boolean gpsFlag = true;
		while(flag) {
			try {
				ds.receive(dp_receive);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(new String(dp_receive.getData(),0,dp_receive.getLength()));
			if(gpsFlag) {
				gs.startSimulate();
				gpsFlag = false;
			}
			
			String sended_data =null;
			if(new String(dp_receive.getData(),0,dp_receive.getLength()).equals("2")){
				sended_data = gson.toJson(gs.getUserCoordinate());
			}else {
				sended_data = gson.toJson(gs.getDriverCoordinate());
			}
			 
			
			DatagramPacket dp_send = new DatagramPacket(sended_data.getBytes(),sended_data.length(),dp_receive.getAddress(),clientPort);
			try {
				ds.send(dp_send);
			} catch (IOException e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
			dp_receive.setLength(2048);
		}
	
	
	

}
	}
