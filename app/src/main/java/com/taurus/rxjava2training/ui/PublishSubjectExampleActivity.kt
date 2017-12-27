package com.taurus.rxjava2training.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.taurus.rxjava2training.R.layout
import com.taurus.rxjava2training.extensions.TAG
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_common.button
import kotlinx.android.synthetic.main.activity_common.textView

class PublishSubjectExampleActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_common)

    button.setOnClickListener({ _ -> doSomeWork() })
  }

  /* PublishSubject emits to an observer only those items that are emitted
   * by the source Observable, subsequent to the time of the subscription.
   */
  private fun doSomeWork() {

    val source = PublishSubject.create<Int>()

    source.subscribe(getFirstObserver()) // it will get 1, 2, 3, 4 and onComplete

    source.onNext(1)
    source.onNext(2)
    source.onNext(3)

    /*
     * it will emit 4 and onComplete for second observer also.
     */
    source.subscribe(getSecondObserver())

    source.onNext(4)
    source.onComplete()

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
 * It emits all the subsequent items of the source Observable at the time of subscription.
 * Here, if a student entered late into the classroom, he just wants to listen from that point of time
 * when he entered the classroom. So, Publish will be the best for this use-case.
 */
