package com.example.ftkj.mycook.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ftkj.mycook.R;
import com.example.ftkj.mycook.base.BaseActivity;
import com.example.ftkj.mycook.bean.RegEvent;
import com.example.ftkj.mycook.bean.RegisterBean;
import com.example.ftkj.mycook.fragments.ImageFragment;
import com.example.ftkj.mycook.mvp.contract.CommonContract;
import com.example.ftkj.mycook.mvp.contract.RegisterContract;
import com.example.ftkj.mycook.mvp.presenter.RegisterPresenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by FTKJ on 2017/5/26.
 */

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements CommonContract.IView<RegisterBean>{

    @BindView(R.id.rigist_username_edit)
    EditText mRigistUsernameEdit;
    @BindView(R.id.rigist_username_layout)
    TextInputLayout mRigistUsernameLayout;
    @BindView(R.id.rigist_password_edit)
    EditText mRigistPasswordEdit;
    @BindView(R.id.rigist_email_edit)
    EditText mRigistEmailEdit;
    @BindView(R.id.rigist_email_layout)
    TextInputLayout mRigistEmailLayout;
    @BindView(R.id.rigister_rigist_btn)
    TextView mRigisterRigistBtn;
    @BindView(R.id.rigist_password_layout)
    TextInputLayout mRigistPasswordLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rigister;
    }

    @Override
    protected void ininView() {
        setToolbar("注册");


    }

    @Override
    protected void onListener() {
        mRigistUsernameLayout.getEditText().addTextChangedListener(new MyUsernameWatch());
        mRigistPasswordLayout.getEditText().addTextChangedListener(new MyPasswordWatch());
        mRigistEmailLayout.getEditText().addTextChangedListener(new MyEmailWatch());
    }

    @OnClick(R.id.rigister_rigist_btn)
    public void onClick() {
        String emil = mRigistEmailEdit.getText().toString().trim();
        String password = mRigistPasswordEdit.getText().toString().trim();
        String username = mRigistUsernameEdit.getText().toString().trim();
        if ("".equals(emil)||"".equals(password)||"".equals(username)){
                   showToast("信息不能为空");
        }else {
            if (mRigistPasswordLayout.isErrorEnabled()||mRigistUsernameLayout.isErrorEnabled()||mRigistEmailLayout.isErrorEnabled()){
                showToast("输入信息有误");
            }else {
                /**
                 * TODO满足注册条件，向服务器进行注册。
                 */
                RegEvent event = new RegEvent(username, password, emil);
                presenter.load(event);

            }
        }
    }

    @Override
    public void getData(RegisterBean registerBean, String string) {
        if (registerBean!=null) {
            if (registerBean.isStatus()) {
                showToast("注册成功");
            } else {
               showToast(registerBean.getMsg());
            }
        }
    }


    private class MyUsernameWatch implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() < 6) {
                mRigistUsernameLayout.setError("用户名长度必须六位以上");
                mRigistUsernameLayout.setErrorEnabled(true);
            } else {
                mRigistUsernameLayout.setErrorEnabled(false);

            }
        }
    }

    private class MyPasswordWatch implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() < 6) {
                mRigistPasswordLayout.setError("密码设置必须六位以上");
                mRigistPasswordLayout.setErrorEnabled(true);
            } else {
                mRigistPasswordLayout.setErrorEnabled(false);
            }
        }
    }

    private class MyEmailWatch implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!checkEmail(mRigistEmailEdit.getText().toString().trim())){
                mRigistEmailLayout.setError("请输入正确邮箱格式");
                mRigistEmailLayout.setErrorEnabled(true);
            }else {
                mRigistEmailLayout.setErrorEnabled(false);
            }

        }
    }


    /**
     *   * 验证邮箱
     *   * @param email
     *   * @return
     *  
     */
    public  boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}
