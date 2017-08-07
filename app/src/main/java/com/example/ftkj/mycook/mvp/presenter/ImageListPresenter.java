package com.example.ftkj.mycook.mvp.presenter;

import com.example.ftkj.mycook.activity.ImageListActivity;
import com.example.ftkj.mycook.bean.ImageListBean;
import com.example.ftkj.mycook.bean.QueryEvent;
import com.example.ftkj.mycook.interfaces.DataListener;
import com.example.ftkj.mycook.mvp.contract.CommonContract;
import com.example.ftkj.mycook.mvp.contract.ImageListContract;
import com.example.ftkj.mycook.mvp.model.ImageListModel;
import com.example.ftkj.mycook.utils.GsonUtils;

/**
 * Created by FTKJ on 2017/6/5.
 */

public class ImageListPresenter extends MBasePresenter<ImageListModel, ImageListActivity> implements CommonContract.IPresenter<QueryEvent> {
    @Override
    public void load(QueryEvent qe) {
        model.readData(qe, new DataListener<String, ImageListBean>() {
            @Override
            public void onCompleted(String s) {
                view.getData(null, s);
            }

            @Override
            public void onError(String s) {
                view.getData(null, s);
            }

            @Override
            public void onNext(ImageListBean imageListBean) {
                view.getData(imageListBean,GsonUtils.toJson(imageListBean));
            }
        });

    }
}
