package huang.bling.smartbus.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import huang.bling.smartbus.bean.DriverBean;
import huang.bling.smartbus.bean.PassagerBean;


public class JSONHandler {
    public static Gson myGson=new GsonBuilder().create();
    public static String DriverBeanToString(DriverBean bean){

        return myGson.toJson(bean);
    }

    public static String PassagerBeanToString(PassagerBean bean){

        return myGson.toJson(bean);
    }
    public static DriverBean StringToDriverBean(String str){

        return myGson.fromJson(str,DriverBean.class);
    }
    public static PassagerBean StringToPassagerBean(String str){

        return myGson.fromJson(str,PassagerBean.class);
    }
}
