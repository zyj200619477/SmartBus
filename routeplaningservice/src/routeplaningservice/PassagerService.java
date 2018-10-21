package routeplaningservice;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//这个功能是创建服务器接收包, 根据接收的包做出相应的动作,相应的动作用接口来表示
//接收包后,必须返回结果包, 结果包是一个been实体的转换

public class PassagerService {
	int servicePort = 9000;
	int clientPort = 3000;
	
	Gson gson =new Gson();
	PassagerReaction ap;

	public PassagerService() {}
	public PassagerService(int port,int cPort,PassagerReaction ap) {
		this.servicePort = port;
		this.ap = ap;
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
		while(flag) {
			try {
				ds.receive(dp_receive);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(new String(dp_receive.getData()));
			PassagerBean myBean =  gson.fromJson(new String(dp_receive.getData(),0,dp_receive.getLength()),PassagerBean.class);
			ap.receive(myBean);

			if(ap.result() !=null) {
				String sended_data = gson.toJson(ap.result());
				DatagramPacket dp_send = new DatagramPacket(sended_data.getBytes(),sended_data.length(),dp_receive.getAddress(),clientPort);
				try {
					ds.send(dp_send);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
			dp_receive.setLength(2048);
		}
		
	}
	
	public static void main(String[] args) {
		
		PassagerService ps = new PassagerService(9000,3000,new PassagerReaction(new IAssignAlgorithm()));
		ps.start();

		

		
	}

}
