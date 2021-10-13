package com.example.androidservices;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    boolean done = false;
    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(!done) {
            done = true;
            final int currentId = 0;
            Runnable r = new Runnable() {
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        long endTime = System.currentTimeMillis() + 10 * 1000;
                        while (System.currentTimeMillis() < endTime) {
                            synchronized (this) {
                                try {
                                    wait(endTime - System.currentTimeMillis());
                                } catch (Exception e) {
                                }
                            }
                        }
                        Log.i("abccc", "Service running " + i + ": currentID = " + currentId);
                    }
                    stopSelf();
                }
            };
            Thread t = new Thread(r);
            t.start();
        }
        return Service.START_NOT_STICKY;
    }

    //demo started service sẽ chỉ vào st    artCommand, không vào onBind
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}