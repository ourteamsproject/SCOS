package es.source.code.thread;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


/**
 * Created by Cristo on 2017/10/22.
 */

public class SettleAccounts extends AsyncTask {
    private Context context;
    private ProgressDialog progressDialog;
    private ProgressBar pb;


    @Override
//写progressbar?
    protected void onPreExecute() {

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                bar.setVisibility(View.GONE);
//            }
//        },6000);

    }

    @Override
    protected void onPostExecute(Object o) {

    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Object o) {
        super.onCancelled(o);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        System.out.print("正在结账中");
        return null;
    }
}
