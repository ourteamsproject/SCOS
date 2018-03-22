package es.source.code.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import es.source.code.Fragment.FoodOrderNotPay;
import es.source.code.Fragment.FoodOrderPay;


/**
 * Created by Cristo on 2017/10/16.
 */

public class FoodOrderViewAdapter extends FragmentStatePagerAdapter {
    int nNumOfTabs;
    public FoodOrderViewAdapter(FragmentManager fragmentManager, int nNumOfTabs){
        super(fragmentManager);
        this.nNumOfTabs = nNumOfTabs;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                FoodOrderNotPay food1 = new FoodOrderNotPay();
                return food1;
            case 1:
                 FoodOrderPay food2 = new FoodOrderPay();
                return food2;

        }
        return null;
    }

    @Override
    public int getCount(){
        return nNumOfTabs;
    }

}