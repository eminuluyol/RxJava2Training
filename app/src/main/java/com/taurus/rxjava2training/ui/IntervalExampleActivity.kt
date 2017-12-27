package com.taurus.rxjava2training.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.taurus.rxjava2training.R.layout
import com.taurus.rxjava2training.extensions.TAG
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_common.button
import kotlinx.android.synthetic.main.activity_common.textView
import java.util.concurrent.TimeUnit


class IntervalExampleActivity : AppCompatActivity() {

  private val disposables = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_common)

    button.setOnClickListener({ _ -> doSomeWork() })
  }

  override fun onDestroy() {
    super.onDestroy()
    disposables.clear() // clearing it : do not emit after destroy
  }

  /*
     * simple example using interval to run task at an interval of 2 sec
     * which start immediately
     */
  private fun doSomeWork() {
    disposables.add(getObservable()
        // Run on a background thread
        .subscribeOn(Schedulers.io())
        // Be notified on the main thread
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(getObserver()))
  }

  private fun getObservable(): Observable<out Long> {
    return Observable.interval(0, 2, TimeUnit.SECONDS)
  }

  private fun getObserver(): DisposableObserver<Long> {
    return object : DisposableObserver<Long>() {

      override fun onNext(value: Long) {
        textView.append(" onNext : value : " + value)
        textView.append(LINE_SEPARATOR)
        Log.d(TAG(), " onNext : value : " + value)
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
 * Returns an Observable that emits a {@code 0L} after the {@code initialDelay} and ever increasing numbers
 * after each {@code period} of time thereafter.
 */
