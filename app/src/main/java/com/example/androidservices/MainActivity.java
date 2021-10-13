package com.example.androidservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Current Date
    MyLocalService myService;
    //Message
    Messenger messenger = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Current Date
//        Intent intent = new Intent(this, MyLocalService.class);

        //Message
        Intent intent = new Intent(this, MyRemoteService.class);

        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    //thuc hien ban tin nhan
    public void onClickBtn(View view) {
        //Like Thread
        //thực hiện start một service
//        Intent intent = new Intent(this, MyIntentService.class);
//        Intent intent = new Intent(this, MyService.class);
//        startService(intent);

        //Current Date
//        String s = myService.getCurrentTime();
//        ((TextView)findViewById(R.id.textView)).setText(s);

        //thi ben remote se nhan dc
        Message msg = Message.obtain();
        Bundle b = new Bundle();
        b.putString("myData", "Do Action A");
        msg.setData(b);
        try {
            messenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //bind xong sẽ nhảy vào hàm này
            //Current Date
            //lấy service
//            MyLocalService.MyLocalBinder binder = (MyLocalService.MyLocalBinder) service;
//            myService = binder.getCurrentService();

            //Message
            //lay ra messenger tu ben kia ban ve
            messenger = new Messenger(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };//kết thúc việc tạo conection
}