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


class BufferExampleActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_common)

    button.setOnClickListener({ _ -> doSomeWork() })
  }

  /*
     * simple example using buffer operator - bundles all emitted values into a list
     */
  private fun doSomeWork() {

    val buffered = getObservable().buffer(3, 1)

    // 3 means,  it takes max of three from its start index and create list
    // 1 means, it jumps one step every time
    // so the it gives the following list
    // 1 - one, two, three
    // 2 - two, three, four
    // 3 - three, four, five
    // 4 - four, five
    // 5 - five

    buffered.subscribe(getObserver())
  }

  private fun getObservable(): Observable<String> {
    return Observable.just("one", "two", "three", "four", "five")
  }

  private fun getObserver(): Observer<List<String>> {
    return object : Observer<List<String>> {

      override fun onSubscribe(d: Disposable) {
        Log.d(TAG(), " onSubscribe : " + d.isDisposed)
      }

      override fun onNext(stringList: List<String>) {
        textView.append(" onNext size : " + stringList.size)
        textView.append(LINE_SEPARATOR)
        Log.d(TAG(), " onNext : size :" + stringList.size)

        for (value in stringList) {
          textView.append(" value : " + value)
          textView.append(LINE_SEPARATOR)
          Log.d(TAG(), " : value :" + value)
        }

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
 * Returns an Observable that emits buffers of items it collects from the source ObservableSource. The resulting
 * ObservableSource emits buffers every {@code skip} items, each containing {@code count} items. When the source
 * ObservableSource completes or encounters an error, the resulting ObservableSource emits the current buffer and
 * propagates the notification from the source ObservableSource.
 */
