package es.source.code.thread;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;

import java.util.logging.Formatter;

import java.util.logging.LogRecord;

/**
 * Created by Cristo on 2017/10/22.
 */

public class MailSender extends  Thread {
    boolean run=true;

    private Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    @Override
    public void run() {

    while (run){

        Message m=handler.obtainMessage();


    }



    }



}
