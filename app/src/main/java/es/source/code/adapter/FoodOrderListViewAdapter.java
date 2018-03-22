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
 * Created by Cristo on 2017/10/16.
 */

public class FoodOrderListViewAdapter extends BaseAdapter {

    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;
    int ordernum=0;
    public FoodOrderListViewAdapter(Context context, List<Map<String, Object>> data){
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }
    public final class ViewHolder{
        public ImageView imageView1;
        public TextView foodName;
        public TextView foodPrice;
        public EditText beizhu;
        public Button button;

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
        ViewHolder vh = null;
        if (convertView == null){
            vh = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.food_oder_not_pay_listitem, null);
            vh.imageView1 = (ImageView)convertView.findViewById(R.id.food_order_view_imageView1);
            vh.foodName = (TextView)convertView.findViewById(R.id.food_order_view_name);
            vh.foodPrice = (TextView)convertView.findViewById(R.id.food_order_view_price);
            vh.beizhu = (EditText)convertView.findViewById(R.id.food_order_view_text);
            vh.button = (Button)convertView.findViewById(R.id.food_order_cancel_button);
            convertView.setTag(vh);
        }
        else {
            vh = (ViewHolder)convertView.getTag();
        }
//        vh.imageView1.setBackgroundResource((Integer)data.get(position).get("image1"));
        vh.foodName.setText((String)data.get(position).get("foodName"));
        vh.foodPrice.setText((String)data.get(position).get("foodPrice"));



        vh.button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                Toast.makeText(context,"点菜成功",Toast.LENGTH_LONG).show();

            }
        });

        return convertView;
    }

}
