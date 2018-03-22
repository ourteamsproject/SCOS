package es.source.code.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cristo.example.com.myapplication.R;
import es.source.code.Fragment.FoodOrderNotPay;
import es.source.code.Fragment.FoodViewFragment;
import es.source.code.adapter.FoodOrderViewAdapter;
import es.source.code.model.User;

public class FoodOderView extends AppCompatActivity {
    User user;
    private Button pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_oder_view);
        initViews();

        user=(User)getIntent().getSerializableExtra("SER_KEY");
        Bundle bundle =new Bundle();
        bundle.putSerializable("SER_KEY",user);
    }

    private void initViews(){
        TabLayout tabLayout = (TabLayout) findViewById(R.id.food_oder_tabLayout_main);
        tabLayout.addTab(tabLayout.newTab().setText("未下菜单"));
        tabLayout.addTab(tabLayout.newTab().setText("已下菜单"));
        final ViewPager viewPager = (ViewPager) findViewById(R.id.food_oder_viewPager_main);
        final FoodOrderViewAdapter adapter = new FoodOrderViewAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        int i=getIntent().getIntExtra("flag",-1);
        viewPager.setCurrentItem(i);

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
}
