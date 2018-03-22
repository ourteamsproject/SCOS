package es.source.code.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import cristo.example.com.myapplication.R;

/**
 * Created by Cristo on 2017/10/20.
 */

public class FoodOrderPayAdapter extends BaseAdapter {

    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;
    int ordernum=0;
    public FoodOrderPayAdapter(Context context, List<Map<String, Object>> data){
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }
    public final class ViewHolder{
        public ImageView imageView1;
        public TextView foodName;
        public TextView foodPrice;
        public EditText beizhu;


    }

    @Override
    public int getCount(){
        return data.size();
    }
    @Override
    public Object getItem(int position){
        return position;
    }
    @Override
    public long getItemId(int position){
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        FoodOrderPayAdapter.ViewHolder vh = null;
        if (convertView == null){
            vh = new FoodOrderPayAdapter.ViewHolder();
            convertView = layoutInflater.inflate(R.layout.food_order_pay_listitem, null);
            vh.imageView1 = (ImageView)convertView.findViewById(R.id.food_order_view_pay_imageView1);
            vh.foodName = (TextView)convertView.findViewById(R.id.food_order_view_pay_name);
            vh.foodPrice = (TextView)convertView.findViewById(R.id.food_order_view_pay_price);
            vh.beizhu = (EditText)convertView.findViewById(R.id.food_order_view_pay_text);
            convertView.setTag(vh);
        }
        else {
            vh = (FoodOrderPayAdapter.ViewHolder)convertView.getTag();
        }
//        vh.imageView1.setBackgroundResource((Integer)data.get(position).get("image1"));
        vh.foodName.setText((String)data.get(position).get("foodName"));
        vh.foodPrice.setText((String)data.get(position).get("foodPrice"));
        vh.beizhu.setText((String)data.get(position).get("beizhu"));

        return convertView;
    }

}
