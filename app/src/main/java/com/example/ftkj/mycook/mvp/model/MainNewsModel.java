package com.example.ftkj.mycook.mvp.model;

import com.example.ftkj.mycook.bean.ClassifyBean;
import com.example.ftkj.mycook.cache.MyCache;
import com.example.ftkj.mycook.interfaces.DataListener;
import com.example.ftkj.mycook.mvp.contract.CommonContract;
import com.example.ftkj.mycook.mvp.contract.MainNewsContract;
import com.example.ftkj.mycook.net.NetWorkUtil;
import com.example.ftkj.mycook.net.ServiceApi;
import com.example.ftkj.mycook.net.UrlConstant;
import com.example.ftkj.mycook.utils.GsonUtils;
import com.example.ftkj.mycook.utils.SDUtils;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by FTKJ on 2017/5/22.
 */

public class MainNewsModel implements CommonContract.IModel<String,ClassifyBean> {


    @Override
    public void readData(String s,final DataListener<String,ClassifyBean> listener) {
        ServiceApi serviceApi = NetWorkUtil.getServices();
        Observable<ClassifyBean> observable = serviceApi.getClassifyModel();
        observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .doOnNext(new Action1<ClassifyBean>() {
                    @Override
                    public void call(ClassifyBean classifyBean) {
                        MyCache cacheInstance = MyCache.getCacheInstance();
                        cacheInstance.saveData(GsonUtils.toJson(classifyBean), UrlConstant.NEWSFRAGMENT_CACHE_NAME);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifyBean>() {
                    @Override
                    public void onCompleted() {
                        listener.onCompleted("网络请求完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                       listener.onError(e.toString());

                    }

                    @Override
                    public void onNext(ClassifyBean classifyBean) {
                       listener.onNext(classifyBean);
                    }
                });

    }
}
