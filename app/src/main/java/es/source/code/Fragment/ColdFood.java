package es.source.code.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cristo.example.com.myapplication.R;
import es.source.code.activity.FoodDetail;
import es.source.code.activity.LoginOrRegister;
import es.source.code.activity.MainScreen;
import es.source.code.adapter.ListViewAdapter;
import es.source.code.domain.FoodViewData;


/**
 * Created by Cristo on 2017/10/16.
 */

public class ColdFood extends Fragment {
    private ListView listView;
    private Context context;



    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cold_food, container, false);
        listView = (ListView)view.findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getActivity(), "第" + i + "行", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), FoodDetail.class);

                startActivity(intent);

            }
        });
        FoodViewData data=new FoodViewData();
        List<Map<String, Object>> list =data.getData(R.mipmap.food3,"LIANG ","199",R.drawable.add,"0",R.mipmap.sub,"余量：10");
        listView.setAdapter(new ListViewAdapter(getActivity(), list));
        return view;
    }

}
