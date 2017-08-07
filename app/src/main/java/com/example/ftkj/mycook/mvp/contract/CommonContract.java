package com.example.ftkj.mycook.mvp.contract;

import android.support.annotation.Nullable;

import com.example.ftkj.mycook.interfaces.DataListener;


/**
 * Created by FTKJ on 2017/6/22.
 */

/**
 * T 为网络获取数据
 * F 为查询条件对象
 */
public interface CommonContract {
    interface IView<T> extends MContract.IView{
        void getData(T t,String string);
    }
    interface IPresenter<F> extends MContract.IPresenter{
        void load(F f);
    }
    interface IModel<F,T> extends MContract.IModel{
        void readData(F f,DataListener<String,T> listener);
    }
}
