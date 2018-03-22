package es.source.code.service;


import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cristo.example.com.myapplication.R;
import es.source.code.activity.FoodDetail;
import es.source.code.model.Food;

/**
 * Created by Cristo on 2017/10/29.
 */

public class UpdateService extends IntentService {
    public UpdateService() {
        super("test");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        showNotification();
//        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification);
//        NotificationManager barmanager=(NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
//        Notification notice;
//        Notification.Builder builder = new Notification.Builder(this)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setWhen(System.currentTimeMillis())
//                .setContent(remoteViews);
//
//        Intent appIntent=null;
//        appIntent = new Intent(this,FoodDetail.class);
//        appIntent.setAction(Intent.ACTION_MAIN);
//        appIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//        appIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);//关键的一步，设置启动模式
//        PendingIntent contentIntent =PendingIntent.getActivity(this, 0,appIntent,PendingIntent.FLAG_UPDATE_CURRENT);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            if(isUpdate()) {
//                notice = builder.setContentIntent(contentIntent).setContentTitle("新品上线").setContentText("菜名，价格，类型").build();
//                notice.flags = Notification.FLAG_AUTO_CANCEL;
//                notice.sound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);//系统默认通知声音
//                barmanager.notify(10, notice);
//
//            }
//        }


    }
    public void showNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        RemoteViews rv = new RemoteViews(getPackageName(),R.layout.notification);
        rv.setTextViewText(R.id.tv,"菜名   数量");//修改自定义View中的歌名
        //修改自定义View中的图片(两种方法)
//        rv.setImageViewResource(R.id.iv,R.mipmap.ic_launcher);
        rv.setImageViewBitmap(R.id.iv, BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        Intent appIntent=null;
        appIntent = new Intent(this,FoodDetail.class);
        appIntent.setAction(Intent.ACTION_MAIN);
        appIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        appIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);//关键的一步，设置启动模式
        PendingIntent contentIntent =PendingIntent.getActivity(this, 0,appIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContent(rv).setContentTitle("新品上线");
        Notification notification = builder.build();
        notification.sound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(10,notification);
    }
    public boolean isUpdate(){
        boolean ref=false;
        String url;
        url="http://192.168.43.130:8080/SCOSServer/FoodUpdateService";
        try {
     URL httpurl=new URL(url);
        HttpURLConnection conn=(HttpURLConnection)httpurl.openConnection();
        InputStream is=conn.getInputStream();
        conn.setRequestMethod("GET");
        conn.setReadTimeout(5000);
        BufferedReader reader =new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String str;
        StringBuffer sb=new StringBuffer();
        while ((str=reader.readLine())!=null){
            sb.append(str);
            ref=true;
        }
            JSONObject jsonObject=new JSONObject(sb.toString());
            final int foodID=jsonObject.getInt("ID");
            final String foodName=jsonObject.getString("name");
            final int foodNumber=jsonObject.getInt("number");
            final int foodPrice=jsonObject.getInt("price");

        } catch (Exception e) {
            e.printStackTrace();
        }

       return ref;

    }


}
