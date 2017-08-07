package com.example.ftkj.mycook.mvp.presenter;

import com.example.ftkj.mycook.activity.RegisterActivity;
import com.example.ftkj.mycook.bean.RegEvent;
import com.example.ftkj.mycook.bean.RegisterBean;
import com.example.ftkj.mycook.interfaces.DataListener;
import com.example.ftkj.mycook.mvp.contract.CommonContract;
import com.example.ftkj.mycook.mvp.contract.RegisterContract;
import com.example.ftkj.mycook.mvp.model.RegisterModel;
import com.example.ftkj.mycook.utils.GsonUtils;

/**
 * Created by FTKJ on 2017/5/26.
 */

public class RegisterPresenter extends MBasePresenter<RegisterModel,RegisterActivity> implements CommonContract.IPresenter<RegEvent> {
    @Override
    public void load(RegEvent event) {
        model.readData(event, new DataListener<String,RegisterBean>() {
            @Override
            public void onCompleted(String s) {
                view.getData(null,s);

            }

            @Override
            public void onError(String s) {
                view.getData(null,s);

            }

            @Override
            public void onNext(RegisterBean registerBean) {
                view.getData(registerBean, GsonUtils.toJson(registerBean));
            }

        });

    }
}
