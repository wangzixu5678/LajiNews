package com.example.ftkj.mycook.fragments;

import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ftkj.mycook.R;
import com.example.ftkj.mycook.activity.ImageListActivity;
import com.example.ftkj.mycook.adapters.listviewcommon.CommonAdapter;
import com.example.ftkj.mycook.adapters.listviewcommon.ViewHolder;
import com.example.ftkj.mycook.base.BaseFragment;
import com.example.ftkj.mycook.bean.ImageItemBean;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;


/**
 * Created by FTKJ on 2017/5/22.
 */

public class ImageFragment extends BaseFragment {

    @BindView(R.id.fm_image_listview)
    ListView mFmImageListview;
    String[] names = {
            "性感美女",
            "韩日美女",
            "丝袜美腿",
            "美女照片",
            "美女写真",
            "性感车模",
            "清纯美女"
    };

    @Override
    protected void onInit() {
        ArrayList<ImageItemBean> items = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            items.add(new ImageItemBean(names[i],getIntId(i+1),i+1));
        }

        CommonAdapter<ImageItemBean> adapter = new CommonAdapter<ImageItemBean>(getContext(),items,R.layout.images_item) {

            @Override
            public void convert(ViewHolder holder, final ImageItemBean item) {
                ImageView imageView = (ImageView) holder.getView(R.id.images_imageview);
                TextView name = (TextView) holder.getView(R.id.images_name);
                FrameLayout frameLayout = (FrameLayout) holder.getView(R.id.images_frame);
                imageView.setImageResource(item.getImageRes());
                name.setText(item.getClassifyName());
                frameLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (item.getId()){
                            case 1:
                                Intent intent = new Intent(getActivity(),ImageListActivity.class);
                                intent.putExtra("id",item.getId());
                                startActivity(intent);
                                break;
                        }
                    }
                });
            }
        };

        mFmImageListview.setAdapter(adapter);
    }

    int getIntId(int i){
        int ret = 0;
        String sId="imageitem"+i;
        Class<R.drawable> aClass = R.drawable.class;
        try {
            Field field = aClass.getDeclaredField(sId);
            field.setAccessible(true);
            ret = field.getInt(aClass);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_image;
    }


}
