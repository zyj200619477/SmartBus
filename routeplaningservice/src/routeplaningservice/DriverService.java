package routeplaningservice;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import com.google.gson.Gson;

public class DriverService {
	int port = 9001;
	int clientPort = 3000;
	Gson gson = new Gson();
	DriverReaction ap;

	public DriverService() {}
	public DriverService(int port,int cPort,DriverReaction ap) {
		this.port = port;
		this.ap = ap;
		this.clientPort = cPort;
		
	}
	
	public void start() {
		
		byte[] buf = new byte[2048];
		DatagramSocket ds = null;
		try {
			ds = new DatagramSocket(port);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DatagramPacket dp_receive = new DatagramPacket(buf,2048);
		boolean flag = true;
		while(flag) {
			try {
				ds.receive(dp_receive);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(new String(dp_receive.getData(),0,dp_receive.getLength()));
			DriverBean myBean =  gson.fromJson(new String(dp_receive.getData(),0,dp_receive.getLength()),DriverBean.class);
			ap.receive(myBean);

			if(ap.result() !=null) {
				String sended_data = gson.toJson(ap.result());
				System.out.println("sended_data");
				System.out.println(sended_data);
				DatagramPacket dp_send = new DatagramPacket(sended_data.getBytes(),sended_data.length(),dp_receive.getAddress(),clientPort);
				try {
					ds.send(dp_send);
					System.out.println("Send success");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
			dp_receive.setLength(2048);
		}
		
}
	}