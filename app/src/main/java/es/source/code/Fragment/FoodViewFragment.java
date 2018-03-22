package es.source.code.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cristo.example.com.myapplication.R;
import es.source.code.adapter.FoodViewAdapter;
import es.source.code.adapter.ListViewAdapter;


public class FoodViewFragment extends ListFragment {
    private final static String TAG = "DummyFragment";

    private TextView tvInfo;
    private ListView listView;
    private int tabindex = 0;
    private List<Map<String, Object>> totalList = new ArrayList<Map<String, Object>>();

    public static FoodViewFragment getInstance(int tabindex) {
        FoodViewFragment fragment = new FoodViewFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("tabindex", tabindex);
        fragment.setArguments(bundle);
        return fragment;
    }

    // 数据初始化
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        tabindex = bundle.getInt("tabindex");
        totalList = loadNetworkData();
    }

    // 真实场景中应该访问网络，json解析，返回list
    private List<Map<String, Object>> loadNetworkData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 20; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("icon", R.mipmap.sub);
            map.put("name", "maodou" + i);
            map.put("price", 10*i);
            map.put("image", R.mipmap.addred);

            list.add(map);
        }
        return list;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cold_food, container, false);
        listView=view.findViewById(R.id.listview);
//        SimpleAdapter adapter = new SimpleAdapter(
//                getActivity(),
//                totalList,
//                R.layout.list_view_item,
//                new String[]{"icon", "name", "price","image"},
//                new int[]{R.id.imageView1, R.id.name, R.id.price,R.id.imageView2});
//        setListAdapter(adapter);
        List<Map<String, Object>> list=loadNetworkData();
//        listView.setAdapter(new FoodViewAdapter(getActivity(),totalList));
        FoodViewAdapter adapter=new FoodViewAdapter(getActivity(),list);
        setListAdapter(adapter);
          return view;
    }

}
