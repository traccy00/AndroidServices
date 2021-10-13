package com.example.androidservices;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

//Current Date
public class MyLocalService extends Service {

    public IBinder binder = new MyLocalBinder();

    public MyLocalService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }

    public String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String time = sdf.format(new Date());
        return time;
    }

    public class MyLocalBinder extends Binder {
        //lấy ra service hiện tại
        public MyLocalService getCurrentService() {
            return MyLocalService.this;
        }
    }
}