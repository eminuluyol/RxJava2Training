package com.taurus.rxjava2training.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.taurus.rxjava2training.R.layout
import com.taurus.rxjava2training.extensions.TAG
import com.taurus.rxjava2training.model.ApiUser
import com.taurus.rxjava2training.model.User
import com.taurus.rxjava2training.utils.Utils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_common.button
import kotlinx.android.synthetic.main.activity_common.textView

class MapExampleActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_common)

    button.setOnClickListener({ _ -> doSomeWork() })
  }

  /*
    * Here we are getting ApiUser Object from api server
    * then we are converting it into User Object because
    * may be our database support User Not ApiUser Object
    * Here we are using Map Operator to do that
    */
  private fun doSomeWork() {
    createObservable()
        // Run on a background thread
        .subscribeOn(Schedulers.io())
        // Be notified on the main thread
        .observeOn(AndroidSchedulers.mainThread())
        .map { apiUsers -> Utils.convertApiUserListToUserList(apiUsers) }
        .subscribe(getObserver())
  }


  private fun getObservable(): Observable<List<ApiUser>> {
    return Observable.create({ e ->
      if (!e.isDisposed) {
        e.onNext(Utils.getApiUserList())
        e.onComplete()
      }
    })
  }

  private fun createObservable() : Observable<List<ApiUser>> {
    return Observable.defer{
      Observable.just(Utils.getApiUserList())
    }
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
 * Here we used create method to create our Observable. The difference between creating an observable
 * with create and just method is that with create method we can more customize our observable.
 *
 * Map is basically returns an Observable that applies a specified function to each item emitted by
 * the source ObservableSource and emits the results of these function applications.
 */
