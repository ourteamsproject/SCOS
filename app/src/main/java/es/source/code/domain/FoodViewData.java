package es.source.code.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cristo.example.com.myapplication.R;

/**
 * Created by Cristo on 2017/10/21.
 */

public class FoodViewData {
    public List<Map<String, Object>> getData(Object image1,String name,String price,Object image2,String num,Object image3,String remain){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 15; i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image1", image1);
            map.put("foodName",name );
            map.put("foodPrice",price);
            map.put("image2",image2);
            map.put("numtext",num);
            map.put("image3",image3);
            map.put("remain",remain);
            list.add(map);
        }
        return list;
    }
}
