package com.taurus.rxjava2training.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.taurus.rxjava2training.R.layout
import com.taurus.rxjava2training.extensions.TAG
import io.reactivex.MaybeObserver
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_common.button
import kotlinx.android.synthetic.main.activity_common.textView


class ReduceExampleActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_common)

    button.setOnClickListener({ _ -> doSomeWork() })
  }


  /*
     * simple example using reduce to add all the number
     */
  private fun doSomeWork() {
    getObservable()
        .reduce { t1, t2 -> t1 + t2 }
        .subscribe(getObserver())
  }

  private fun getObservable(): Observable<Int> {
    return Observable.just(1, 2, 3, 4)
  }

  private fun getObserver(): MaybeObserver<Int> {
    return object : MaybeObserver<Int> {

      override fun onSubscribe(d: Disposable) {
        Log.d(TAG(), " onSubscribe : " + d.isDisposed)
      }

      override fun onSuccess(value: Int) {
        textView.append(" onSuccess : value : " + value)
        textView.append(LINE_SEPARATOR)
        Log.d(TAG(), " onSuccess : value : " + value)
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
 * Returns a Maybe that applies a specified accumulator function to the first item emitted by a source
 * ObservableSource, then feeds the result of that function along with the second item emitted by the source
 * ObservableSource into the same function, and so on until all items have been emitted by the source ObservableSource,
 * and emits the final result from the final call to your function as its sole item.
 */
