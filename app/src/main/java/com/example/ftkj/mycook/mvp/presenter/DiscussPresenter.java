package com.example.ftkj.mycook.mvp.presenter;

import com.example.ftkj.mycook.activity.DiscussActivity;
import com.example.ftkj.mycook.db.model.DiscussBean;
import com.example.ftkj.mycook.interfaces.DataListener;
import com.example.ftkj.mycook.mvp.contract.CommonContract;
import com.example.ftkj.mycook.mvp.contract.DiscussContract;
import com.example.ftkj.mycook.mvp.model.DiscussModel;
import com.example.ftkj.mycook.utils.GsonUtils;

import java.util.List;

/**
 * Created by FTKJ on 2017/5/25.
 */

public class DiscussPresenter extends MBasePresenter<DiscussModel,DiscussActivity> implements CommonContract.IPresenter<Integer> {
    @Override
    public void load(Integer newsId) {
        model.readData(newsId,new DataListener<String,List<DiscussBean>>() {
            @Override
            public void onCompleted(String s) {
                view.getData(null,s);
            }

            @Override
            public void onError(String s) {
                view.getData(null,s);

            }

            @Override
            public void onNext(List<DiscussBean> discussBeanList) {
                view.getData(discussBeanList, GsonUtils.toJson(discussBeanList));
            }
        });

    }
}
