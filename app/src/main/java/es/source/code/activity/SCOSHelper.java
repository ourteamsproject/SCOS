package es.source.code.activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.util.Properties;
import java.util.logging.LogRecord;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import cristo.example.com.myapplication.R;
import es.source.code.model.User;
import es.source.code.thread.MailSender;

public class SCOSHelper extends AppCompatActivity {
    private GridView gv;
    //    private Handler mainHandler;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 1) {
                Toast.makeText(SCOSHelper.this, "邮件发送成功", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoshelper);

        List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
        List<Object> image = new ArrayList<>();
        image.add(R.mipmap.editor);
        image.add(R.mipmap.order);
        image.add(R.mipmap.phone);
        image.add(R.mipmap.mse);
        image.add(R.mipmap.email);

        ArrayList<String> list = new ArrayList<>();
        list.add("用户使用协议");
        list.add("关于系统");
        list.add("电话人工帮助");
        list.add("短信帮助");
        list.add("邮件帮助");

        Iterator<String> iter = list.iterator();
        Iterator<Object> imageIter = image.iterator();
        while (iter.hasNext()) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("imageItem", imageIter.next());
            item.put("textItem", iter.next());
            items.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, items, R.layout.griditem, new String[]{"imageItem", "textItem"},
                new int[]{R.id.image_item, R.id.text_item});
        gv = (GridView) findViewById(R.id.gridview);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                          User user = (User) getIntent().getSerializableExtra("SER_KEY");
                                          Bundle mBundle = new Bundle();
                                          mBundle.putSerializable("SER_KEY", user);
                                          switch (i) {
                                              case 0:
                                                  break;
                                              case 1:
                                                  Toast.makeText(SCOSHelper.this, "点击了1", Toast.LENGTH_LONG).show();

                                                  break;
                                              case 2:
                                                  Intent dialIntent = new Intent();
                                                  dialIntent.setAction(Intent.ACTION_DIAL);
                                                  dialIntent.setData(Uri.parse("tel:5554"));
                                                  startActivity(dialIntent);
                                                  break;
                                              case 3:
                                                  String SENT_SMS_ACTION = "SENT_SMS_ACTION";
                                                  Intent sentIntent = new Intent(SENT_SMS_ACTION);
                                                  SmsManager sms = SmsManager.getDefault();
                                                  Intent mesIntent = new Intent();
                                                  mesIntent.setAction(Intent.ACTION_SENDTO);
                                                  mesIntent.setData(Uri.parse("smsto:5554"));
                                                  mesIntent.putExtra("sms_body", "test scos helper");
                                                  SCOSHelper.this.registerReceiver(new BroadcastReceiver() {
                                                      @Override
                                                      public void onReceive(Context _context, Intent _intent) {
                                                          switch (getResultCode()) {
                                                              case Activity.RESULT_OK:
                                                                  Toast.makeText(SCOSHelper.this,
                                                                          "短信发送成功", Toast.LENGTH_SHORT)
                                                                          .show();
                                                                  break;
                                                              case android.telephony.gsm.SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                                                                  break;
                                                              case android.telephony.gsm.SmsManager.RESULT_ERROR_RADIO_OFF:
                                                                  break;
                                                              case android.telephony.gsm.SmsManager.RESULT_ERROR_NULL_PDU:
                                                                  break;
                                                          }
                                                      }
                                                  }, new IntentFilter(SENT_SMS_ACTION));
                                                  startActivity(mesIntent);
                                                  break;
                                              case 4:
                                                  MailSender mailSender = new MailSender();
                                                  mailSender.start();
                                                  break;

                                          }
                                      }
                                  }
        );
    }


    class MailSender extends Thread {
        @Override
        public void run() {
            Intent data = new Intent(Intent.ACTION_SENDTO);
            data.setData(Uri.parse("mailto:1171788630@qq.com"));
            data.putExtra(Intent.EXTRA_SUBJECT, "这是标题");
            data.putExtra(Intent.EXTRA_TEXT, "这是内容");
            startActivity(data);



        }
    }
}



