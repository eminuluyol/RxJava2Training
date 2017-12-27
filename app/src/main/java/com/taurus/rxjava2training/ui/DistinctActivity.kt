package com.taurus.rxjava2training.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.taurus.rxjava2training.R.layout
import com.taurus.rxjava2training.extensions.TAG
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_common.button
import kotlinx.android.synthetic.main.activity_common.textView

class DistinctActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_common)

    button.setOnClickListener({ _ -> doSomeWork() })
  }

  /*
   * distinct() suppresses duplicate items emitted by the source Observable.
   */
  private fun doSomeWork() {

    getObservable()
        .distinct()
        .subscribe(getObserver())
  }

  private fun getObservable(): Observable<Int> {
    return Observable.just(1, 2, 1, 1, 2, 3, 4, 6, 4)
  }

  private fun getObserver(): Observer<Int> {
    return object : Observer<Int> {

      override fun onSubscribe(d: Disposable) {
        Log.d(TAG(), " onSubscribe : " + d.isDisposed)
      }

      override fun onNext(value: Int) {
        textView.append(" onNext : value : " + value)
        textView.append(LINE_SEPARATOR)
        Log.d(TAG(), " onNext value : " + value)
      }

      override fun onError(e: Throwable) {
        Log.d(TAG(), " onError : " + e.message)
      }

      override fun onComplete() {
        Log.d(TAG(), " onComplete")
      }
    }
  }
}
