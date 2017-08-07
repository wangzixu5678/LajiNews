package com.example.ftkj.mycook.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ftkj.mycook.fragments.NewsFragment;
import com.example.ftkj.mycook.fragments.NewsItemFragment;

import java.util.List;

/**
 * Created by FTKJ on 2017/4/1.
 */

public class NewsItemFragementAdapter extends FragmentPagerAdapter {
    private List<NewsItemFragment> mList;
    public NewsItemFragementAdapter(FragmentManager fm, List<NewsItemFragment> list) {
        super(fm);
        mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

}
