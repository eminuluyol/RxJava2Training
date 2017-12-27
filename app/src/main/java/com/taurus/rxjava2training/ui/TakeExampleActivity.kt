package com.taurus.rxjava2training.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.taurus.rxjava2training.R.layout
import com.taurus.rxjava2training.extensions.TAG
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_common.button
import kotlinx.android.synthetic.main.activity_common.textView

class TakeExampleActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_common)

    button.setOnClickListener({ _ -> doSomeWork() })
  }

  /* Using take operator, it only emits
    * required number of values. here only 3 out of 5
    */
  private fun doSomeWork() {
    getObservable()
        // Run on a background thread
        .subscribeOn(Schedulers.io())
        // Be notified on the main thread
        .observeOn(AndroidSchedulers.mainThread())
        .take(3)
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
        textView.append(" onNext : value : " + value)
        textView.append(LINE_SEPARATOR)

        Log.d(TAG(), " onNext : " + value)
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
 * Returns an Observable that emits only the first {@code count} items emitted by the source ObservableSource. If the source emits fewer than
 * {@code count} items then all of its items are emitted.
 */
