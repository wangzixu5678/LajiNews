package com.example.ftkj.mycook.mvp.contract;

import com.example.ftkj.mycook.bean.NewsDetailBean;
import com.example.ftkj.mycook.bean.NewsListBean;
import com.example.ftkj.mycook.bean.QueryEvent;
import com.example.ftkj.mycook.interfaces.DataListener;

/**
 * Created by FTKJ on 2017/5/24.
 */

public interface NewsDetailContract  {
    interface IView extends MContract.IView{
        void getData(NewsDetailBean newsDetailBean,String string);
        void getData2(String string);
    }
    interface IPresenter extends MContract.IPresenter{
        void load(int id);
        void load2(int id);
    }
    interface IModel extends MContract.IModel{
        void readData(int id, DataListener<String,NewsDetailBean> listener);
        void readDate2(int id,DataListener<String,String> listener);
    }
}
