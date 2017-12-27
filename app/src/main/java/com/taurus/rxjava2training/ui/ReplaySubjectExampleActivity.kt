package com.taurus.rxjava2training.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.taurus.rxjava2training.R.layout
import com.taurus.rxjava2training.extensions.TAG
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_common.button
import kotlinx.android.synthetic.main.activity_common.textView
import io.reactivex.subjects.ReplaySubject

class ReplaySubjectExampleActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_common)

    button.setOnClickListener({ _ -> doSomeWork() })
  }

  /* ReplaySubject emits to any observer all of the items that were emitted
   * by the source Observable, regardless of when the observer subscribes.
   */
  private fun doSomeWork() {

    val source = ReplaySubject.create<Int>()

    source.subscribe(getFirstObserver()) // it will get 1, 2, 3, 4

    source.onNext(1)
    source.onNext(2)
    source.onNext(3)
    source.onNext(4)
    source.onComplete()

    /*
     * it will emit 1, 2, 3, 4 for second observer also as we have used replay
     */
    source.subscribe(getSecondObserver())

  }

  private fun getFirstObserver(): Observer<Int> {
    return object : Observer<Int> {

      override fun onSubscribe(d: Disposable) {
        textView.append(" First onSubscribe : isDisposed :" + d.isDisposed)
        Log.d(TAG(), " First onSubscribe : " + d.isDisposed)
        textView.append(LINE_SEPARATOR)
      }

      override fun onNext(value: Int) {
        textView.append(" First onNext : value : " + value)
        textView.append(LINE_SEPARATOR)
        Log.d(TAG(), " First onNext value : " + value)
      }

      override fun onError(e: Throwable) {
        textView.append(" First onError : " + e.message)
        textView.append(LINE_SEPARATOR)
        Log.d(TAG(), " First onError : " + e.message)
      }

      override fun onComplete() {
        textView.append(" First onComplete")
        textView.append(LINE_SEPARATOR)
        Log.d(TAG(), " First onComplete")
      }
    }
  }

  private fun getSecondObserver(): Observer<Int> {
    return object : Observer<Int> {

      override fun onSubscribe(d: Disposable) {
        textView.append(" Second onSubscribe : isDisposed :" + d.isDisposed)
        Log.d(TAG(), " Second onSubscribe : " + d.isDisposed)
        textView.append(LINE_SEPARATOR)
      }

      override fun onNext(value: Int) {
        textView.append(" Second onNext : value : " + value)
        textView.append(LINE_SEPARATOR)
        Log.d(TAG(), " Second onNext value : " + value)
      }

      override fun onError(e: Throwable) {
        textView.append(" Second onError : " + e.message)
        textView.append(LINE_SEPARATOR)
        Log.d(TAG(), " Second onError : " + e.message)
      }

      override fun onComplete() {
        textView.append(" Second onComplete")
        textView.append(LINE_SEPARATOR)
        Log.d(TAG(), " Second onComplete")
      }
    }
  }
}

/**
 * It emits all the items of the source Observable, regardless of when the subscriber subscribes
 * Here, if a student entered late into the classroom, he wants to listen from the beginning.
 * So, here we will use Replay to achieve this.
 */