package com.example.ftkj.mycook.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.example.ftkj.mycook.R;
import com.example.ftkj.mycook.adapters.XRecyclerViewAdapter;
import com.example.ftkj.mycook.base.BaseFragment;
import com.example.ftkj.mycook.bean.NewsListBean;
import com.example.ftkj.mycook.bean.QueryEvent;
import com.example.ftkj.mycook.cache.MyCache;
import com.example.ftkj.mycook.mvp.contract.CommonContract;
import com.example.ftkj.mycook.mvp.presenter.ItemNewsPresenter;
import com.example.ftkj.mycook.net.UrlConstant;
import com.example.ftkj.mycook.utils.GsonUtils;
import com.example.ftkj.mycook.utils.LoadAnimationUtils;
import com.example.ftkj.mycook.utils.NetUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by FTKJ on 2017/5/23.
 */

public class NewsItemFragment extends BaseFragment<ItemNewsPresenter> implements CommonContract.IView<NewsListBean> {


    @BindView(R.id.fm_xrecyclerview)
    XRecyclerView mFmXrecyclerview;
    @BindView(R.id.fm_loadAnim)
    ImageView mFmLoadAnim;
    private int mId;
    private ArrayList<NewsListBean.TngouBean> mList;
    private XRecyclerViewAdapter mAdapter;
    private int page;
    private static final int REFRESH_FLOG = 1;
    private static final int LOADMORE_FLOG = 2;
    private int flag;
    private LoadAnimationUtils mAnimationUtils;

    @Override
    protected void onInit() {
        /**
         * 播放帧动画
         */
        mAnimationUtils = new LoadAnimationUtils();
        mAnimationUtils.setAnimation(mFmLoadAnim);
        mAnimationUtils.setOneShot(false);
        mAnimationUtils.startAnimation();

        Bundle bundle = getArguments();
        mId = bundle.getInt("id");
        page = 1;
        /**
         * 初始化XRecyclerview
         */
        mList = new ArrayList<>();
        mAdapter = new XRecyclerViewAdapter(mList, getActivity());
        mFmXrecyclerview.setAdapter(mAdapter);
        mFmXrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mFmXrecyclerview.setArrowImageView(R.drawable.refresh_anim);

        /**
         * 判断是否有网络
         */
        if (NetUtil.getNetWorkState(getActivity())==NetUtil.NETWORK_NONE) {
            String JSONCache = MyCache.getCacheInstance().getData(UrlConstant.NEWSITEMFRM_CACHE_NAME + mId + ".txt");
            if (JSONCache!=null&&!"".equals(JSONCache)){
                NewsListBean newsListBean = (NewsListBean) GsonUtils.fromJson(JSONCache, NewsListBean.class);
                mFmXrecyclerview.setVisibility(View.VISIBLE);
                mAnimationUtils.stopAnimation();
                mFmLoadAnim.setVisibility(View.GONE);
                mList.clear();
                mList.addAll(newsListBean.getTngou());
                mAdapter.notifyDataSetChanged();
            }
        }else {
            QueryEvent queryEvent = new QueryEvent(mId, 1);
            presenter.load(queryEvent);
        }
    }

    @Override
    protected void onListener() {
        mFmXrecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                flag = REFRESH_FLOG;
                QueryEvent queryEvent = new QueryEvent(mId, 1);
                presenter.load(queryEvent);
            }

            @Override
            public void onLoadMore() {
                flag = LOADMORE_FLOG;
                page = page + 1;
                QueryEvent queryEvent = new QueryEvent(mId, page);
                presenter.load(queryEvent);
            }
        });
    }

    @Override
    public void getData(NewsListBean newsListBean, String string) {
        if (newsListBean != null) {
            mFmXrecyclerview.setVisibility(View.VISIBLE);
            mAnimationUtils.stopAnimation();
            mFmLoadAnim.setVisibility(View.GONE);
            List<NewsListBean.TngouBean> tngouBeen = newsListBean.getTngou();
            if (flag == REFRESH_FLOG) {
                mList.clear();
                mList.addAll(tngouBeen);
                mAdapter.notifyDataSetChanged();
                mFmXrecyclerview.refreshComplete();
            } else {
                mList.addAll(tngouBeen);
                mAdapter.notifyDataSetChanged();
                flag = REFRESH_FLOG;
                mFmXrecyclerview.loadMoreComplete();
            }
        }


    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_item;
    }

    public static NewsItemFragment getInstance(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        NewsItemFragment myFragment = new NewsItemFragment();
        myFragment.setArguments(bundle);
        return myFragment;
    }


}
