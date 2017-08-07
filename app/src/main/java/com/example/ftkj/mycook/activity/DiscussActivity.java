package com.example.ftkj.mycook.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ftkj.mycook.R;
import com.example.ftkj.mycook.adapters.listviewcommon.CommonAdapter;
import com.example.ftkj.mycook.adapters.listviewcommon.ViewHolder;
import com.example.ftkj.mycook.base.BaseActivity;
import com.example.ftkj.mycook.db.model.DiscussBean;
import com.example.ftkj.mycook.mvp.contract.CommonContract;
import com.example.ftkj.mycook.mvp.contract.DiscussContract;
import com.example.ftkj.mycook.mvp.presenter.DiscussPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by FTKJ on 2017/5/25.
 */

public class DiscussActivity extends BaseActivity<DiscussPresenter> implements CommonContract.IView<List<DiscussBean>> {

    @BindView(R.id.discuss_listview)
    ListView mDiscussListview;
    private ArrayList<DiscussBean> mDiscusslist;
    private CommonAdapter<DiscussBean> mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int newsId = getIntent().getIntExtra("newsId", 0);
        if (newsId != 0) {
            presenter.load(newsId);
        }

    }

    @Override
    protected void ininView() {
        setToolbar("全部评论");
        mDiscusslist = new ArrayList<>();
        mAdapter = new CommonAdapter<DiscussBean>(this, mDiscusslist, R.layout.discuss_item) {

            @Override
            public void convert(ViewHolder holder, DiscussBean item) {
                TextView textView = (TextView) holder.getView(R.id.discuss_text);
                textView.setText(item.getMessage());
            }
        };
        mDiscussListview.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_discuss;
    }


    @Override
    public void getData(List<DiscussBean> discussBeanList, String string) {
        if (discussBeanList != null) {
            mDiscusslist.clear();
            mDiscusslist.addAll(discussBeanList);
            mAdapter.notifyDataSetChanged();
        }

    }
}
