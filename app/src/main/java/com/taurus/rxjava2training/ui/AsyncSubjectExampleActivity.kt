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
import io.reactivex.subjects.AsyncSubject

class AsyncSubjectExampleActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_common)

    button.setOnClickListener({ _ -> doSomeWork() })
  }

  /* An AsyncSubject emits the last value (and only the last value) emitted by the source
   * Observable, and only after that source Observable completes. (If the source Observable
   * does not emit any values, the AsyncSubject also completes without emitting any values.)
   */
  private fun doSomeWork() {

    val source = AsyncSubject.create<Int>()

    source.subscribe(getFirstObserver()) // it will emit only 4 and onComplete

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
 * It only emits the last value of the source Observable(and only the last value) only after that source Observable completes.
 * Here, if a student entered at any point of time into the classroom, and he wants to listen
 * only about the last thing(and only the last thing) being taught, after class is over. So, here we will use Async.
 */
