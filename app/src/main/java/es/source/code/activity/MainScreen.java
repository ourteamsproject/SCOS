package es.source.code.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cristo.example.com.myapplication.R;
import es.source.code.model.User;
import es.source.code.service.UpdateService;

public class MainScreen extends AppCompatActivity {
    private RadioButton rbt1;
    private RadioButton rbt2;
    private RadioButton rbt3;
    private RadioButton rbt4;
    private Context mycontext;
    private RadioButton order;
    private RadioButton check;
    private RadioButton help;
    private RadioButton lor;

    private GridView gv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreen);


        MainScreen.this.startService(new Intent(MainScreen.this,UpdateService.class));


        List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
        List<Object> image=new ArrayList<>();
        image.add(R.mipmap.editor);
        image.add(R.mipmap.order);
        image.add(R.mipmap.me);
        image.add(R.mipmap.help);
        ArrayList<String> list=new ArrayList<>();
        list.add("点菜");
        list.add("查看订单");
        list.add("系统帮助");
        list.add("登陆/注册");
        Iterator<String> iter=list.iterator();
        Iterator<Object> imageIter=image.iterator();
        while (iter.hasNext()) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("imageItem",imageIter.next());
            item.put("textItem",iter.next());
            items.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, items, R.layout.griditem, new String[]{"imageItem", "textItem"},
                new int[]{R.id.image_item, R.id.text_item});
        gv = (GridView)findViewById(R.id.gridview);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                          User user=(User)getIntent().getSerializableExtra("signUser");
                                          Bundle mBundle=new Bundle();
                                          mBundle.putSerializable("signUser",user);
                                          switch (i) {
                                              case 0:
                                                  Intent foodViewIntent = new Intent(MainScreen.this,FoodView.class);
                                                  foodViewIntent.putExtras(mBundle);
                                                  startActivity(foodViewIntent);
                                                  break;
                                              case 1:
                                                  Intent foodOrderViewIntent = new Intent(MainScreen.this,FoodOderView.class);
                                                  foodOrderViewIntent.putExtras(mBundle);
                                                  startActivity(foodOrderViewIntent);

                                                  break;
                                              case 2:
                                                  Intent helpIntent = new Intent(MainScreen.this, SCOSHelper.class);
                                                  startActivity(helpIntent);
                                                  break;
                                              case 3:
//                                                  Toast.makeText(MainScreen.this, "点击了3", Toast.LENGTH_LONG).show();
                                                  Intent logIntent = new Intent(MainScreen.this, LoginOrRegister.class);
                                                  startActivity(logIntent);
                                                  break;

                                          }
                                      }
        }
    );
//        compareString();
        SharedPreferences sPreferences=getSharedPreferences("config", MODE_PRIVATE);
        int state =sPreferences.getInt("loginState",0);
        if(state==0){
            Toast.makeText(MainScreen.this, "您还未登陆", Toast.LENGTH_LONG).show();
        }else if(state==1){
            Toast.makeText(MainScreen.this, "您已登陆", Toast.LENGTH_LONG).show();
        }


    }

    public boolean compareString(){
        Intent intent=getIntent();
        ArrayList<String> list=new ArrayList<>();
        list.add(intent.getStringExtra("stringInScosEntry"));
        Iterator<String> iter=list.iterator();
        while (iter.hasNext()){
            String string=iter.next();
            switch (string){
                case "FromEntry":

                    break;
                case "LoginSuccess":
                    Toast.makeText(MainScreen.this, "LoginSuccess", Toast.LENGTH_LONG).show();
                    break;
                case "RegisterSuccess":
                    User user=(User)getIntent().getSerializableExtra("SER_KEY");
                    Toast.makeText(MainScreen.this,"欢迎"+user.getUserNmae()+"成为SCOS新用户",Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    public  boolean isLogin(){
        SharedPreferences sPreferences=getSharedPreferences("config", MODE_PRIVATE);
        int state =sPreferences.getInt("loginState",0);
        Toast.makeText(MainScreen.this, state, Toast.LENGTH_LONG).show();
        return true;
    }
}
