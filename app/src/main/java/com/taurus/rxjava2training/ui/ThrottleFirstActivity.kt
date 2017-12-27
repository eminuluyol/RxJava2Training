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
import java.util.concurrent.TimeUnit

class ThrottleFirstActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_common)

    button.setOnClickListener({ _ -> doSomeWork() })
  }

  /*
    * Using throttleFirst() -> if the source Observable has emitted no items since
    * the last time it was sampled, the Observable that results from this operator
    * will emit no item for that sampling period.
    */
  private fun doSomeWork() {
    getObservable()
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        // Run on a background thread
        .subscribeOn(Schedulers.io())
        // Be notified on the main thread
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(getObserver())
  }

  private fun getObservable(): Observable<Int> {
    return Observable.create({ emitter ->
      // send events with simulated time wait
      Thread.sleep(0)
      emitter.onNext(1) // skip
      emitter.onNext(2) // deliver
      Thread.sleep(505)
      emitter.onNext(3) // skip
      Thread.sleep(99)
      emitter.onNext(4) // skip
      Thread.sleep(100)
      emitter.onNext(5) // skip
      emitter.onNext(6) // deliver
      Thread.sleep(305)
      emitter.onNext(7) // deliver
      Thread.sleep(510)
      emitter.onComplete()
    })
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
