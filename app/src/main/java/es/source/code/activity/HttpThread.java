package es.source.code.activity;

import android.os.*;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.android.gms.plus.PlusOneDummyView.TAG;

/**
 * Created by Cristo on 2017/11/4.
 */

public class HttpThread extends Thread {
    String name;
    String password;
    String url;
    List list=null;

    public HttpThread(String name, String password, String url) {
        this.name = name;
        this.password = password;
        this.url = url;
    }
    private  boolean doGet(){
        boolean isUser=false;
        url=url+"?name="+name+"&password="+password;
        byte[] result=new byte[1024];
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
            }
            String res=sb.toString();
            JSONObject jsonObject=new JSONObject(res);
            final int resultcode=jsonObject.getInt("RESULTCODE");
            if(resultcode==1){
                isUser=true;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
       return isUser;
    }

    @Override
    public void run() {
        super.run();
        doGet();
    }
}
