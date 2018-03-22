package es.source.code.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cristo.example.com.myapplication.R;
import es.source.code.activity.LoginOrRegister;
import es.source.code.activity.MainScreen;
import es.source.code.model.Food;

/**
 * Created by Cristo on 2017/10/14.
 */

public class FoodViewAdapter extends BaseAdapter {

         private List<Map<String, Object>> data;
         private Context mContext;
         private LayoutInflater layoutInflater;
         ViewHolder vh;
         private List<Food> foodlist;

   public FoodViewAdapter(Context context,List<Map<String, Object>> list){
       mContext=context;

   }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return foodlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                vh=new ViewHolder();
                convertView= LayoutInflater.from(mContext).inflate(R.layout.list_view_item,null);
                vh.imageview1=(ImageView)convertView.findViewById(R.id.imageView1);
                vh.name=(TextView)convertView.findViewById(R.id.name);
                vh.name.setText(data.get(position).get("name").toString());
                vh.price=(TextView)convertView.findViewById(R.id.price);
                vh.price.setText(data.get(position).get("price").toString());
                vh.imageview2=(ImageView)convertView.findViewById(R.id.imageView2);
                convertView.setTag(vh);

            }else{
                vh=(ViewHolder)convertView.getTag();
            }
            vh.imageview2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext,"点菜成功",Toast.LENGTH_LONG).show();
                }
            });
            vh.imageview2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext,"点菜成功",Toast.LENGTH_LONG).show();
                }
            });

            return convertView;
        }
        class ViewHolder {
            ImageView imageview1;
            TextView name;
            TextView price;
            ImageView imageview2;
        }
    }