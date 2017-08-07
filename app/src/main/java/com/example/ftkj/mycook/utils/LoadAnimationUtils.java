package com.example.ftkj.mycook.utils;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

/**
 * ***********************************************************
 * author: paul
 * time: 2016/10/29 15:39
 * name:
 * desc:
 * ***********************************************************
 */

public class LoadAnimationUtils {
    AnimationDrawable mAnimationDrawable;

    public LoadAnimationUtils() {
        mAnimationDrawable = new AnimationDrawable();
    }

    public void setAnimation(ImageView imageView) {
        mAnimationDrawable = (AnimationDrawable) imageView.getBackground();
        mAnimationDrawable.start();
    }

    public void startAnimation() {
        mAnimationDrawable.start();
    }

    public void stopAnimation() {
        mAnimationDrawable.stop();
    }

    public void setOneShot(boolean oneShot) {
        mAnimationDrawable.setOneShot(oneShot);
    }
}
