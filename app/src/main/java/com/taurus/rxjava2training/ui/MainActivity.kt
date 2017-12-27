package com.taurus.rxjava2training.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.taurus.rxjava2training.R
import com.taurus.rxjava2training.R.layout
import kotlinx.android.synthetic.main.activity_main.asyncSubjectButton
import kotlinx.android.synthetic.main.activity_main.behaviourSubjectButton
import kotlinx.android.synthetic.main.activity_main.bufferButton
import kotlinx.android.synthetic.main.activity_main.completableObserverButton
import kotlinx.android.synthetic.main.activity_main.concatButton
import kotlinx.android.synthetic.main.activity_main.debounceButton
import kotlinx.android.synthetic.main.activity_main.deferButton
import kotlinx.android.synthetic.main.activity_main.delayButton
import kotlinx.android.synthetic.main.activity_main.disposableButton
import kotlinx.android.synthetic.main.activity_main.distinctButton
import kotlinx.android.synthetic.main.activity_main.filterButton
import kotlinx.android.synthetic.main.activity_main.flowableButton
import kotlinx.android.synthetic.main.activity_main.intervalButton
import kotlinx.android.synthetic.main.activity_main.lastOperatorButton
import kotlinx.android.synthetic.main.activity_main.mapButton
import kotlinx.android.synthetic.main.activity_main.mergeButton
import kotlinx.android.synthetic.main.activity_main.publishSubjectButton
import kotlinx.android.synthetic.main.activity_main.reduceButton
import kotlinx.android.synthetic.main.activity_main.replayButton
import kotlinx.android.synthetic.main.activity_main.replaySubjectButton
import kotlinx.android.synthetic.main.activity_main.scanButton
import kotlinx.android.synthetic.main.activity_main.searchButton
import kotlinx.android.synthetic.main.activity_main.singleObserverButton
import kotlinx.android.synthetic.main.activity_main.skipButton
import kotlinx.android.synthetic.main.activity_main.takeButton
import kotlinx.android.synthetic.main.activity_main.throttleFirstButton
import kotlinx.android.synthetic.main.activity_main.zipButton


