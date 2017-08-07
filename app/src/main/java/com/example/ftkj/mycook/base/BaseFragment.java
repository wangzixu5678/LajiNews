package com.example.ftkj.mycook.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ftkj.mycook.R;
import com.example.ftkj.mycook.mvp.presenter.MBasePresenter;
import com.example.ftkj.mycook.mvp.view.GenericHelper;

import butterknife.ButterKnife;

/**
 * Created by FTKJ on 2017/5/22.
 */

public abstract class BaseFragment<B extends MBasePresenter> extends Fragment {
    public B presenter;
    public View rootView;
    private Toast toast;
    private ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("test", "onCreateView: baseFragment ");
        try{
            presenter = GenericHelper.newPresenter(this);
        }catch (Exception e) {
            e.printStackTrace();
        }
        rootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this,rootView);
        onInit();
        onListener();
        if (presenter != null) {
            presenter.start();
        }
        return rootView;
    }

    /**
     * 添加监听
     */
    protected void onListener(){

        Log.d("test", "this is the base fragment listener ");
    }

    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected void onInit(){
        Log.d("test", "this is the base fragment init ");
    }

    public View getRootView() {
        return this.rootView;
    }

    /**
     * 显示吐司
     *
     * @param msg
     */
    public void showTs(String msg) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * 显示菊花
     * 使用默认提示
     */
    public void showDialog() {
        showDialog(getString(R.string.tips_loading));
    }

    /**
     * 显示菊花
     *
     * @param msg
     */
    public void showDialog(String msg) {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(msg);
        progressDialog.show();
    }

    /**
     * 隐藏掉菊花
     */
    public void dissDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        presenter.end();
    }
    public <T extends View> T getView(int layoutId){
        return ((T) rootView.findViewById(layoutId));
    }


}
