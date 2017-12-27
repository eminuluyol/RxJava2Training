package com.taurus.rxjava2training.model

data class ApiUser(var id: Long = 0, var firstname: String = "", var lastname: String = "") {

  override fun toString(): String {
    return "ApiUser{" +
        "id=" + id +
        ", firstname='" + firstname + '\'' +
        ", lastname='" + lastname + '\'' +
        '}'
  }
}
