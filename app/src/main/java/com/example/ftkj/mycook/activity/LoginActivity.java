package com.example.ftkj.mycook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chaychan.viewlib.PowerfulEditText;
import com.example.ftkj.mycook.R;
import com.example.ftkj.mycook.base.BaseActivity;
import com.example.ftkj.mycook.bean.LogEvent;
import com.example.ftkj.mycook.bean.LoginBean;
import com.example.ftkj.mycook.mvp.contract.CommonContract;
import com.example.ftkj.mycook.mvp.contract.LoginContract;
import com.example.ftkj.mycook.mvp.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by FTKJ on 2017/5/25.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements CommonContract.IView<LoginBean> {
    @BindView(R.id.log_btn_login)
    TextView mLogBtnLogin;
    @BindView(R.id.log_btn_rigister)
    TextView mLogBtnRigister;
    @BindView(R.id.log_edit_acount)
    PowerfulEditText mLogEditAcount;
    @BindView(R.id.log_edit_password)
    PowerfulEditText mLogEditPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void ininView() {

    }

    @OnClick({R.id.log_btn_login, R.id.log_btn_rigister})
    public void onClick(View view) {
//        String userAccount = mLogEditAcount.getText().toString().trim();
//        String userPassword = mLogEditPassword.getText().toString().trim();

        switch (view.getId()) {
            case R.id.log_btn_login:
//                LogEvent event = new LogEvent(userAccount,userPassword);
//                presenter.load(event);'
                go(MainActivity.class);

                break;
            case R.id.log_btn_rigister:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void getData(LoginBean loginBean, String string) {
        if (loginBean!=null){
            Log.d("AAA", "getData: "+string);
        }
    }
}
