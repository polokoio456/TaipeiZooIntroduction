package com.nie.taipeizoo.extension

import com.nie.taipeizoo.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

fun Disposable.bind(viewModel: BaseViewModel) {
    viewModel.addDisposable(this)
}

fun Observable<Any>.throttleClick(duration: Long = 1_000): Observable<Any> {
    return this.throttleFirst(duration, TimeUnit.MILLISECONDS)
}