class MainActivity : AppCompatActivity(), View.OnClickListener {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)

    asyncSubjectButton.setOnClickListener(this)
    behaviourSubjectButton.setOnClickListener(this)
    bufferButton.setOnClickListener(this)
    completableObserverButton.setOnClickListener(this)
    concatButton.setOnClickListener(this)
    deferButton.setOnClickListener(this)
    disposableButton.setOnClickListener(this)
    filterButton.setOnClickListener(this)
    flowableButton.setOnClickListener(this)
    intervalButton.setOnClickListener(this)
    mapButton.setOnClickListener(this)
    mergeButton.setOnClickListener(this)
    publishSubjectButton.setOnClickListener(this)
    reduceButton.setOnClickListener(this)
    replayButton.setOnClickListener(this)
    searchButton.setOnClickListener(this)
    singleObserverButton.setOnClickListener(this)
    skipButton.setOnClickListener(this)
    takeButton.setOnClickListener(this)
    zipButton.setOnClickListener(this)
    replaySubjectButton.setOnClickListener(this)
    debounceButton.setOnClickListener(this)
    delayButton.setOnClickListener(this)
    distinctButton.setOnClickListener(this)
    lastOperatorButton.setOnClickListener(this)
    scanButton.setOnClickListener(this)
    throttleFirstButton.setOnClickListener(this)

  }


  override fun onClick(view: View) {

    when (view.id) {
      R.id.asyncSubjectButton -> startAsyncSubjectActivity()
      R.id.behaviourSubjectButton -> startBehaviourSubjectActivity()
      R.id.bufferButton -> startBufferActivity()
      R.id.completableObserverButton -> startCompletableObserverActivity()
      R.id.concatButton -> startConcatActivity()
      R.id.deferButton -> startDeferActivity()
      R.id.disposableButton -> startDisposableActivity()
      R.id.filterButton -> startFilterActivity()
      R.id.flowableButton -> startFlowableActivity()
      R.id.intervalButton -> startIntervalActivity()
      R.id.mapButton -> startMapActivity()
      R.id.mergeButton -> startMergeActivity()
      R.id.publishSubjectButton -> startPublishSubjectActivity()
      R.id.reduceButton -> startReduceActivity()
      R.id.replayButton -> startReplayActivity()
      R.id.searchButton -> startSearchActivity()
      R.id.singleObserverButton -> startSingleObserverActivity()
      R.id.skipButton -> startSkipActivity()
      R.id.takeButton -> startTakeActivity()
      R.id.zipButton -> startZipActivity()
      R.id.replaySubjectButton -> startReplaySubjectActivity()
      R.id.debounceButton -> startDebounceActivity()
      R.id.delayButton -> startDelayActivity()
      R.id.distinctButton -> startDistinctActivity()
      R.id.lastOperatorButton -> startLastOperatorActivity()
      R.id.scanButton -> startScanActivity()
      R.id.throttleFirstButton -> startThrottleFirstActivity()
    }
  }

  private fun startThrottleFirstActivity() {
    startActivity(Intent(this@MainActivity, ThrottleFirstActivity::class.java))
  }

  private fun startScanActivity() {
    startActivity(Intent(this@MainActivity, ScanActivity::class.java))
  }

  private fun startLastOperatorActivity() {
    startActivity(Intent(this@MainActivity, LastOperatorActivity::class.java))
  }

  private fun startDistinctActivity() {
    startActivity(Intent(this@MainActivity, DistinctActivity::class.java))
  }

  private fun startDelayActivity() {
    startActivity(Intent(this@MainActivity, DelayActivity::class.java))
  }

  private fun startDebounceActivity() {
    startActivity(Intent(this@MainActivity, DebounceActivity::class.java))
  }

  private fun startReplaySubjectActivity() {
    startActivity(Intent(this@MainActivity, ReplaySubjectExampleActivity::class.java))
  }

  private fun startZipActivity() {
    startActivity(Intent(this@MainActivity, ZipExampleActivity::class.java))
  }

  private fun startTakeActivity() {
    startActivity(Intent(this@MainActivity, TakeExampleActivity::class.java))

  }

  private fun startSkipActivity() {
    startActivity(Intent(this@MainActivity, SkipExampleActivity::class.java))

  }

  private fun startSingleObserverActivity() {
    startActivity(Intent(this@MainActivity, SingleObserverExampleActivity::class.java))

  }

  private fun startSearchActivity() {
    startActivity(Intent(this@MainActivity, SearchActivity::class.java))

  }

  private fun startReplayActivity() {
    startActivity(Intent(this@MainActivity, ReplayExampleActivity::class.java))

  }

  private fun startReduceActivity() {
    startActivity(Intent(this@MainActivity, ReduceExampleActivity::class.java))

  }

  private fun startPublishSubjectActivity() {
    startActivity(Intent(this@MainActivity, PublishSubjectExampleActivity::class.java))

  }

  private fun startMergeActivity() {
    startActivity(Intent(this@MainActivity, MergeExampleActivity::class.java))

  }

  private fun startMapActivity() {
    startActivity(Intent(this@MainActivity, MapExampleActivity::class.java))

  }

  private fun startIntervalActivity() {
    startActivity(Intent(this@MainActivity, IntervalExampleActivity::class.java))

  }

  private fun startFlowableActivity() {
    startActivity(Intent(this@MainActivity, FlowableExampleActivity::class.java))

  }

  private fun startFilterActivity() {
    startActivity(Intent(this@MainActivity, FilterExampleActivity::class.java))

  }

  private fun startDisposableActivity() {
    startActivity(Intent(this@MainActivity, DisposableExampleActivity::class.java))

  }

  private fun startDeferActivity() {
    startActivity(Intent(this@MainActivity, DeferExampleActivity::class.java))

  }

  private fun startConcatActivity() {
    startActivity(Intent(this@MainActivity, ConcatExampleActivity::class.java))

  }

  private fun startCompletableObserverActivity() {
    startActivity(Intent(this@MainActivity, CompletableObserverExampleActivity::class.java))

  }

  private fun startBufferActivity() {
    startActivity(Intent(this@MainActivity, BufferExampleActivity::class.java))

  }

  private fun startBehaviourSubjectActivity() {
    startActivity(Intent(this@MainActivity, BehaviorSubjectExampleActivity::class.java))

  }

  private fun startAsyncSubjectActivity() {
    startActivity(Intent(this@MainActivity, AsyncSubjectExampleActivity::class.java))

  }
}
