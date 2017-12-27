package com.taurus.rxjava2training.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.taurus.rxjava2training.R.layout
import com.taurus.rxjava2training.extensions.TAG
import com.taurus.rxjava2training.model.Car
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_common.button
import kotlinx.android.synthetic.main.activity_common.textView

class DeferExampleActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_common)

    button.setOnClickListener({ _ -> doSomeWork() })
  }

  /*
   * Defer used for Deferring Observable code until subscription in RxJava
   */
  private fun doSomeWork() {

    val car = Car()

    val brandDeferObservable = car.brandDeferObservable()

    car.brand = "BMW"  // Even if we are setting the brand after creating Observable
    // we will get the brand as BMW.
    // If we had not used defer, we would have got null as the brand.

    brandDeferObservable
        .subscribe(getObserver())
  }

  private fun getObserver(): Observer<String> {
    return object : Observer<String> {

      override fun onSubscribe(d: Disposable) {
        Log.d(TAG(), " onSubscribe : " + d.isDisposed)
      }

      override fun onNext(value: String) {
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
 * Observable defer basically make us sure that even if we set the value of observable after creating
 * Observable, we will get the correct value. If we had not used defer, we would would have got null.
 */