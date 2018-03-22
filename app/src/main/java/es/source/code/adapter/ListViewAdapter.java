package es.source.code.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import cristo.example.com.myapplication.R;

/**
 * Created by Cristo on 2017/10/15.
 */

public class ListViewAdapter extends BaseAdapter {

    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;
     int ordernum=0;
    public ListViewAdapter(Context context, List<Map<String, Object>> data){
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }
    public final class ViewHolder{
        public ImageView imageView1;
        public TextView foodName;
        public TextView foodPrice;
        public TextView numtext;
        public ImageView imageView2;
        public ImageView imageView3;
        public TextView remain;
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
            convertView = layoutInflater.inflate(R.layout.list_view_item, null);
            vh.imageView1 = (ImageView)convertView.findViewById(R.id.imageView1);
            vh.foodName = (TextView)convertView.findViewById(R.id.name);
            vh.foodPrice = (TextView)convertView.findViewById(R.id.price);
            vh.numtext = (TextView)convertView.findViewById(R.id.numtext);
            vh.imageView2 = (ImageView)convertView.findViewById(R.id.imageView2);
            vh.imageView3 = (ImageView)convertView.findViewById(R.id.imageView3);
            vh.remain=(TextView)convertView.findViewById(R.id.remain);
            convertView.setTag(vh);
        }
        else {
            vh = (ViewHolder)convertView.getTag();
        }
        vh.imageView1.setBackgroundResource((Integer)data.get(position).get("image1"));
        vh.foodName.setText((String)data.get(position).get("foodName"));
        vh.foodPrice.setText((String)data.get(position).get("foodPrice"));
        vh.numtext.setText((String)data.get(position).get("numtext"));
        vh.imageView2.setBackgroundResource((Integer)data.get(position).get("image2"));
        vh.imageView3.setBackgroundResource((Integer)data.get(position).get("image3"));
        vh.remain.setText((String)data.get(position).get("remain"));
        vh.imageView2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
//                 final ViewHolder viewHolder= new ViewHolder();;
//                 ordernum=ordernum+1;
//                viewHolder.numtext.setText(ordernum);


            }
        });
        vh.imageView3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                Toast.makeText(context,"退菜成功",Toast.LENGTH_LONG).show();

            }
        });
        return convertView;
    }
}
