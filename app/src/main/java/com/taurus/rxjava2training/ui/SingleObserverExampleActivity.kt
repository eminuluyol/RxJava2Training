package com.taurus.rxjava2training.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.taurus.rxjava2training.R.layout
import com.taurus.rxjava2training.extensions.TAG
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_common.button
import kotlinx.android.synthetic.main.activity_common.textView

class SingleObserverExampleActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_common)

    button.setOnClickListener({ _ -> doSomeWork() })
  }

  /*
     * simple example using Flowable
     */
  private fun doSomeWork() {

    Single.just("Emin")
        .subscribe(getSingleObserver())
  }

  private fun getSingleObserver(): SingleObserver<String> {
    return object : SingleObserver<String> {
      override fun onSubscribe(d: Disposable) {
        Log.d(TAG(), " onSubscribe : " + d.isDisposed)
      }

      override fun onSuccess(value: String) {
        textView.append(" onNext : value : " + value)
        textView.append(LINE_SEPARATOR)
        Log.d(TAG(), " onNext value : " + value)
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
 * Single Observer calls onSuccess when it obverves the all emitted data. That means it perfectly fits
 * with Network api calls. Because when we do an api call we are only interested in the success and error
 * cases. onSubscribe method provides an disposable which lets us to dispose the call whenever we want to
 */