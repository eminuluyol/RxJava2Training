package com.taurus.rxjava2training.extensions

import kotlin.reflect.KClass


fun<T: Any> T.getClass(): KClass<T> {
  return javaClass.kotlin
}

fun<T: Any> T.TAG(): String {
  return javaClass.kotlin.toString()
}