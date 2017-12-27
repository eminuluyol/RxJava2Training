package com.taurus.rxjava2training.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.taurus.rxjava2training.R.layout
import com.taurus.rxjava2training.extensions.TAG
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_common.button
import kotlinx.android.synthetic.main.activity_common.textView
import java.util.concurrent.TimeUnit

class CompletableObserverExampleActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_common)

    button.setOnClickListener({ _ -> doSomeWork() })
  }

  /*
     * simple example using CompletableObserver
     */
  private fun doSomeWork() {
    val completable = Completable.timer(1000, TimeUnit.MILLISECONDS)

    completable
        .subscribeOn(Schedulers.io())
        // Be notified on the main thread
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(getCompletableObserver())
  }

  private fun getCompletableObserver(): CompletableObserver {
    return object : CompletableObserver {
      override fun onSubscribe(d: Disposable) {
        Log.d(TAG(), " onSubscribe : " + d.isDisposed)
      }

      override fun onComplete() {
        textView.append(" onComplete")
        textView.append(LINE_SEPARATOR)
        Log.d(TAG(), " onComplete")
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
 * Basically completable obsever is used for the cases when we don't expect any acknowledgment. Best fits
 * with DB writing etc. where we don't expect any data and only interested in if it is failed or completed
 */
