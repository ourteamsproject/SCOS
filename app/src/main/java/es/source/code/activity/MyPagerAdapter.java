package es.source.code.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import es.source.code.Fragment.ColdFood;
import es.source.code.Fragment.HotFood;
import es.source.code.Fragment.JiuShui;
import es.source.code.Fragment.SeaFood;

/**
 * Created by Cristo on 2017/10/13.
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    int nNumOfTabs;
    public MyPagerAdapter(FragmentManager fragmentManager, int nNumOfTabs){
        super(fragmentManager);
        this.nNumOfTabs = nNumOfTabs;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                ColdFood coldFood = new ColdFood();
                return coldFood;
            case 1:
                HotFood hotFood = new HotFood();
                return hotFood;
            case 2:
                SeaFood seaFood = new SeaFood();
                return seaFood;
            case 3:
                JiuShui jiushui = new JiuShui();
                return jiushui;
        }
        return null;
    }

    @Override
    public int getCount(){
        return nNumOfTabs;
    }

}
