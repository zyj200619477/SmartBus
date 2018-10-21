package huang.bling.smartbus.ui;

import android.content.Context;
import android.widget.Toast;

public class uiUtil {
    public static void makeShortToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
