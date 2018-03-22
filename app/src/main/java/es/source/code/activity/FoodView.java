package es.source.code.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.*;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cristo.example.com.myapplication.R;
import es.source.code.Fragment.FoodViewFragment;
import es.source.code.model.Food;
import es.source.code.model.User;
import es.source.code.service.ServerObserverService;

public class FoodView extends AppCompatActivity {
    private boolean isBound=false;
    private Messenger msger;
    User user;
    MenuItem menuItem=null;
    private Messenger mService;

    class sMessageHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 10:
                    Toast.makeText(FoodView.this,"更新菜名。。小鱼",Toast.LENGTH_LONG).show();
                    break;


                default:
                    super.handleMessage(msg);
            }
        }
    }


    private ServiceConnection mConn = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            mService = new Messenger(service);
            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            mService = null;
            isBound = false;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_view);
        initViews();



    }

    private void initViews(){
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout_main);
        tabLayout.addTab(tabLayout.newTab().setText("冷菜"));
        tabLayout.addTab(tabLayout.newTab().setText("热菜"));
        tabLayout.addTab(tabLayout.newTab().setText("海鲜"));
        tabLayout.addTab(tabLayout.newTab().setText("酒水"));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager_main);
        final MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
          menuItem = menu.findItem(R.id.refreshing);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.orderlist:
                Intent intent = new Intent(FoodView.this, FoodOderView.class);
                User user=(User)getIntent().getSerializableExtra("SER_KEY");
                Bundle mBundle=new Bundle();
                mBundle.putSerializable("SER_KEY",user);
                intent.putExtra("flag",0);
                startActivity(intent);
                return true;
            case R.id.chek:
                Intent cIntent = new Intent(FoodView.this,  FoodOderView.class);
                cIntent.putExtra("flag",1);
                startActivity(cIntent);
                return true;
            case R.id.call:
                //TODO
                return true;
            case R.id.refreshing:
//                启动 ServerObserverService 服务，并向 ServerObserverService 发送信息 Message 属性 what 值为 1
                try {
//               “启动实时更新”Action 被点击后，将 Action 标签修改为“停止实时更新时更新”
                if( menuItem.getTitle().equals("启动实时更新")&&isBound){
                    menuItem.setTitle("停止实时更新");

                        Message msgFromClient = Message.obtain(null, 1, 0, 0);
                        mService.send(msgFromClient);
                }else{
                    menuItem.setTitle("启动实时更新");//此时应为停止实时更新
//                     当在 FoodView  界面中点击“停止实时更新” Action  时，向 ServerObserverService 发送 Message 属性 what 值为 0
                    Message msgFromClientStop = Message.obtain(null, 0, 0, 0);

                    mService.send(msgFromClientStop);
                }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent=new Intent(this,ServerObserverService.class);
        bindService(intent,mConn, Context.BIND_AUTO_CREATE);


    }

    @Override
    protected void onStop() {
        super.onStop();
        if(isBound){
            unbindService(mConn);
            isBound=false;
        }
    }




}
