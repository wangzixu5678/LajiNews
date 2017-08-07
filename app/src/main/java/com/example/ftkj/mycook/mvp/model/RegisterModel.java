package com.example.ftkj.mycook.mvp.model;

import android.util.Log;

import com.example.ftkj.mycook.bean.ClassifyBean;
import com.example.ftkj.mycook.bean.RegEvent;
import com.example.ftkj.mycook.bean.RegisterBean;
import com.example.ftkj.mycook.interfaces.DataListener;
import com.example.ftkj.mycook.mvp.contract.CommonContract;
import com.example.ftkj.mycook.mvp.contract.RegisterContract;
import com.example.ftkj.mycook.net.NetWorkUtil;
import com.example.ftkj.mycook.utils.MyInformationUtil;
import com.example.ftkj.mycook.utils.NetUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by FTKJ on 2017/5/26.
 */

public class RegisterModel implements CommonContract.IModel<RegEvent,RegisterBean> {

    @Override
    public void readData(RegEvent event, final DataListener<String, RegisterBean> listener) {
        NetWorkUtil.getServices().getRegResult(MyInformationUtil.client_id,
                MyInformationUtil.client_secret,
                event.getEmil(),
                event.getUsername(),
                event.getPassword())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RegisterBean>() {
                    @Override
                    public void onCompleted() {
                        listener.onCompleted("注册完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e.toString());
                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        listener.onNext(registerBean);

                    }
                });

    }
}
