package com.taurus.rxjava2training.ui

import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.taurus.rxjava2training.R.layout
import com.taurus.rxjava2training.extensions.TAG
import com.taurus.rxjava2training.extensions.plusAssign
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_common.button
import kotlinx.android.synthetic.main.activity_common.textView

const val LINE_SEPARATOR = "\n"

class DisposableExampleActivity : AppCompatActivity() {

  private val disposables = CompositeDisposable()


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_common)

    button.setOnClickListener({ _ -> doSomeWork() })
  }

  override fun onDestroy() {
    super.onDestroy()
    disposables.clear() // do not send event after activity has been destroyed
  }

  private fun doSomeWork() {

    disposables += sampleObservable()
        // Run on a background thread
        .subscribeOn(Schedulers.io())
        // Be notified on the main thread
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(object : DisposableObserver<String>() {
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

          override fun onNext(value: String) {
            textView.append(" onNext : value : " + value)
            textView.append(LINE_SEPARATOR)
            Log.d(TAG(), " onNext value : " + value)
          }
        })
  }


  private fun sampleObservable(): Observable<String> {
    return Observable.defer {
      // Do some long running operation
      SystemClock.sleep(2000)
      Observable.just("one", "two", "three", "four", "five")
    }
  }
}

/**
 * Basically disposable is used for putting all observables in it and then clear all the observables
 * when the disposables.clear() method is called. Observables may causes memory leakes in order to
 * prevent these leakes we should try to terminate all obsevables when the view is destroyed
 */

