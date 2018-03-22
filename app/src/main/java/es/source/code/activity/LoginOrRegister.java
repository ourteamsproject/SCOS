package es.source.code.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.*;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cristo.example.com.myapplication.R;
import es.source.code.model.User;

public class LoginOrRegister extends AppCompatActivity {
    private Button SignButton;
    private Button returnButton;
    private Button registerButton;
    private ProgressBar progressBar;
    private Context mycontext;
    private AutoCompleteTextView phone;
    private EditText password;
    private TextView percent;
    private String username;
    String url;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    Toast.makeText(LoginOrRegister.this,"登陆失败",Toast.LENGTH_LONG).show();
                    break;

                case  1:
                    Intent intent = new Intent(mycontext, MainScreen.class);
                    intent.putExtra("stringInScosEntry", "LoginSuccess");
                    startActivity(intent);
                    Toast.makeText(LoginOrRegister.this,"登陆成功",Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        SharedPreferences sPreferences = getSharedPreferences("config", MODE_PRIVATE);
        username = sPreferences.getString("username", "");

        SignButton = (Button) findViewById(R.id.signbutton);
        registerButton = (Button) findViewById(R.id.registerbutton);
        returnButton = (Button) findViewById(R.id.returnbutton);
        progressBar = (ProgressBar) findViewById(R.id.login_progress);
        phone = (AutoCompleteTextView) findViewById(R.id.phone1);


        if (username == null || username.length() <= 0) {
            Toast.makeText(LoginOrRegister.this, "请先注册", Toast.LENGTH_LONG).show();
            SignButton.setVisibility(View.GONE);
            registerButton.setVisibility(View.VISIBLE);
        } else {
            phone.setText(username);
            registerButton.setVisibility(View.GONE);
            SignButton.setVisibility(View.VISIBLE);

        }


        password = (EditText) findViewById(R.id.password);
        mycontext = this;


        SignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setProgressBar(progressBar);
                final String ipn = phone.getText().toString();
                final String ps = password.getText().toString();
                if (isPhoneNumber(ipn)) {                                                                     //判断用户名格式
                    if (isPassword(ps)) {//判断密码格式
                        url="http://192.168.43.130:8080/SCOSServer/LoginValidator";
                        new signThread(ipn,ps,url).start();

                        User signUser = new User();
                        signUser.setUserNmae(ipn);
                        signUser.setPassword(ps);
                        signUser.setOldUser(true);
                        Bundle mBundle = new Bundle();
                        mBundle.putSerializable("signUser", signUser);
                        saveUserInfo(mycontext, signUser.getUserNmae());
//
//                        Intent intent = new Intent(mycontext, MainScreen.class);
//                        intent.putExtra("stringInScosEntry", "LoginSuccess");
//                        startActivity(intent);
//                        }else{
//                            Toast.makeText(LoginOrRegister.this,"用户名或密码错误",Toast.LENGTH_LONG).show();
//                        }

                    } else {
                        password.setError("密码格式错误");
                    }

                } else {
                    phone.setError("用户名格式错误");
                }
            }
        });
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SharedPreferences sPreferences = getSharedPreferences("config", MODE_APPEND);
                username = sPreferences.getString("username", "");

                if (username == null || username.length() <= 0) {
                    SharedPreferences.Editor editor = sPreferences.edit();
                    editor.putInt("loginState", 0);
                    editor.commit();
                } else {

                }

                Intent intent = new Intent(mycontext, MainScreen.class);
                intent.putExtra("String", "Return");
                startActivity(intent);
            }

        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ipn = phone.getText().toString();
                String ps = password.getText().toString();
                if (isPhoneNumber(ipn)) {
                    if (isPassword(ps)) {
                        User loginUser = new User();
                        loginUser.setUserNmae(ipn);
                        loginUser.setPassword(ps);
                        saveUserInfo(mycontext, loginUser.getUserNmae());
                        Bundle mBundle = new Bundle();
                        mBundle.putSerializable("SER_KEY", loginUser);
                        Intent intent = new Intent(mycontext, MainScreen.class);
                        intent.putExtras(mBundle);
                        intent.putExtra("stringInScosEntry", "RegisterSuccess");
                        startActivity(intent);


                    } else {
                        password.setError("密码格式错误");
                    }

                } else {
                    phone.setError("用户名格式错误");
                }
            }
        });
    }

    /*
    @设置progressbar
     */
    public void setProgressBar(final ProgressBar progressbar) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressbar.setVisibility(View.GONE);

            }
        }, 2000);
    }

    /*
    @判断密码格式
     */
    public boolean isPassword(String account) {
        String all = "^[A-Za-z0-9\\-]+$";
        Pattern pattern = Pattern.compile(all);
        return pattern.matches(all, account);

    }

    /*
     @判断用户名格式
      */
    public static boolean isPhoneNumber(String pnumber) {
        String all = "[1][358]\\d{9}";
        Pattern p = Pattern.compile(all);
        Matcher m = p.matcher(pnumber);
        return m.matches();
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }


    public static void saveUserInfo(Context context, String username) {

        SharedPreferences sPreferences = context.getSharedPreferences("config", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sPreferences.edit();
        editor.putString("username", username);
        editor.putInt("loginState", 1);
        editor.commit();
    }

    class signThread extends Thread{
        String name;
        String password;
        String url;
        List list=null;
        public signThread(String name, String password, String url) {
            this.name = name;
            this.password = password;
            this.url = url;
        }

        @Override
        public void run() {
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
                Message message=Message.obtain(null,resultcode,0,0);
                handler.sendMessage(message);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}