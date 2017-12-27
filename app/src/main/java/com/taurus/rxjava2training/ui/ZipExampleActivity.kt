package com.taurus.rxjava2training.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.taurus.rxjava2training.R.layout
import com.taurus.rxjava2training.extensions.TAG
import com.taurus.rxjava2training.model.User
import com.taurus.rxjava2training.utils.Utils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_common.button
import kotlinx.android.synthetic.main.activity_common.textView

class ZipExampleActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_common)

    button.setOnClickListener({ _ -> doSomeWork() })
  }

  /*
    * Here we are getting two user list
    * One, the list of cricket fans
    * Another one, the list of football fans
    * Then we are finding the list of users who loves both
    */
  private fun doSomeWork() {
    Observable.zip(getCricketFansObservable(), getFootballFansObservable(),
        BiFunction<List<User>, List<User>, List<User>> { cricketFans, footballFans ->
          Utils.filterUserWhoLovesBoth(cricketFans, footballFans)
        })
        // Run on a background thread
        .subscribeOn(Schedulers.io())
        // Be notified on the main thread
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(getObserver())
  }

  private fun getCricketFansObservable(): Observable<List<User>> {
    return Observable.create({ e ->
      if (!e.isDisposed) {
        e.onNext(Utils.getUserListWhoLovesCricket())
        e.onComplete()
      }
    })
  }

  private fun getFootballFansObservable(): Observable<List<User>> {
    return Observable.create({ e ->
      if (!e.isDisposed) {
        e.onNext(Utils.getUserListWhoLovesFootball())
        e.onComplete()
      }
    })
  }

  private fun getObserver(): Observer<List<User>> {
    return object : Observer<List<User>> {

      override fun onSubscribe(d: Disposable) {
        Log.d(TAG(), " onSubscribe : " + d.isDisposed)
      }

      override fun onNext(userList: List<User>) {
        textView.append(" onNext")
        textView.append(LINE_SEPARATOR)
        for (user in userList) {
          textView.append(" firstname : " + user.firstname)
          textView.append(LINE_SEPARATOR)
        }
        Log.d(TAG(), " onNext : " + userList.size)
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
 * Zip returns an Observable that emits the results of a specified combiner function applied to
 * combinations of two items emitted, in sequence, by two other ObservableSources. It waits for the both items to arrive
 * then merges them.
 */
