package com.example.androidservices;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

import androidx.annotation.NonNull;

//Message
public class MyRemoteService extends Service {
    public MyRemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        //binder của messenger
        return messenger.getBinder();
    }

    //chuyên nhận tin nhắn từ bên kia đẩy sang, nó parse
    public class Incoming extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            Bundle b = msg.getData();
            //bắn tin nhắn thông qua messenger
            String s = b.getString("myData");
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        }
    }

    //thông qua messenger, incoming sẽ làm việc để đón messagse
    public Messenger messenger = new Messenger(new Incoming());
}