package com.taurus.rxjava2training.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.taurus.rxjava2training.R.layout
import com.taurus.rxjava2training.extensions.TAG
import io.reactivex.Flowable
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_common.button
import kotlinx.android.synthetic.main.activity_common.textView

class FlowableExampleActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_common)

    button.setOnClickListener({ _ -> doSomeWork() })
  }

  /*
     * simple example using Flowable
     */
  private fun doSomeWork() {

    val observable = Flowable.just(1, 2, 3, 4)

    observable.reduce(50) { t1, t2 ->
      t1 + t2
    }.subscribe(getObserver())

  }

  private fun getObserver(): SingleObserver<Int> {

    return object : SingleObserver<Int> {
      override fun onSubscribe(d: Disposable) {
        Log.d(TAG(), " onSubscribe : " + d.isDisposed)
      }

      override fun onSuccess(value: Int) {
        textView.append(" onSuccess : value : " + value!!)
        textView.append(LINE_SEPARATOR)
        Log.d(TAG(), " onSuccess : value : " + value)
      }

      override fun onError(e: Throwable) {
        textView.append(" onError : " + e.message)
        textView.append(LINE_SEPARATOR)
        Log.d(TAG(), " onError : " + e.message)
      }
    }
  }
}

/**
 * Flowable is basically backpressure support of Observables. The main issue with back pressure is
 * that many hot sources, such as UI events, can't be reasonably backpressured and cause unexpected
 * MissingBackpressureExpection. Use Flowable when you have relatively large amount of items and you
 * need to carefully control how Producer behaves in order to avoid resource exhaustion and congestion
 * Best fits with DB queries when it is more than 1.000 items
 */
