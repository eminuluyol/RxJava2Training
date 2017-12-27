package com.taurus.rxjava2training.model

import io.reactivex.Observable

data class Car(var brand: String = "") {

  fun brandDeferObservable(): Observable<String> {
    return Observable.defer { Observable.just(brand) }
  }
}