package com.kronos.sample;


import rx.Observable;

public class RxJavaDemo {

    void test() {
        Observable.just("hello world").subscribe();
    }
}