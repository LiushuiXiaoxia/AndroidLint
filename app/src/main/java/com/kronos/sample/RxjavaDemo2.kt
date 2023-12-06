package com.kronos.sample

import rx.Observable
import rx.android.schedulers.AndroidSchedulers

class RxjavaDemo2 {

    fun test() {
        Observable.just("hello world")
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}