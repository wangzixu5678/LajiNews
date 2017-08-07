package com.example.ftkj.mycook.mvp.model;

import com.example.ftkj.mycook.App;
import com.example.ftkj.mycook.bean.NewsDetailBean;
import com.example.ftkj.mycook.db.LiteOrmUtil;
import com.example.ftkj.mycook.db.model.DiscussBean;
import com.example.ftkj.mycook.interfaces.DataListener;
import com.example.ftkj.mycook.mvp.contract.NewsDetailContract;
import com.example.ftkj.mycook.net.NetWorkUtil;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;

import java.util.ArrayList;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by FTKJ on 2017/5/24.
 */

public class NewsDetailModel implements NewsDetailContract.IModel {
    private LiteOrm liteorm;
    @Override
    public void readData(int id, final DataListener<String, NewsDetailBean> listener) {
        NetWorkUtil.getServices().getNewsDetail(id
        ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsDetailBean>() {
                    @Override
                    public void onCompleted() {
                        listener.onCompleted("网络请求完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e.toString());

                    }

                    @Override
                    public void onNext(NewsDetailBean newsDetailBean) {
                        listener.onNext(newsDetailBean);
                    }
                });

    }

    @Override
    public void readDate2(final int id, final DataListener<String, String> listener) {

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                liteorm = LiteOrmUtil.getLiteOrm(App.getAppContext());
                ArrayList<DiscussBean> list = liteorm.query(new QueryBuilder<>(DiscussBean.class)
                        .whereEquals("newsId",id));
                subscriber.onNext(list.size());
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        listener.onCompleted("");
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError("");

                    }

                    @Override
                    public void onNext(Integer integer) {
                        listener.onNext(String.valueOf(integer));
                    }
                });

    }
}
