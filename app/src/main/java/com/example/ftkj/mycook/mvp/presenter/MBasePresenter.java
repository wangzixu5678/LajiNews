package com.example.ftkj.mycook.mvp.presenter;


import com.example.ftkj.mycook.mvp.contract.MContract;

/**
 * Created by wwj on 2017/5/9.
 */

public abstract class MBasePresenter<M extends MContract.IModel,V extends MContract.IView> {
    public M model;
    public V view;
    public void init(Object view, Object model) {
        this.view = (V) view;
        this.model = (M) model;
    }
    /*
    * 开始方法
    * */
    public void start(){
    }
    public void end(){
        view=null;
        model=null;
    }
}
