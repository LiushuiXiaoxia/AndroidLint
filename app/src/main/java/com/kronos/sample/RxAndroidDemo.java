package com.kronos.sample;


import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class RxAndroidDemo {

    void test() {
        Observable.just("hello world")
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}