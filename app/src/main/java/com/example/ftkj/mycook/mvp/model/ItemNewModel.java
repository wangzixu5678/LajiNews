package com.example.ftkj.mycook.mvp.model;



import com.example.ftkj.mycook.bean.NewsListBean;
import com.example.ftkj.mycook.bean.QueryEvent;
import com.example.ftkj.mycook.cache.MyCache;
import com.example.ftkj.mycook.interfaces.DataListener;
import com.example.ftkj.mycook.mvp.contract.CommonContract;
import com.example.ftkj.mycook.net.NetWorkUtil;
import com.example.ftkj.mycook.net.UrlConstant;
import com.example.ftkj.mycook.utils.GsonUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * Created by FTKJ on 2017/5/23.
 */

public class ItemNewModel implements CommonContract.IModel<QueryEvent,NewsListBean> {

    @Override
    public void readData(final QueryEvent qe, final DataListener<String, NewsListBean> listener) {
        NetWorkUtil.getServices().getNewsList(qe.getId(),qe.getPage())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .doOnNext(new Action1<NewsListBean>() {
                    @Override
                    public void call(NewsListBean newsListBean) {
                        if (qe.getPage()==1){
                            MyCache cacheInstance = MyCache.getCacheInstance();
                            cacheInstance.saveData(GsonUtils.toJson(newsListBean), UrlConstant.NEWSITEMFRM_CACHE_NAME+qe.getId()+".txt");
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsListBean>() {
                    @Override
                    public void onCompleted() {
                        listener.onCompleted("网络请求完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                       listener.onError(e.toString());
                    }

                    @Override
                    public void onNext(NewsListBean newsListBean) {
                            listener.onNext(newsListBean);
                    }
                });
    }
}
