package huang.bling.smartbus.util;

import huang.bling.smartbus.bean.PassagerBean;
import huang.bling.smartbus.global.NetworkParam;

public class PassengerNetImpl implements PassengerNet<PassagerBean> {
    public final String TAG="PassengerNetImpl";
    public final String SERVER_IP="192.168.0.105";

    public PassengerNetImpl(){
    }

    public void connect(int id) {

    }

    public void disconnect(int id) {

    }

    public PassagerBean getPassengerResponse(int location) {
        PassagerBean myBean=new PassagerBean();
        myBean.setType("U");
        myBean.setCBusStopLocation(location);
        String strToSend=JSONHandler.PassagerBeanToString(myBean);
        String result=NetUtil.sendAndReceive(strToSend,SERVER_IP, NetworkParam.PassengerPORT,NetworkParam.PassengerRECEIVE_PORT);
        if(result.equals("Error")) {
            System.out.println("getPassengerInfo failure, update fail");
            return null;
        }
        PassagerBean resultBean;
        resultBean=JSONHandler.StringToPassagerBean(result);
        return resultBean;
    }

    public PassagerBean registerForBus(int location) {
        PassagerBean myBean=new PassagerBean();
        myBean.setType("R");
        myBean.setCBusStopLocation(location);
        String strToSend=JSONHandler.PassagerBeanToString(myBean);
        String result=NetUtil.sendAndReceive(strToSend,SERVER_IP,NetworkParam.PassengerPORT,NetworkParam.PassengerRECEIVE_PORT);
        if(result.equals("Error")) {
            System.out.println("wait for bus failure, update fail");
            return null;
        }
        PassagerBean resultBean;
        resultBean=JSONHandler.StringToPassagerBean(result);
        return resultBean;
    }

}
