package util;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import huang.bling.smartbus.bean.PassagerBean;

public class PassengerNetImpl implements PassengerNet<PassagerBean> {
    public final String TAG="PassengerNetImpl";
    public final String SERVER_IP="x.x.x.x";
    public final int PORT=8080;
    DatagramSocket mySocket;
    DatagramPacket myPacket;
    InetAddress myAdd;
    @Override
    public void connect(int id) {

    }

    @Override
    public void disconnect(int id) {

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
    public PassagerBean getPassengerResponse(int location) {
        PassagerBean myBean=new PassagerBean();
        myBean.setType("U");
        myBean.setCBusStopLocation(location);
        String strToSend=JSONHandler.PassagerBeanToString(myBean);
        String result=SendAndReceive(strToSend);
        if(result.equals("Error")) {
            Log.e(TAG,"getDriverInfo failure, update fail");
            return null;
        }
        PassagerBean resultBean;
        resultBean=JSONHandler.StringToPassagerBean(result);
        return resultBean;
    }

    @Override
    public PassagerBean registerForBus(int location) {
        PassagerBean myBean=new PassagerBean();
        myBean.setType("R");
        myBean.setCBusStopLocation(location);
        String strToSend=JSONHandler.PassagerBeanToString(myBean);
        String result=SendAndReceive(strToSend);
        if(result.equals("Error")) {
            Log.e(TAG,"wait for bus failure, update fail");
            return null;
        }
        PassagerBean resultBean;
        resultBean=JSONHandler.StringToPassagerBean(result);
        return resultBean;
    }

}
