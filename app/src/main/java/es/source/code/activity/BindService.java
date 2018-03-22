package es.source.code.activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.*;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import cristo.example.com.myapplication.R;
import es.source.code.service.ServerObserverService;
import es.source.code.service.UpdateService;


public class BindService extends Activity{

//    //Notification.FLAG_FOREGROUND_SERVICE    //表示正在运行的服务
//    public static final String NOTIFICATION_TAG = "littlejie";
//    public static final int DEFAULT_NOTIFICATION_ID = 1;
//
//    private NotificationManager mNotificationManager;
//    private  Button b;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.test);
//
//        mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
//        b=(Button)findViewById(R.id.button);
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sendNotification();
//            }
//        });
//
//
//    }
//
//
//
//    /**
//     * 发送最简单的通知,该通知的ID = 1
//     */
//    private void sendNotification() {
//        //这里使用 NotificationCompat 而不是 Notification ,因为 Notification 需要 API 16 才能使用
//        //NotificationCompat 存在于 V4 Support Library
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("Send Notification")
//                .setContentText("Hi,My id is 1");
//        mNotificationManager.notify(DEFAULT_NOTIFICATION_ID, builder.build());
//    }
//
//    /**
//     * 使用notify(String tag, int id, Notification notification)方法发送通知
//     * 移除对应通知需使用 cancel(String tag, int id)
//     */
//    private void sendNotificationWithTag() {
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("Send Notification With Tag")
//                .setContentText("Hi,My id is 1,tag is " + NOTIFICATION_TAG);
//        mNotificationManager.notify(NOTIFICATION_TAG, DEFAULT_NOTIFICATION_ID, builder.build());
//    }

}
