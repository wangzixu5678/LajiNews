package com.example.ftkj.mycook.mvp.model;

import com.example.ftkj.mycook.bean.ImageListBean;
import com.example.ftkj.mycook.bean.QueryEvent;
import com.example.ftkj.mycook.interfaces.DataListener;
import com.example.ftkj.mycook.mvp.contract.CommonContract;
import com.example.ftkj.mycook.mvp.contract.ImageListContract;
import com.example.ftkj.mycook.net.NetWorkUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by FTKJ on 2017/6/5.
 */

public class ImageListModel implements CommonContract.IModel<QueryEvent,ImageListBean> {
    @Override
    public void readData(QueryEvent qe, final DataListener<String,ImageListBean> listener) {
        int id = qe.getId();
        int page = qe.getPage();
        int rows = qe.getRows();
        NetWorkUtil.getServices().getImageList(id,page,rows).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ImageListBean>() {
                    @Override
                    public void onCompleted() {
                        listener.onCompleted("数据请求完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e.toString());
                    }

                    @Override
                    public void onNext(ImageListBean imageListBean) {
                        listener.onNext(imageListBean);
                    }
                });

    }
}
