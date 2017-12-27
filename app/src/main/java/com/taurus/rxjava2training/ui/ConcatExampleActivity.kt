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

class ConcatExampleActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_common)

    button.setOnClickListener({ _ -> doSomeWork() })
  }

  /*
   * Using concat operator to combine Observable : concat maintain
   * the order of Observable.
   * It will emit all the 7 values in order
   * here - first "A1", "A2", "A3", "A4" and then "B1", "B2", "B3"
   * first all from the first Observable and then
   * all from the second Observable all in order
   */
  private fun doSomeWork() {
    val aStrings = arrayOf("A1", "A2", "A3", "A4")
    val bStrings = arrayOf("B1", "B2", "B3")

    val aObservable = Observable.fromArray(*aStrings)
    val bObservable = Observable.fromArray(*bStrings)

    Observable.concat(aObservable, bObservable)
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
 * Using concat operator to combine Observable : concat maintain
 * the order of Observable.
 * It will emit all the 7 values in order
 * here - first "A1", "A2", "A3", "A4" and then "B1", "B2", "B3"
 * first all from the first Observable and then
 * all from the second Observable all in order
 */
