package com.taurus.rxjava2training.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.taurus.rxjava2training.R.layout
import com.taurus.rxjava2training.extensions.TAG
import io.reactivex.Observable
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_common.button
import kotlinx.android.synthetic.main.activity_common.textView

class LastOperatorActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_common)

    button.setOnClickListener({ _ -> doSomeWork() })
  }

  /*
    * last() emits only the last item emitted by the Observable.
    */
  private fun doSomeWork() {
    getObservable().last("A1") // the default item ("A1") to emit if the source ObservableSource is empty
        .subscribe(getObserver())
  }

  private fun getObservable(): Observable<String> {
    return Observable.just("A1", "A2", "A3", "A4", "A5", "A6")
  }

  private fun getObserver(): SingleObserver<String> {
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
