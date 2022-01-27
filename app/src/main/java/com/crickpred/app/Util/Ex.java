package com.crickpred.app.Util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.crickpred.app.R;


import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class Ex {
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    private static          TelephonyManager tMgr;
    private static Activity activity;
    //network check
    public static boolean isConnectionEnable(Context context){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public static boolean checkAndRequestPermissions(Context context,Activity activity) {
        int readIMEI= ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_PHONE_STATE);

        List<String> listPermissionsNeeded = new ArrayList<>();

        if (readIMEI != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_PHONE_STATE);        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(activity,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    public static boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public static void AlertBox(String message,Activity activity) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton(activity.getResources().getString(R.string.ok_message),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                      ;
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

   /* public static void getIPaddress (){
        String api="https://api.ipify.org?format=json";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(api, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String res = new String(responseBody);

                try {
                    JSONObject jsonObject = new JSONObject(res);
                   Constant.PublicIP =jsonObject.get("ip").toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }*/

    public static String GetDeviceID(Activity activity){
        TelephonyManager tm=(TelephonyManager)activity.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceID = null;
        int readIMEI= ContextCompat.checkSelfPermission(activity,
                Manifest.permission.READ_PHONE_STATE);
        if(deviceID == null) {
            if (readIMEI == PackageManager.PERMISSION_GRANTED) {
             // deviceID = tm.getDeviceId().toString();
            }
        }
        return deviceID;
    }
    public static boolean isConnectedToServer(String url, int timeout) {
        try{
            URL myUrl = new URL(url);
            URLConnection connection = myUrl.openConnection();
            connection.setConnectTimeout(timeout);
            connection.connect();
            return true;
        } catch (Exception e) {
            // Handle your exceptions
            return false;
        }
    }

    public static boolean isServerReachable(Context context) {
         final Socket socket;

        try {
            URL url = new URL("wwww.google.com");
            socket = new Socket(url.getHost(), url.getPort());
        } catch (IOException e) {
            return false;
        }

        try {
            socket.close();
        } catch (IOException e) {
            // will never happen, it's thread-safe
        }

        return true;
    }

}
