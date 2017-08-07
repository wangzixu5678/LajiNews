package com.example.ftkj.mycook.activity;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;

import com.example.ftkj.mycook.R;
import com.example.ftkj.mycook.base.BaseActivity;
import com.example.ftkj.mycook.broadcast.NetWorkReceiver;
import com.example.ftkj.mycook.customview.BarEntity;
import com.example.ftkj.mycook.customview.BottomTabBar;
import com.example.ftkj.mycook.fragments.ImageFragment;
import com.example.ftkj.mycook.fragments.NewsFragment;
import com.example.ftkj.mycook.fragments.UserFragment;
import com.example.ftkj.mycook.fragments.VideoFragment;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements BottomTabBar.OnSelectListener {
    private BottomTabBar tb ;
    private List<BarEntity> bars ;
    private FragmentManager manager ;
    private NewsFragment mNewsFragment;
    private ImageFragment mImageFragment;
    private VideoFragment mVideoFragment;
    private UserFragment mUserFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void ininView() {
        manager = getSupportFragmentManager();
        tb = (BottomTabBar) findViewById(R.id.tb);
        tb.setManager(manager);
        tb.setOnSelectListener(this);
        bars = new ArrayList<>();
        bars.add(new BarEntity("新闻",R.drawable.ic_news_select,R.drawable.ic_news_unselect));
        bars.add(new BarEntity("图片",R.drawable.ic_image_select,R.drawable.ic_image_unselect));
        bars.add(new BarEntity("视频",R.drawable.ic_video_select,R.drawable.ic_video_unselect));
        bars.add(new BarEntity("我的",R.drawable.ic_user_select,R.drawable.ic_user_unselect));
        tb.setBars(bars);
    }
    @Override
    public void onSelect(int position) {
        switch (position){
            case 0:
                if (mNewsFragment==null){
                    mNewsFragment = new NewsFragment();
                }
                tb.switchContent(mNewsFragment);
                break;
            case 1:
                if (mImageFragment==null){
                    mImageFragment = new ImageFragment();
                }
                tb.switchContent(mImageFragment);
                break;
            case 2:
                if (mVideoFragment==null){
                    mVideoFragment = new VideoFragment();
                }
                tb.switchContent(mVideoFragment);
                break;
            case 3:
                if (mUserFragment==null){
                    mUserFragment = new UserFragment();
                }
                tb.switchContent(mUserFragment);
                break;

        }

    }


}
