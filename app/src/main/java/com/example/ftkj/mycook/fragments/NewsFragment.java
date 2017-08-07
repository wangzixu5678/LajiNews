package com.example.ftkj.mycook.fragments;


import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ftkj.mycook.R;
import com.example.ftkj.mycook.adapters.NewsItemFragementAdapter;
import com.example.ftkj.mycook.base.BaseFragment;
import com.example.ftkj.mycook.bean.ClassifyBean;
import com.example.ftkj.mycook.broadcast.NetWorkReceiver;
import com.example.ftkj.mycook.cache.MyCache;
import com.example.ftkj.mycook.mvp.contract.CommonContract;
import com.example.ftkj.mycook.mvp.contract.MainNewsContract;
import com.example.ftkj.mycook.mvp.presenter.MainNewsPresenter;
import com.example.ftkj.mycook.net.UrlConstant;
import com.example.ftkj.mycook.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by FTKJ on 2017/5/22.
 */

public class NewsFragment extends BaseFragment<MainNewsPresenter> implements CommonContract.IView<ClassifyBean>, TabLayout.OnTabSelectedListener {

    @BindView(R.id.fm_tablayout)
    TabLayout mFmTablayout;
    @BindView(R.id.fm_viewpager)
    ViewPager mFmViewpager;
    @BindView(R.id.fm_unnetwork)
    TextView mFmUnnetwork;
    private ArrayList<NewsItemFragment> mFragments;
    private NewsItemFragementAdapter mAdapter;
    private NetWorkReceiver mNetWorkReceiver;
    @Override
    protected void onInit() {
        /**
         * 开启无网络广播
         */
        rigisterBroadCast();
        /**
         * 初始化Fragment+viewpager
         */
        mFragments = new ArrayList<>();
        mAdapter = new NewsItemFragementAdapter(getChildFragmentManager(), mFragments);
        mFmViewpager.setAdapter(mAdapter);
        /**
         * 开启缓存
         */
        String JSONCache = MyCache.getCacheInstance().getData(UrlConstant.NEWSFRAGMENT_CACHE_NAME);
        if (JSONCache != null && !"".equals(JSONCache)) {
            ClassifyBean classifyBean = (ClassifyBean) GsonUtils.fromJson(JSONCache, ClassifyBean.class);
            loadViewpager(classifyBean);
        } else {
            presenter.load("");
        }
    }

    private void rigisterBroadCast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        mNetWorkReceiver = new NetWorkReceiver(mFmUnnetwork);
        getActivity().registerReceiver(mNetWorkReceiver,intentFilter);
    }

    @Override
    protected void onListener() {
        mFmTablayout.setOnTabSelectedListener(this);
        mFmViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mFmTablayout));
    }

    @Override
    public void getData(ClassifyBean classifyBean, String string) {
        loadViewpager(classifyBean);


    }

    private void loadViewpager(ClassifyBean classifyBean) {
        TabLayout.Tab tab;
        if (classifyBean != null) {
            List<ClassifyBean.TngouBean> tngou = classifyBean.getTngou();
            for (int i = 0; i < tngou.size(); i++) {
                tab = mFmTablayout.newTab();
                tab.setText(tngou.get(i).getName());
                mFmTablayout.addTab(tab);
                NewsItemFragment newsItemFragment = NewsItemFragment.getInstance(tngou.get(i).getId());
                mFragments.add(newsItemFragment);
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mFmViewpager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}
