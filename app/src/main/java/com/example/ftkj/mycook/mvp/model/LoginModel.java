package com.example.ftkj.mycook.mvp.model;

import android.util.Log;

import com.example.ftkj.mycook.bean.LogEvent;
import com.example.ftkj.mycook.bean.LoginBean;
import com.example.ftkj.mycook.interfaces.DataListener;
import com.example.ftkj.mycook.mvp.contract.CommonContract;
import com.example.ftkj.mycook.mvp.contract.LoginContract;
import com.example.ftkj.mycook.net.NetWorkUtil;
import com.example.ftkj.mycook.utils.MyInformationUtil;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by FTKJ on 2017/5/26.
 */

public class LoginModel implements CommonContract.IModel<LogEvent,LoginBean> {
    @Override
    public void readData(final LogEvent event, final DataListener<String,LoginBean> listener) {
        NetWorkUtil.getServices().getLogResult(MyInformationUtil.client_id,
                MyInformationUtil.client_secret,event.getUsername(),event.getPassword())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginBean>() {
                    @Override
                    public void onCompleted() {
                        listener.onCompleted("登陆成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e.toString());

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        listener.onNext(loginBean);

                    }
                });


    }
}
