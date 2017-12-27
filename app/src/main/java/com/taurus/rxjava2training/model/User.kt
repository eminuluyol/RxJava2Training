package com.taurus.rxjava2training.model

data class User(var id: Long = 0, var firstname: String = "", var lastname: String = "", var isFollowing: Boolean = false) {

  override fun toString(): String {
    return "User{" +
        "id=" + id +
        ", firstname='" + firstname + '\'' +
        ", lastname='" + lastname + '\'' +
        ", isFollowing=" + isFollowing +
        '}'
  }
}
