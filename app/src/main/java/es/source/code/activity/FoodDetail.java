package es.source.code.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cristo.example.com.myapplication.R;

public class FoodDetail extends AppCompatActivity {
        private ListView listView;
        private SimpleAdapter simple_adapter;
        private List<Map<String, Object>> list;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.food_detail);
            listView = (ListView) findViewById(R.id.FoodDetaillistView);
		simple_adapter = new SimpleAdapter(FoodDetail.this,
                getData(),
				 R.layout.food_detail_item,
				new String[] { "image", "foodname" ,"foodprice","beizhu","sub","add"},
                new int[] {R.id.fooddDetailItemImageView, R.id.foodname, R.id.foodprice,R.id.beizhu,R.id.foodDetailSub,R.id.foodDetailAdd }
        );
		listView.setAdapter(simple_adapter);

        }
        // 加载SimpleAdapter数据集
        private List<Map<String, Object>> getData() {
            list = new ArrayList<Map<String, Object>>();
            for (int i=0;i<20;i++){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("image", R.mipmap.person);
                map.put("foodname", "松鼠鳜鱼");
                map.put("foodprice", "199");
                map.put("beizhu", "备注");
                map.put("sub",R.mipmap.sub);
                map.put("add", R.drawable.add);
                list.add(map);
            }
            return list;
        }
    }
