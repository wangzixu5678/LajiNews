package com.example.ftkj.mycook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ftkj.mycook.R;
import com.example.ftkj.mycook.base.BaseActivity;
import com.example.ftkj.mycook.bean.NewsDetailBean;
import com.example.ftkj.mycook.customview.EditTextDialog;
import com.example.ftkj.mycook.mvp.contract.NewsDetailContract;
import com.example.ftkj.mycook.mvp.presenter.NewsDetailPresenter;
import com.example.ftkj.mycook.net.UrlConstant;
import com.example.ftkj.mycook.utils.CollapsingToolbarLayoutState;
import com.example.ftkj.mycook.utils.SimpleDateUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by FTKJ on 2017/5/24.
 */

public class NewsDetailActivity extends BaseActivity<NewsDetailPresenter> implements NewsDetailContract.IView {


    @BindView(R.id.tv_image)
    ImageView mTvImage;
    @BindView(R.id.at_time)
    TextView mAtTime;
    @BindView(R.id.at_fromname)
    TextView mAtFromname;
    @BindView(R.id.at_title)
    TextView mAtTitle;
    @BindView(R.id.at_message)
    TextView mAtMessage;
    @BindView(R.id.at_count)
    TextView mAtCount;
    @BindView(R.id.tv_appbarlayout)
    AppBarLayout mTvAppbarlayout;
    @BindView(R.id.at_share_txt)
    TextView mAtShareTxt;
    @BindView(R.id.at_shareqq)
    ImageView mAtShareqq;
    @BindView(R.id.at_sharesina)
    ImageView mAtSharesina;
    @BindView(R.id.at_sharewechat)
    ImageView mAtSharewechat;
    @BindView(R.id.at_rcount)
    EditText mAtRcount;
    private CollapsingToolbarLayoutState state;
    private int mId;
    private EditTextDialog mDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mId = getIntent().getIntExtra("newsId", 0);
        if (mId != 0) {
            presenter.load(mId);
            presenter.load2(mId);
        }
    }

    @Override
    protected void ininView() {

    }

    @Override
    protected void onListener() {
        mTvAppbarlayout.addOnOffsetChangedListener(
                new AppBarLayout.OnOffsetChangedListener() {
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                        if (verticalOffset == 0) {
                            if (state != CollapsingToolbarLayoutState.EXPANDED) {
                                state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开

                            }
                        } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                            if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                                //collapsingToolbarLayout.setTitle("");//设置title不显示
                                mTitle.setVisibility(View.VISIBLE);
                                state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                            }
                        } else {
                            if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                                if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                                    mTitle.setVisibility(View.GONE);
                                }
                                state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                            }
                        }
                    }
                }
        );
    }

    @Override
    public void getData(NewsDetailBean newsDetailBean, String string) {
        if (newsDetailBean != null) {
            setToolbar(newsDetailBean.getKeywords());
            mTitle.setVisibility(View.GONE);
            Glide.with(this).load(UrlConstant.IMAGEBASEURL + newsDetailBean.getImg()).into(mTvImage);
            mAtTime.setText(SimpleDateUtil.parseDatetime(newsDetailBean.getTime(), "MM-dd HH:mm"));
            mAtFromname.setText(newsDetailBean.getFromname());
            //mAtCount.setText(String.valueOf(newsDetailBean.getRcount()));
            mAtTitle.setText(newsDetailBean.getTitle());
            mAtMessage.setText(Html.fromHtml(newsDetailBean.getMessage().trim()));


        }


    }

    @Override
    public void getData2(String s) {
        if (!"".equals(s)){
            mAtCount.setText(s.trim());
        }else {
            mAtCount.setText("0");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_newsdetail;
    }

    @OnClick({R.id.at_rcount,R.id.at_count,R.id.at_qipao})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.at_rcount:
                /**
                 * TODO:开始编辑评论
                 */
                mDialog = new EditTextDialog(mId);
                mDialog.getMyDialog(mDialog);
                mDialog.show(getSupportFragmentManager());
                break;
            case R.id.at_qipao:
                goDiscuss();
                break;
            case R.id.at_count:
                goDiscuss();
                break;



        }
    }

    private void goDiscuss() {
        Intent intent = new Intent(this,DiscussActivity.class);
        intent.putExtra("newsId",mId);
        startActivity(intent);
    }

}
