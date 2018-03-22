package es.source.code.service;


import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.source.code.activity.SCOSHelper;
import es.source.code.model.Food;

/**
 * Created by Cristo on 2017/10/27.
 */

public class ServerObserverService extends Service {
    private Messenger toClient;
    serviveThread st=new serviveThread();
    private boolean isRun=true;
    private final Messenger msger =new Messenger(new cMessageHandler());
    cMessageHandler handler=new cMessageHandler();

    public ServerObserverService() {
        super();

    }
    public boolean isRun(){
        ActivityManager am = (ActivityManager)getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(500);
        boolean isAppRunning = false;
        if(list.get(0).topActivity.getPackageName().equals("es.source.code.activity")){
            isAppRunning=true;
        }else {
            for (ActivityManager.RunningTaskInfo info :list){
                if(info.topActivity.getPackageName().equals("es.source.code.activity")) {
                    isAppRunning = true;

                }
            }
        }
        Log.i("ActivityService isRun()", "1  ...isAppRunning......"+isAppRunning);
        return isAppRunning;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        isRun();
        Log.i("DemoLog", "ServiceHandler -> onCreate");

    }
    @Nullable
    @Override
    //任务体写在这
    public IBinder onBind(Intent intent) {
        Log.i("DemoLog", "ServiceHandler -> onBind");
        return msger.getBinder() ;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isRun=false;
        Log.i("DemoLog", "ServiceHandler -> onDestroy");
    }
    class cMessageHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    Toast.makeText(getApplicationContext(),"用户停止了更新更新",Toast.LENGTH_LONG).show();
                    Log.i("DemoLog", "用户停止了更新更新");
//                    ServerObserverService service=new ServerObserverService();
//
//                    service.isAppRun();
                    isRun=false;
                case 1:
                    isRun=true;
                    Log.i("DemoLog", "用户启动了更新更新");
                    st.start();



                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }



    class  serviveThread extends  Thread{
        @Override
        public void run() {

//          此线程模拟接收服务器传回的菜品信息
            while (isRun){
                Message message = Message.obtain(null, 10, 0, 0);
                try {
                    Thread.sleep(2000);
                    Log.i("DemoLog", "菜品更新");
//                    if(isRun()){
                        Food food=new Food();
                        food.setFoodName("小鱼");
                        food.setSaleNumber(20);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("food",food);
                        message.setData(bundle);
                       handler.sendMessage(message);
//                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


//            private boolean isServiceRunning(){
//                ActivityManager manager = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
//                for(ActivityManager.RunningServiceInfo service : manager
//                        .getRunningServices(Integer.MAX_VALUE)){
//                    if(("es.source.code.service").equals(getSystemServiceName().toString())){
//                        return true;
//                    }
//                }
//                return false;
//            }








        }
    }

}
