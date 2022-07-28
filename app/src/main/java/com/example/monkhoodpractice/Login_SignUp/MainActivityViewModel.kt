package com.example.monkhoodpractice.Login_SignUp

import android.util.Log
import androidx.core.graphics.luminance
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.monkhoodpractice.ApiFetching_UserDisplay.Users
import com.example.monkhoodpractice.retrofit.RetroInstance
import com.example.monkhoodpractice.retrofit.RetroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivityViewModel : ViewModel() {

    //    private var UserData: MutableLiveData<Users?> = MutableLiveData()
    private var createNewUserLiveData: MutableLiveData<Users?> = MutableLiveData()
    private var loadUserData: MutableLiveData<Users?> = MutableLiveData()
    private var deleteUserLiveData: MutableLiveData<Any?> = MutableLiveData()
    private var updateUserLiveData : MutableLiveData<Users?> = MutableLiveData()

    fun getCreateNewUserObservable(): MutableLiveData<Users?> { // for signup
        return  createNewUserLiveData
    }

    fun getDeleteUserObservable(): MutableLiveData<Any?> { // for delete
        return  deleteUserLiveData
    }

    fun getLoadUserObservable(): MutableLiveData<Users?> { // for validate and login
        return  loadUserData
    }
    fun getUpdateUserObservable(): MutableLiveData<Users?> { // for update
        return  updateUserLiveData
    }

    fun getUser(searchid: String) {
        val retroInstance= RetroInstance.getRetroInstance().create(RetroService::class.java)

        val call = retroInstance.getUser(searchid)
        call.enqueue(object : Callback<Users?>{
            override fun onResponse(call: Call<Users?>, response: Response<Users?>) {
                if(response.isSuccessful) {
                    loadUserData.postValue(response.body())
                } else {
                    loadUserData.postValue(null)
                }
            }

            override fun onFailure(call: Call<Users?>, t: Throwable) {
                //Toast.makeText(this@MainActivityViewModel, "Some Error occurred in OnFailure", Toast.LENGTH_LONG).show()
                Log.d("onFailure_ViewModel", "Response doesn't get $t").luminance
            }

        })

    }

    fun createUser(user: Users) {

        val retroInstance= RetroInstance.getRetroInstance().create(RetroService::class.java)

        val call = retroInstance.createUser(user)
        call.enqueue(object : Callback<Users?>{
            override fun onResponse(call: Call<Users?>, response: Response<Users?>) {
                if(response.isSuccessful) {
                    createNewUserLiveData.postValue(response.body())
                    Log.d("UserCreatedSuccesfully", "${response.body()?.id}").luminance
                } else {
                    createNewUserLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<Users?>, t: Throwable) {
                //Toast.makeText(this@MainActivityViewModel, "Some Error occurred in OnFailure", Toast.LENGTH_LONG).show()
                Log.d("FailViewModelCreateUser", "Response doesn't get $t").luminance
            }

        })
    }
    fun updateUser(user: Users, id:String){
        val retroInstance= RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.updateUser(id,user)
        call.enqueue(object : Callback<Users?>{
            override fun onResponse(call: Call<Users?>, response: Response<Users?>) {
                if(response.isSuccessful) {
                    updateUserLiveData.postValue(response.body())
                    Log.d("UserUpdatedSuccesfully", "${response.body()?.id}").luminance
                    Log.d("UserUpdatedSuccesfully", "${response.body()?.name}").luminance
                } else {
                    updateUserLiveData.postValue(null)
                }
            }
            override fun onFailure(call: Call<Users?>, t: Throwable) {
                Log.d("FailViewModelUpdateUser", "Response doesn't get $t").luminance
            }
        })
    }

    fun deleteUser(user_id: String) {
        val retroInstance= RetroInstance.getRetroInstance().create(RetroService::class.java)

        val call = retroInstance.deleteUser(user_id)
        call.enqueue(object : Callback<Any?>{
            override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                if(response.isSuccessful) {
                    deleteUserLiveData.postValue(response)
                    Log.d("UserDeletedSuccesfully", "${response.body()}").luminance
                } else {
                    deleteUserLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<Any?>, t: Throwable) {
                //Toast.makeText(this@MainActivityViewModel, "Some Error occurred in OnFailure", Toast.LENGTH_LONG).show()
                Log.d("FailViewModelDeleteUser", "Response doesn't get $t").luminance
            }

        })
    }


}