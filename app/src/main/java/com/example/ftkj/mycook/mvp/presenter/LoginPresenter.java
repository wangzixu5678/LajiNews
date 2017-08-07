package com.example.ftkj.mycook.mvp.presenter;

import com.example.ftkj.mycook.activity.LoginActivity;
import com.example.ftkj.mycook.bean.LogEvent;
import com.example.ftkj.mycook.bean.LoginBean;
import com.example.ftkj.mycook.interfaces.DataListener;
import com.example.ftkj.mycook.mvp.contract.CommonContract;
import com.example.ftkj.mycook.mvp.contract.LoginContract;
import com.example.ftkj.mycook.mvp.model.LoginModel;
import com.example.ftkj.mycook.utils.GsonUtils;

/**
 * Created by FTKJ on 2017/5/26.
 */

public class LoginPresenter extends MBasePresenter<LoginModel,LoginActivity> implements CommonContract.IPresenter<LogEvent> {
    @Override
    public void load(LogEvent event) {
        model.readData(event,new DataListener<String, LoginBean>() {
            @Override
            public void onCompleted(String s) {
                view.getData(null,s);

            }

            @Override
            public void onError(String s) {
                view.getData(null,s);

            }

            @Override
            public void onNext(LoginBean loginBean) {
                view.getData(loginBean, GsonUtils.toJson(loginBean));
            }
        });

    }
}
