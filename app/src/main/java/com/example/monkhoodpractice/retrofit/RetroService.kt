package com.example.monkhoodpractice.retrofit

import com.example.monkhoodpractice.ApiFetching_UserDisplay.Users
import com.example.monkhoodpractice.ApiFetching_UserDisplay.UsersList
import retrofit2.Call
import retrofit2.http.*

// https://www.youtube.com/watch?v=TJpk7ezvtGo&ab_channel=LearningWorldz

interface RetroService {

    //https://gorest.co.in/public/v2/users

    @GET("users")
    @Headers("Authorization:Bearer b830bc3148d27f8806d2d13724d60f83417bb6ef589688732f8478adce2d0712",
        "Accept:application/json", "Content-Type:application/json")
    fun getUsersList(): Call<UsersList>

    //https://gorest.co.in/public/v2/users/?name=a
    // we have to send the email as a search query.
    @GET("users")
    @Headers("Authorization:Bearer b830bc3148d27f8806d2d13724d60f83417bb6ef589688732f8478adce2d0712",
        "Accept:application/json", "Content-Type:application/json")
    fun searchUsers(@Query("email") searchText: String): Call<UsersList> // searchText can be "email"


    //https://gorest.co.in/public/v2/users/?email=khatri_tarun@kuhic-breitenberg.com
    @GET("users/{user_id}")
    @Headers("Authorization:Bearer b830bc3148d27f8806d2d13724d60f83417bb6ef589688732f8478adce2d0712",
        "Accept:application/json","Content-Type:application/json")
    fun getUser(@Path("user_id") user_id: String): Call<Users?>


    @POST("users")
    @Headers("Authorization:Bearer b830bc3148d27f8806d2d13724d60f83417bb6ef589688732f8478adce2d0712",
        "Accept:application/json", "Content-Type:application/json")
    // we have to pass a user in the body of the API to create a user along with access token
    fun createUser(@Body params: Users): Call<Users?>

    @PATCH("users/{id}")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer b830bc3148d27f8806d2d13724d60f83417bb6ef589688732f8478adce2d0712")
    // we can only update the user through its id and send the updated parameters in the body
    fun updateUser(@Path("id") id: String, @Body params: Users): Call<Users?>

    @DELETE("users/{id}")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer b830bc3148d27f8806d2d13724d60f83417bb6ef589688732f8478adce2d0712")
    fun deleteUser(@Path("id") id: String):Call<Any?>

}