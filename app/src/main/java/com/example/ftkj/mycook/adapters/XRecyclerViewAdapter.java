package com.example.ftkj.mycook.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ftkj.mycook.R;
import com.example.ftkj.mycook.activity.NewsDetailActivity;
import com.example.ftkj.mycook.bean.NewsListBean;
import com.example.ftkj.mycook.net.UrlConstant;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by FTKJ on 2017/5/23.
 */

public class XRecyclerViewAdapter extends RecyclerView.Adapter {



    private List<NewsListBean.TngouBean> mList;
    private static Context mContext;

    public XRecyclerViewAdapter(List<NewsListBean.TngouBean> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertview = LayoutInflater.from(mContext).inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(convertview);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NewsViewHolder) {
            ((NewsViewHolder) holder).bindView(mList.get(position), mContext);
        }
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (mList != null) {
            ret = mList.size();
        }
        return ret;
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.news_image)
        ImageView mNewsImage;
        @BindView(R.id.news_title)
        TextView mNewsTitle;
        @BindView(R.id.news_fromname)
        TextView mNewsFromname;
        @BindView(R.id.news_time)
        TextView mNewsTime;
        @BindView(R.id.news_slayout)
        LinearLayout mNewsSlayout;
        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(final NewsListBean.TngouBean tngouBean, Context context) {
            if (tngouBean != null) {
                Glide.with(context).load(UrlConstant.IMAGEBASEURL + tngouBean.getImg())
                        .into(mNewsImage);
                mNewsTitle.setText(tngouBean.getTitle().trim());
                mNewsFromname.setText(tngouBean.getFromname().trim());
                SimpleDateFormat format = new SimpleDateFormat("dd天前");
                Date date = new Date(tngouBean.getTime());
                String formatString = format.format(date);
                mNewsTime.setText(formatString);
                Log.d("AAAA", "bindView: " + tngouBean.getImg());
                mNewsSlayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, NewsDetailActivity.class);
                        intent.putExtra("newsId",tngouBean.getId());
                        mContext.startActivity(intent);
                    }
                });
            }
        }
    }
}
