package com.example.ftkj.mycook.interfaces;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public interface DataListener<T,F> {
    void onCompleted(T t);
    void onError(T t);
    void onNext(F f);
}
