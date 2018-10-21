package huang.bling.smartbus.util;


import java.net.InetAddress;

import huang.bling.smartbus.bean.DriverBean;
import huang.bling.smartbus.global.NetworkParam;


public class DriverNetImpl implements DriverNet<DriverBean> {
    public final String TAG="DriverNetImpl";
    InetAddress myAdd;


    public void connect(int id) {
        System.out.println("connect() has been deprecated");
    }


    public void disconnect(int id) {
        System.out.println("disconnect() has been deprecated");
    }

    public DriverBean getDriverResponse(int location) {
        DriverBean myBean=new DriverBean();
        myBean.setType("U");
        myBean.setCBusStopLocation(location);
        String strToSend=JSONHandler.DriverBeanToString(myBean);
        System.out.println("strToSend="+strToSend);
        String result=NetUtil.sendAndReceive(strToSend,NetworkParam.ip, NetworkParam.DriverPORT,NetworkParam.DriverRECEIVE_PORT);
        System.out.println(result);
        if(result.equals("Error")) {
            System.out.println("getDriverInfo failure, update fail");
            return null;
        }
        DriverBean resultBean;
        resultBean=JSONHandler.StringToDriverBean(result);
        return resultBean;

    }
}
