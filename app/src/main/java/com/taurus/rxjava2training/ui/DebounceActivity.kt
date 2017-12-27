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

class DebounceActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_common)

    button.setOnClickListener({ _ -> doSomeWork() })
  }

  /*
    * Using debounce() -> only emit an item from an Observable if a particular time-span has
    * passed without it emitting another item, so it will emit 2, 4, 5 as we have simulated it.
    */
  private fun doSomeWork() {
    getObservable()
        .debounce(500, TimeUnit.MILLISECONDS)
        // Run on a background thread
        .subscribeOn(Schedulers.io())
        // Be notified on the main thread
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(getObserver())
  }

  private fun getObservable(): Observable<Int> {
    return Observable.create({ emitter ->
      // send events with simulated time wait
      emitter.onNext(1) // skip
      Thread.sleep(400)
      emitter.onNext(2) // deliver
      Thread.sleep(505)
      emitter.onNext(3) // skip
      Thread.sleep(100)
      emitter.onNext(4) // deliver
      Thread.sleep(605)
      emitter.onNext(5) // deliver
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
        textView.append(" onNext : ")
        textView.append(LINE_SEPARATOR)
        textView.append(" value : " + value!!)
        textView.append(LINE_SEPARATOR)
        Log.d(TAG(), " onNext ")
        Log.d(TAG(), " value : " + value!!)
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
  * Using debounce() -> only emit an item from an Observable if a particular time-span has
  * passed without it emitting another item, so it will emit 2, 4, 5 as we have simulated it.
  */