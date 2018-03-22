package es.source.code.activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.telephony.gsm.SmsManager;


import android.view.View;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Cristo on 2017/10/23.
 */

public class Message extends AppCompatActivity {

    PendingIntent sentPI ;
    PendingIntent deliverPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);



        //处理返回的发送状态
        String SENT_SMS_ACTION = "SENT_SMS_ACTION";
        Intent sentIntent = new Intent(SENT_SMS_ACTION);
        sentPI= PendingIntent.getBroadcast(this, 0, sentIntent,
                0);
        // register the Broadcast Receivers
        this.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context _context, Intent _intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(Message.this,
                                "短信发送成功", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        break;
                }
            }
        }, new IntentFilter(SENT_SMS_ACTION));


        //处理返回的接收状态
        String DELIVERED_SMS_ACTION = "DELIVERED_SMS_ACTION";
        // create the deilverIntent parameter
        Intent deliverIntent = new Intent(DELIVERED_SMS_ACTION);
        deliverPI = PendingIntent.getBroadcast(this, 0,
                deliverIntent, 0);
        this.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context _context, Intent _intent) {
                Toast.makeText(Message.this,
                        "收信人已经成功接收", Toast.LENGTH_SHORT)
                        .show();
            }
        }, new IntentFilter(DELIVERED_SMS_ACTION));

    }



    /**
     * 直接调用短信接口发短信
     *
     * @param phoneNumber
     * @param message
     */
    public void sendSMS(String phoneNumber, String message) {
        // 获取短信管理器
        android.telephony.SmsManager smsManager = android.telephony.SmsManager
                .getDefault();
        // 拆分短信内容（手机短信长度限制）
        List<String> divideContents = smsManager.divideMessage(message);
        for (String text : divideContents) {
            smsManager.sendTextMessage(phoneNumber, null, text, sentPI,
                    deliverPI);
        }
    }

    /**
     * 调起系统发短信功能
     * @param phoneNumber
     * @param message
     */
    public void doSendSMSTo(String phoneNumber,String message){
        if(PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)){
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phoneNumber));
            intent.putExtra("sms_body", message);
            startActivity(intent);
        }
    }
    /**
     * 测试发送短信
     * @param view
     */
    public void SendSms(View view){
        sendSMS("10086", "ceshi");
    }

}
