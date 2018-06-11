package com.example.a1505197.deliveringurway;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by kiit on 29/6/17.
 */

class Pager extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    ContactRental tab2;
    ContactFragment tab3;

    public Pager(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ProductFragment tab1 = new ProductFragment();
                return tab1;
            case 1:

                VendorType vendorType=new VendorType();
                String Type=vendorType.getType();
                if(Type.equals("Rental"))
                {
                    tab2=new ContactRental();
                    return tab2;
                }
                else
                    {
                    tab3 = new ContactFragment();
                        return tab3;
                }

            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return mNumOfTabs;
    }
}
//////////