package com.example.ftkj.mycook.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.example.ftkj.mycook.utils.NetUtil;

/**
 * Created by FTKJ on 2017/5/25.
 */

public class NetWorkReceiver extends BroadcastReceiver{
    private TextView mTextView;
    public NetWorkReceiver(TextView textView){
        mTextView =textView;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        int netWorkState = NetUtil.getNetWorkState(context);

        if (netWorkState==NetUtil.NETWORK_NONE){
            mTextView.setVisibility(View.VISIBLE);
        }else {
            mTextView.setVisibility(View.GONE);
        }
    }
}
