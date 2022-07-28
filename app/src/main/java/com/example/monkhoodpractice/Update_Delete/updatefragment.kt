package com.example.monkhoodpractice.Update_Delete

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.monkhoodpractice.ApiFetching_UserDisplay.Users
import com.example.monkhoodpractice.Login_SignUp.MainActivityViewModel
import com.example.monkhoodpractice.R
import com.example.monkhoodpractice.databinding.FragmentUpdatefragmentBinding

class updatefragment : Fragment() {

    private  var binding: FragmentUpdatefragmentBinding? = null
    private var viewModel  = MainActivityViewModel()

    private  var gender:String? = null
    private  var update_gender:String? = null
    private  var update_name:String? = null
    private  var update_email:String? = null
    private  var update_password:String? = null
    private lateinit var userdetail: Users
    private lateinit var userid:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdatefragmentBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = arguments
        handelGender()
        binding?.updateButton?.setOnClickListener {
            userdetail = CollectUserDetail()

            userid = data!!.get("userID").toString()

            initialiseviewmodel_update(userdetail, userid)
        }
    }
    private fun CollectUserDetail():Users {
        update_name = binding?.updateName?.editText?.text.toString()
        Log.d("update_name", update_name!!)
        update_email = binding?.updateEmail?.editText?.text.toString()
        Log.d("update_email", update_email!!)

        update_gender = gender
        Log.d("update_gender", "$update_gender")
        update_password = "active" // status

        return  Users(1234,update_name,update_email,update_gender, update_password)

    }
    private fun handelGender() {
        val Gender = arrayOf<String?>("Male", "Female", "Other")

        val ad = ArrayAdapter(this@updatefragment.requireContext(),R.layout.gender_itemview,Gender)
        binding?.textField?.setAdapter(ad)

        binding?.updateGender?.editText?.doOnTextChanged { text, start, before, count ->
            //Log.d("Gender", "${binding?.spinner?.editText?.text}")
            gender = binding?.updateGender?.editText?.text.toString()
        }
    }

    private fun initialiseviewmodel_update(userdetail: Users, userid: String) {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getUpdateUserObservable().observe(viewLifecycleOwner, Observer<Users?> {
            if (it == null) {
                Toast.makeText(this@updatefragment.requireContext(), "no user found...", Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(this@updatefragment.requireContext(), "updated name is ${it.name}", Toast.LENGTH_LONG)
                    .show()
            }
        })
        viewModel.updateUser(userdetail, userid)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}