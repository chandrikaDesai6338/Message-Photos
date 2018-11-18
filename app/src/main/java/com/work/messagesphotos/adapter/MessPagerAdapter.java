package com.work.messagesphotos.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.work.messagesphotos.ui.AllFragment;
import com.work.messagesphotos.ui.SBUTFragment;
import com.work.messagesphotos.ui.TwitterFragment;

public class MessPagerAdapter extends FragmentStatePagerAdapter {
    public MessPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "All";
            case 1:
                return "SBUT";
            case 2:
                return "Twitter";
            default:
                return null;
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AllFragment();
            case 1:
                return new SBUTFragment();
            case 2:
                return new TwitterFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
