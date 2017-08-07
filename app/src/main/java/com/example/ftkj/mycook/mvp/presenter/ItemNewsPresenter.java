package com.example.ftkj.mycook.mvp.presenter;

import com.example.ftkj.mycook.bean.NewsListBean;
import com.example.ftkj.mycook.bean.QueryEvent;
import com.example.ftkj.mycook.fragments.NewsItemFragment;
import com.example.ftkj.mycook.interfaces.DataListener;
import com.example.ftkj.mycook.mvp.contract.CommonContract;
import com.example.ftkj.mycook.mvp.model.ItemNewModel;
import com.example.ftkj.mycook.utils.GsonUtils;

/**
 * Created by FTKJ on 2017/5/23.
 */

public class ItemNewsPresenter extends MBasePresenter<ItemNewModel,NewsItemFragment> implements CommonContract.IPresenter<QueryEvent> {

    @Override
    public void load(QueryEvent qe) {
        model.readData(qe, new DataListener<String, NewsListBean>() {
            @Override
            public void onCompleted(String s) {
               view.getData(null,s);
            }

            @Override
            public void onError(String s) {
                view.getData(null,s);
            }
            @Override
            public void onNext(NewsListBean newsListBean) {
                view.getData(newsListBean, GsonUtils.toJson(newsListBean));
            }
        });

    }
}
