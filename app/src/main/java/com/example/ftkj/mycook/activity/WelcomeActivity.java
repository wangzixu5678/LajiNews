package com.example.ftkj.mycook.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.ftkj.mycook.App;
import com.example.ftkj.mycook.R;
import com.example.ftkj.mycook.base.BaseActivity;


/**
 * Created by FTKJ on 2017/5/26.
 */

public class WelcomeActivity extends BaseActivity {
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 998:
                    go(LoginActivity.class);
                    finish();
                    break;
            }
            return true;
        }
    });

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void ininView() {
       if (App.isFirst){
           mHandler.sendEmptyMessageDelayed(998,3000);
       }else {
           go(LoginActivity.class);
       }
    }
}
