package huang.bling.smartbus.util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class NetUtil {
    public static String sendAndReceive(String input,String SERVER_IP,int PORT,int RECEIVE_PORT){
        DatagramPacket myPacket;
        DatagramSocket mySocket = null;

        byte[] sent_data=input.getBytes();
        try {
            System.out.println("input "+ input);
            mySocket=new DatagramSocket(RECEIVE_PORT);
            InetAddress myAdd= InetAddress.getByName(SERVER_IP);
            myPacket=new DatagramPacket(sent_data,sent_data.length,myAdd,PORT);
            System.out.println("new socket created!"+SERVER_IP+": "+PORT);
            mySocket.send(myPacket);
            System.out.println("packet send finish!");
        } catch (IOException e) {
            System.out.println("IOException running NetImpl");
            //e.printStackTrace();
            mySocket.close();
            return "Error";
        }

        byte[] resultByte=new byte[2048];
        DatagramPacket resultPacket=new DatagramPacket(resultByte,0,resultByte.length);
        try {
            System.out.println("start receiving...");
            System.out.println("IP: "+SERVER_IP+": "+RECEIVE_PORT);
            mySocket.receive(resultPacket);
            mySocket.setSoTimeout(5000);
            resultByte=resultPacket.getData();
            System.out.println("receive finish!");
        } catch (IOException e) {
            System.out.println("mySocket receive exception in connect()");
            e.printStackTrace();
            mySocket.close();
            return "Error";
        }
        String resultString=new String(resultByte,0,resultPacket.getLength());
        System.out.println("resultString="+resultString);
        mySocket.close();
        return resultString;
    }

}
