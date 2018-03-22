package es.source.code.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cristo.example.com.myapplication.R;
import es.source.code.activity.FoodOderView;
import es.source.code.activity.LoginOrRegister;
import es.source.code.adapter.FoodOrderListViewAdapter;
import es.source.code.adapter.ListViewAdapter;
import es.source.code.model.User;

/**
 * Created by Cristo on 2017/10/16.
 */

public class FoodOrderNotPay extends Fragment {

    private ListView listView;
    private Button pay;
    User user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_order_not_pay, container, false);
        listView = (ListView)view.findViewById(R.id.foodorderlistview);
        List<Map<String, Object>> list = getData();
        listView.setAdapter(new FoodOrderListViewAdapter(getActivity(), list));
        user=(User) getActivity().getIntent().getSerializableExtra("signUser");
        pay=(Button)view.findViewById(R.id.foodordersubmit);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(user!=null){
                    if (user.getOldUser()){
                        Toast.makeText(getActivity(),"你好，老用户，你本次可享7折优惠",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        return view;
    }

    public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 15; i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image1", R.mipmap.person);
            map.put("foodName","凉拌" + i);
            map.put("foodPrice","10*i");
            map.put("beizhu","10*i");
            map.put("button","退菜");

            list.add(map);
        }
        return list;
    }


}

