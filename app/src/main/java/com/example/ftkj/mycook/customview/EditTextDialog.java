package com.example.ftkj.mycook.customview;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ftkj.mycook.R;
import com.example.ftkj.mycook.db.LiteOrmUtil;
import com.example.ftkj.mycook.db.model.DiscussBean;
import com.litesuits.orm.LiteOrm;

import me.shaohui.bottomdialog.BaseBottomDialog;

/**
 * Created by shaohui on 16/10/12.
 */

public class EditTextDialog extends BaseBottomDialog {

    private EditText mEditText;
    private int newsId;
    private TextView mSend;
    private static LiteOrm liteOrm;
    private InputMethodManager mImm;

    private EditTextDialog mMyDialog;
    public EditTextDialog(int id){
        newsId = id;
    }
    @Override
    public int getLayoutRes() {
        return R.layout.dialog_edit_text;
    }

    @Override
    public void bindView(View v) {
        /**
         * 初始化数据库
         */

        liteOrm = LiteOrmUtil.getLiteOrm(getContext());

        mEditText = (EditText) v.findViewById(R.id.edit_text);
        mSend = ((TextView) v.findViewById(R.id.discuss_send));
        mEditText.post(new Runnable() {
            @Override
            public void run() {
                mImm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                mImm.showSoftInput(mEditText, 0);
            }
        });

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //存储数据库
                    DiscussBean discussBean = new DiscussBean();
                    discussBean.setNewsId(newsId);
                    discussBean.setMessage(mEditText.getText().toString().trim());
                    liteOrm.insert(discussBean);
                }catch (Exception e){
                    Toast.makeText(getContext(), "评论失败", Toast.LENGTH_SHORT).show();

                }finally {
                    mImm.hideSoftInputFromWindow(mEditText.getWindowToken(),0);
                    mMyDialog.dismiss();
                }
                Toast.makeText(getContext(), "评论成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public float getDimAmount() {
        return 0.9f;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

   public void getMyDialog(EditTextDialog editTextDialog){
       mMyDialog = editTextDialog;

   }
}
