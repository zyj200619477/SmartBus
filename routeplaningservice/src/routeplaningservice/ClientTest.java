package routeplaningservice;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.google.gson.Gson;

public class ClientTest {
	
	public static void main(String args[]) throws UnknownHostException {
		Gson gson = new Gson();
		byte[] buf = new byte[2048];
		DatagramSocket ds = null;
		try {
			ds = new DatagramSocket(3000);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PassagerBean myBean = new PassagerBean();
		myBean.setType("R");
		String sended_data = gson.toJson(myBean);
		
		System.out.println(sended_data);
		DatagramPacket dp_send = new DatagramPacket(sended_data.getBytes(),sended_data.length(),InetAddress.getByName("127.0.0.1"),9000);
		try {
			ds.send(dp_send);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DatagramPacket dp_receive = new DatagramPacket(buf,2048);
		boolean flag = true;
		while(flag) {
			try {
				ds.receive(dp_receive);
				flag = false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(flag == false) {
				System.out.println(new String(dp_receive.getData(),0,dp_receive.getLength()));
				
			}
			
			
			
			}			
			dp_receive.setLength(2048);
		}
		
		

		
		
	}


