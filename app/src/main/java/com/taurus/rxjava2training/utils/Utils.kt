package com.taurus.rxjava2training.utils

import com.taurus.rxjava2training.model.ApiUser
import com.taurus.rxjava2training.model.User
import java.util.ArrayList

class Utils {

  companion object {

    fun getUserList(): List<User> {

      val userList = ArrayList<User>()

      val userOne = User()
      userOne.firstname = "Emin"
      userOne.lastname = "Uluyol"
      userList.add(userOne)

      val userTwo = User()
      userTwo.firstname = "Anil"
      userTwo.lastname = "Tepe"
      userList.add(userTwo)

      val userThree = User()
      userThree.firstname = "Semih"
      userThree.lastname = "Bozdemir"
      userList.add(userThree)

      return userList
    }

    fun getApiUserList(): List<ApiUser> {

      val apiUserList = ArrayList<ApiUser>()

      val apiUserOne = ApiUser()
      apiUserOne.firstname = "Emin"
      apiUserOne.lastname = "Uluyol"
      apiUserList.add(apiUserOne)

      val apiUserTwo = ApiUser()
      apiUserTwo.firstname = "Anil"
      apiUserTwo.lastname = "Tepe"
      apiUserList.add(apiUserTwo)

      val apiUserThree = ApiUser()
      apiUserThree.firstname = "Semih"
      apiUserThree.lastname = "Bozdemir"
      apiUserList.add(apiUserThree)

      return apiUserList
    }


    fun getUserListWhoLovesCricket(): List<User> {

      val userList = ArrayList<User>()

      val userOne = User()
      userOne.id = 1
      userOne.firstname = "Emin"
      userOne.lastname = "Uluyol"
      userList.add(userOne)

      val userTwo = User()
      userTwo.id = 2
      userTwo.firstname = "Anil"
      userTwo.lastname = "Tepe"
      userList.add(userTwo)

      return userList
    }

    fun getUserListWhoLovesFootball(): List<User> {

      val userList = ArrayList<User>()

      val userOne = User()
      userOne.id = 1
      userOne.firstname = "Emin"
      userOne.lastname = "Uluyol"
      userList.add(userOne)

      val userTwo = User()
      userTwo.id = 3
      userTwo.firstname = "Anil"
      userTwo.lastname = "Tepe"
      userList.add(userTwo)

      return userList
    }

    fun convertApiUserListToUserList(apiUserList: List<ApiUser>): List<User> {

      val userList = ArrayList<User>()

      for (apiUser in apiUserList) {
        val user = User()
        user.firstname = apiUser.firstname
        user.lastname = apiUser.lastname
        userList.add(user)
      }

      return userList
    }

    fun filterUserWhoLovesBoth(cricketFans: List<User>, footballFans: List<User>): List<User> {
      val userWhoLovesBoth = ArrayList<User>()
      for (cricketFan in cricketFans) {
        footballFans
            .filter { cricketFan.id == it.id }
            .forEach { userWhoLovesBoth.add(cricketFan) }
      }
      return userWhoLovesBoth
    }

  }

}
