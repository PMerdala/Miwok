package com.example.pmerdala.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by merdala on 2017-11-02.
 */

public class MiwokFragmentPagerAdapter extends FragmentPagerAdapter {

    private final Context context;

    public MiwokFragmentPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position){
            case 0: fragment= new NumbersFragment();break;
            case 1: fragment =new FamilyFragment();break;
            case 2: fragment=new ColorsFragment();break;
            case 3: fragment=new PhrasesFragment();break;
            default:fragment=null;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence fragmentName;
        switch(position){
            case 0: fragmentName = getString(R.string.category_numbers) ;break;
            case 1: fragmentName = getString(R.string.category_family) ;break;
            case 2: fragmentName = getString(R.string.category_colors) ;break;
            case 3: fragmentName = getString(R.string.category_phrases) ;break;
            default: fragmentName = null;
        }
        return fragmentName;
    }

    private CharSequence getString(int resourceId){
        return context.getResources().getString(resourceId);
    }
}
