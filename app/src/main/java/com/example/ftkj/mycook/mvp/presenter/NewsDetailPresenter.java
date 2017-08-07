package com.example.ftkj.mycook.mvp.presenter;

import com.example.ftkj.mycook.activity.NewsDetailActivity;
import com.example.ftkj.mycook.bean.NewsDetailBean;
import com.example.ftkj.mycook.interfaces.DataListener;
import com.example.ftkj.mycook.mvp.contract.NewsDetailContract;
import com.example.ftkj.mycook.mvp.model.NewsDetailModel;
import com.example.ftkj.mycook.utils.GsonUtils;

/**
 * Created by FTKJ on 2017/5/24.
 */

public class NewsDetailPresenter extends MBasePresenter<NewsDetailModel,NewsDetailActivity> implements NewsDetailContract.IPresenter {
    @Override
    public void load(int id) {
        model.readData(id, new DataListener<String, NewsDetailBean>() {
            @Override
            public void onCompleted(String s) {
                view.getData(null,s);
            }

            @Override
            public void onError(String s) {
                view.getData(null,s);
            }

            @Override
            public void onNext(NewsDetailBean newsDetailBean) {
                view.getData(newsDetailBean, GsonUtils.toJson(newsDetailBean));
            }
        });
    }

    @Override
    public void load2(int id) {
        model.readDate2(id, new DataListener<String, String>() {
            @Override
            public void onCompleted(String s) {
                view.getData2(s);

            }

            @Override
            public void onError(String s) {
               view.getData2(s);
            }

            @Override
            public void onNext(String s) {
                view.getData2(s);
            }
        });

    }
}
