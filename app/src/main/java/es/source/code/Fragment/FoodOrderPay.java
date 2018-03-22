package es.source.code.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cristo.example.com.myapplication.R;
import es.source.code.activity.LoginOrRegister;
import es.source.code.adapter.FoodOrderPayAdapter;
import es.source.code.model.User;
import es.source.code.thread.SettleAccounts;

/**
 * Created by Cristo on 2017/10/16.
 */

public class FoodOrderPay extends Fragment {
    private ProgressDialog progressDialog;
    private ListView listView;
    private Button pay;
    private ProgressBar bar;
    User user;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.food_order_pay, container, false);
        listView = (ListView)view.findViewById(R.id.food_order_pay_listview);
        List<Map<String, Object>> list = getData();
        listView.setAdapter(new FoodOrderPayAdapter(getActivity(), list));
         pay=(Button)view.findViewById(R.id.food_order);

        bar=view.findViewById(R.id.order_progressbar);


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bar.setVisibility(View.VISIBLE);
                new SettleAccounts().execute();
                pay.setEnabled(false);


            }
        });

        return view;
    }

    public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 15; i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("imageView1", R.mipmap.food3);
            map.put("foodName","凉拌" + i);
            map.put("foodPrice","10*i");
            map.put("beizhu","10*i");
            list.add(map);
        }
        return list;
    }

    class SettleAccounts extends AsyncTask<String, Integer, String> {
        public SettleAccounts() {
            super();
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(getActivity(),"正在结账，请稍后",Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(String result) {
             pay.setEnabled(false);
            Toast.makeText(getActivity(),"结账成功!本次消费金额为168，" +
                    "你的总积分为1688",Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onProgressUpdate(Integer... progresses) {
            super.onProgressUpdate();
            bar.setProgress(progresses[0]);

        }




        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                int count=0;
                int total=100;
                int length = -1;
                while (count<100){
                    count=count+20;
                    publishProgress((int)((count/(float)total)*100));
                    Thread.sleep(1666);
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            };
            return null;
        }
    }


}
