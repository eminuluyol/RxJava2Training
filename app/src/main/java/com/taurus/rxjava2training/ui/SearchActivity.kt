package com.taurus.rxjava2training.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.taurus.rxjava2training.R.layout
import com.taurus.rxjava2training.utils.RxSearchObservable
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search.searchView
import kotlinx.android.synthetic.main.activity_search.textViewResult
import java.util.concurrent.TimeUnit

class SearchActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_search)
    setUpSearchObservable()
  }

  private fun setUpSearchObservable() {
    RxSearchObservable.fromView(searchView)
        .debounce(300, TimeUnit.MILLISECONDS)
        .filter(Predicate<String> { text ->
          if (text.isEmpty()) {
            textViewResult.text = ""
            return@Predicate false
          }
          return@Predicate true

        })
        .distinctUntilChanged()
        .switchMap(Function<String, ObservableSource<String>> { query ->
          return@Function dataFromNetwork(query)
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ result ->
          textViewResult.text = result
        })
  }

  /**
   * Simulation of network data
   */
  private fun dataFromNetwork(query: String): Observable<String> {
    return Observable.just(true)
        .delay(1, TimeUnit.SECONDS)
        .map { _ -> return@map query }
  }

}


/**
 * Debounce: Here, the debounce operator is used with a time constant.
 * The debounce operator handles the case when the user types “a”, “ab”, “abc”, in a very short time.
 * So there will be too much network calls. But the user is finally interested in the result of the search
 * “abc”. So, you must discard the results of “a” and “ab”. Ideally, there should be no network calls
 * for “a” and “ab” as the user typed those in very short time. So, the debounce operator comes to the rescue.
 * The debounce will wait for the provided time for doing anything, if any other search query comes in
 * between that time, it will ignore the previous item and start waiting for that time again with the new search query.
 * I f nothing new comes in that given constant time, it will proceed with that search query for further processing.
 * So, debounce only emit an item from an Observable if a particular timespan has passed without it emitting an another item.
 */

/**
 * Filter: The filter operator is used to filter the unwanted string like empty string in this case
 * to avoid the unnecessary network call.
 */

/**
 * DistinctUntilChanged: The distinctUntilChanged operator is used to avoid the duplicate network calls.
 * Let say the last on-going search query was “abc” and the user deleted “c” and again typed “c”.
 * So again it’s “abc”. So if the network call is already going on with the search query “abc”,
 * it will not make the duplicate call again with the search query “abc”.
 * So, distinctUntilChanged suppress duplicate consecutive items emitted by the source Observable.
 */

/**
 * SwitchMap: Here, the switchMap operator is used to avoid the network call results which are not
 * needed more for displaying to the user. Let say the last search query was “ab” and
 * there is an ongoing network call for “ab” and the user typed “abc”. Then you are no more
 * interested in the result of “ab”. You are only interested in the result of “abc”. So, the switchMap
 * comes to the rescue. It only provides the result for the last search query(most recent) and ignores the rest.
 */
