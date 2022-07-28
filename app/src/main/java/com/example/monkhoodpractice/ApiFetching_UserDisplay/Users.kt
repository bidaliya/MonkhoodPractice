package com.example.monkhoodpractice.ApiFetching_UserDisplay

data class Users(
    val id:Int?,
    val name: String?,
    val email: String?,
    val gender:String?,
    val status:String?
    )


data class UsersList(val data:ArrayList<Users>)




data class UserResponse(val data: Users?)
 // in response of the API call, we will get the user which we have created