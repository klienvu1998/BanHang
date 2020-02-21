package com.example.hyvu.banhang.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

public class CheckConnection {
    public boolean haveConnection(Context context){
        boolean haveWIFI = false;
        boolean haveMOBILENETWORK = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] infos = connectivityManager.getAllNetworkInfo();
        for(NetworkInfo ni : infos){
            if(ni.getTypeName().equalsIgnoreCase("WIFI")){
                if(ni.isConnected()){
                    haveWIFI=true;
                }
            }
            if(ni.getTypeName().equalsIgnoreCase("MOBILE")){
                if(ni.isConnected()){
                    haveMOBILENETWORK=true;
                }
            }
        }
        return haveWIFI || haveMOBILENETWORK;
    }

    public void ToastConnection(Context context,String notify){
        Toast.makeText(context,notify,Toast.LENGTH_SHORT).show();
    }
}
