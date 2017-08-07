package com.example.ftkj.mycook.mvp.model;

import com.example.ftkj.mycook.App;
import com.example.ftkj.mycook.db.LiteOrmUtil;
import com.example.ftkj.mycook.db.model.DiscussBean;
import com.example.ftkj.mycook.interfaces.DataListener;
import com.example.ftkj.mycook.mvp.contract.CommonContract;
import com.example.ftkj.mycook.mvp.contract.DiscussContract;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by FTKJ on 2017/5/25.
 */

public class DiscussModel implements CommonContract.IModel<Integer,List<DiscussBean>> {
    private LiteOrm liteorm;
    @Override
    public void readData(final Integer newsId, final DataListener<String,List<DiscussBean>> listener) {
        Observable.create(new Observable.OnSubscribe<List<DiscussBean>>() {
            @Override
            public void call(Subscriber<? super List<DiscussBean>> subscriber) {
                liteorm = LiteOrmUtil.getLiteOrm(App.getAppContext());
                ArrayList<DiscussBean> list = liteorm.query(new QueryBuilder<>(DiscussBean.class)
                        .whereEquals("newsId", newsId));
                subscriber.onNext(list);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<DiscussBean>>() {
                    @Override
                    public void onCompleted() {
                        listener.onCompleted("数据获取完成");

                    }
                    @Override
                    public void onError(Throwable e) {
                        listener.onCompleted("数据获取失败");
                    }

                    @Override
                    public void onNext(List<DiscussBean> discusslist) {
                        listener.onNext(discusslist);
                    }
                });



    }
}
