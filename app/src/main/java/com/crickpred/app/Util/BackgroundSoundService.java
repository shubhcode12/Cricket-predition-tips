package com.crickpred.app.Util;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;

public class BackgroundSoundService extends Service  {


    MediaPlayer mp;
    AssetFileDescriptor afd = null;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate()

    {

        try {
            afd = getAssets().openFd("saki.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp = new MediaPlayer();
        try {
            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.setLooping(true);
        try {
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void onDestroy()
    {
        mp.stop();
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mp.start();
        return Service.START_NOT_STICKY;
    }
}
