package com.example.ftkj.mycook.mvp.presenter;

import android.support.annotation.Nullable;

import com.example.ftkj.mycook.bean.ClassifyBean;
import com.example.ftkj.mycook.fragments.NewsFragment;
import com.example.ftkj.mycook.interfaces.DataListener;
import com.example.ftkj.mycook.mvp.contract.CommonContract;
import com.example.ftkj.mycook.mvp.contract.MainNewsContract;
import com.example.ftkj.mycook.mvp.model.MainNewsModel;
import com.example.ftkj.mycook.utils.GsonUtils;

/**
 * Created by FTKJ on 2017/5/22.
 */

public class MainNewsPresenter extends MBasePresenter<MainNewsModel,NewsFragment> implements CommonContract.IPresenter<String> {

    @Override
    public void load(String s) {
        model.readData(s,new DataListener<String,ClassifyBean>() {
            @Override
            public void onCompleted(String s) {
                 view.getData(null,s);
            }

            @Override
            public void onError(String s) {
                 view.getData(null,s);
            }

            @Override
            public void onNext(ClassifyBean classifyBean) {
                view.getData(classifyBean, GsonUtils.toJson(classifyBean));
            }
        });
    }


}