package com.example.ftkj.mycook.adapters.listviewcommon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.ftkj.mycook.bean.ImageItemBean;
import com.example.ftkj.mycook.fragments.ImageFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FTKJ on 2017/3/22.
 */

public abstract class CommonAdapter<T> extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<T> mDatas;
    private int mItemLayoutId;

    public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }



    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = getViewHolder(position,convertView, parent);
        convert(holder,getItem(position));
        return holder.getConvertView();
    }

    public abstract void convert(ViewHolder holder, T item);

    private ViewHolder getViewHolder(int position,View convertView,ViewGroup parent){
        return ViewHolder.get(mContext,convertView,parent,mItemLayoutId,position);
    }


}
