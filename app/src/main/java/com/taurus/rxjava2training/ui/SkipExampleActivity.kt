package com.taurus.rxjava2training.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.taurus.rxjava2training.R.layout
import com.taurus.rxjava2training.extensions.TAG
import io.reactivex.Observable
import io.reactivex.Observer
import kotlinx.android.synthetic.main.activity_common.button
import kotlinx.android.synthetic.main.activity_common.textView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SkipExampleActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_common)

    button.setOnClickListener({ _ -> doSomeWork() })
  }

  /* Using skip operator, it will not emit
    * the first 2 values.
    */
  private fun doSomeWork() {
    getObservable()
        // Run on a background thread
        .subscribeOn(Schedulers.io())
        // Be notified on the main thread
        .observeOn(AndroidSchedulers.mainThread())
        .skip(2)
        .subscribe(getObserver())
  }

  private fun getObservable(): Observable<Int> {
    return Observable.just(1, 2, 3, 4, 5)
  }

  private fun getObserver(): Observer<Int> {
    return object : Observer<Int> {

      override fun onSubscribe(d: Disposable) {
        Log.d(TAG(), " onSubscribe : " + d.isDisposed)
      }

      override fun onNext(value: Int) {
        textView.append(" onNext : ")
        textView.append(LINE_SEPARATOR)
        textView.append(" value : " + value)
        textView.append(LINE_SEPARATOR)
        Log.d(TAG(), " onNext ")
        Log.d(TAG(), " value : " + value)
      }

      override fun onError(e: Throwable) {
        textView.append(" onError : " + e.message)
        textView.append(LINE_SEPARATOR)
        Log.d(TAG(), " onError : " + e.message)
      }

      override fun onComplete() {
        textView.append(" onComplete")
        textView.append(LINE_SEPARATOR)
        Log.d(TAG(), " onComplete")
      }
    }
  }
}

/**
 * Returns an Observable that skips the first {@code count} items emitted by the source ObservableSource and emits
 * the remainder.
 */
