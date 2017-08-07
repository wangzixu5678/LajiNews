package com.example.ftkj.mycook.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ftkj.mycook.R;
import com.example.ftkj.mycook.base.BaseActivity;
import com.example.ftkj.mycook.bean.ImageListBean;
import com.example.ftkj.mycook.bean.QueryEvent;
import com.example.ftkj.mycook.mvp.contract.CommonContract;
import com.example.ftkj.mycook.mvp.contract.ImageListContract;
import com.example.ftkj.mycook.mvp.presenter.ImageListPresenter;
import com.example.ftkj.mycook.net.UrlConstant;
import com.example.ftkj.mycook.utils.GlideRoundTransform;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by FTKJ on 2017/6/5.
 */

public class ImageListActivity extends BaseActivity<ImageListPresenter> implements CommonContract.IView<ImageListBean>, ViewPager.OnPageChangeListener {

    @BindView(R.id.fm_iamges_viewpager)
    ViewPager mFmIamgesViewpager;
    @BindView(R.id.fm_images_backgroud)
    ImageView mFmImagesBackgroud;
    private List<ImageListBean.TngouBean> mIamges;
    private MyImagePagerAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int id = getIntent().getIntExtra("id", 0);
        if (id != 0) {
            QueryEvent queryEvent = new QueryEvent(id, 1, 5);
            presenter.load(queryEvent);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_imagelist;
    }

    @Override
    protected void ininView() {
        mFmIamgesViewpager.setOffscreenPageLimit(3);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mFmIamgesViewpager.setPageMargin((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, metrics));
        mFmIamgesViewpager.setPageTransformer(true, new MyPageTransform());
        mIamges = new ArrayList<>();
        mAdapter = new MyImagePagerAdapter(mIamges, this);
        mFmIamgesViewpager.setAdapter(mAdapter);
        mFmIamgesViewpager.setCurrentItem(0);
    }
    @Override
    protected void onListener() {
        mFmIamgesViewpager.addOnPageChangeListener(this);
    }

    @Override
    public void getData(ImageListBean imageListBean, String string) {
        if (imageListBean != null) {
            mIamges.clear();
            mIamges.addAll(imageListBean.getTngou());
            mAdapter.notifyDataSetChanged();
            mFmIamgesViewpager.setCurrentItem(0);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(final int position) {
        /**
         * ???
         */
        Observable.create(new Observable.OnSubscribe<List<ImageListBean.TngouBean>>() {
            @Override
            public void call(Subscriber<? super List<ImageListBean.TngouBean>> subscriber) {
                subscriber.onNext(mIamges);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .doOnNext(new Action1<List<ImageListBean.TngouBean>>() {
                    @Override
                    public void call(List<ImageListBean.TngouBean> tngouBeen) {
                        while (true){
                            if (tngouBeen!=null){
                                break;
                            }

                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<ImageListBean.TngouBean>>() {
                    @Override
                    public void call(List<ImageListBean.TngouBean> tngouBeen) {
                        Log.d("DDD", "call: "+"加载"+UrlConstant.IMAGEBASEURL + mIamges.get(position).getImg());
                        Glide.with(ImageListActivity.this).load(UrlConstant.IMAGEBASEURL + mIamges.get(position).getImg())
                                .into(mFmImagesBackgroud);
                    }
                });
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class MyImagePagerAdapter extends PagerAdapter {
        private List<ImageListBean.TngouBean> mList;
        private Context mContext;

        public MyImagePagerAdapter(List<ImageListBean.TngouBean> list, Context context) {
            mList = list;
            mContext = context;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(((View) object));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            Glide.with(mContext).load(UrlConstant.IMAGEBASEURL + mList.get(position).getImg())
                    .placeholder(R.drawable.placeholder)
                    .transform(new GlideRoundTransform(mContext, 20))
                    .into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    class MyPageTransform implements ViewPager.PageTransformer {

        final float SCALE_MAX = 0.8f;
        final float ALPHA_MAX = 0.5f;

        @Override
        public void transformPage(View page, float position) {
            if ((int) position < -1 || (int) position > 1) {
                return;
            }

            float scale = (position < 0)
                    ? ((1 - SCALE_MAX) * position + 1)
                    : ((SCALE_MAX - 1) * position + 1);
            float alpha = (position < 0)
                    ? ((1 - ALPHA_MAX) * position + 1)
                    : ((ALPHA_MAX - 1) * position + 1);
            if (position < 0) {
                ViewCompat.setPivotX(page, page.getWidth());
                ViewCompat.setPivotY(page, page.getHeight() / 2);
            } else {
                ViewCompat.setPivotX(page, 0);
                ViewCompat.setPivotY(page, page.getHeight() / 2);
            }
            //Log.d("AAA", "position: " + position + ",scale:" + scale);

            ViewCompat.setScaleX(page, scale);
            ViewCompat.setScaleY(page, scale);
            ViewCompat.setAlpha(page, Math.abs(alpha));
        }
    }
}
