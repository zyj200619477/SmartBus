package util;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import huang.bling.smartbus.bean.Bean;
import huang.bling.smartbus.bean.DriverBean;

public class DriverNetImpl implements DriverNet<DriverBean> {
    public final String TAG="DriverNetImpl";
    public final String SERVER_IP="x.x.x.x";
    public final int PORT=8080;
    DatagramSocket mySocket;
    DatagramPacket myPacket;
    InetAddress myAdd;
    public DriverNetImpl(){
        try {
            mySocket=new DatagramSocket(PORT);
            myAdd=InetAddress.getByName(SERVER_IP);
        } catch (IOException e) {
            Log.e(TAG,"IOException running DriverNetImpl");
            e.printStackTrace();
        }
    }

    String SendAndReceive(String input){
        byte[] sent_data=input.getBytes();
        myPacket=new DatagramPacket(sent_data,sent_data.length,myAdd,PORT);
        try {
            mySocket.send(myPacket);
        } catch (IOException e) {
            Log.e(TAG,"Socket send packet failure");
            e.printStackTrace();
            return "Error";
        }
        byte[] resultByte=new byte[2048];
        DatagramPacket resultPacket=new DatagramPacket(resultByte,0,resultByte.length);
        try {
            mySocket.receive(resultPacket);
        } catch (IOException e) {
            Log.e(TAG,"mySocket receive exception in connect()");
            e.printStackTrace();
            return "Error";
        }
        String resultString=new String(resultByte,0,resultByte.length);
        return resultString;
    }

    @Override
    public void connect(int id) {
        Log.d(TAG,"connect() has been deprecated");
    }

    @Override
    public void disconnect(int id) {
        Log.d(TAG,"disconnect() has been deprecated");
    }

    @Override
    public DriverBean getDriverResponse(int location) {
        Bean myBean=new DriverBean();
        myBean.setType("U");
        myBean.setCBusStopLocation(location);
        String strToSend=myBean.toString();
        String result=SendAndReceive(strToSend);
        if(result.equals("Error")) {
            Log.e(TAG,"getDriverInfo failure, update fail");
            return null;
        }
        DriverBean resultBean;
        resultBean=new DriverBean();
        return resultBean;

    }
}
