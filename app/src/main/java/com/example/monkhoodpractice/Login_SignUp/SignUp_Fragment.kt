package com.example.monkhoodpractice.Login_SignUp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.monkhoodpractice.ApiFetching_UserDisplay.Users
import com.example.monkhoodpractice.R
import com.example.monkhoodpractice.RoomDB.MainActivity
import com.example.monkhoodpractice.databinding.FragmentSignUpBinding


class SignUp_Fragment : Fragment() {

    private  var binding: FragmentSignUpBinding? = null

    private  var gender:String? = null
    private  var signup_name:String? = null
    private  var signup_email:String? = null
    private  var sigup_gender:String? = null
    private  var signup_password:String? = null

    private var viewModel  = MainActivityViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handelGender()
        //UserViewModel= ViewModelProvider(this@SignUp_Fragment.requireActivity()).get(userViewModel::class.java)

        binding?.createAccount?.setOnClickListener {
            signup_name = binding?.nameSignup?.editText?.text.toString()
            Log.d("signup_name", signup_name!!)
            signup_email = binding?.emailSignup?.editText?.text.toString()
            Log.d("signup_email", signup_email!!)

            sigup_gender = gender
            Log.d("signup_gender", "$sigup_gender")
            signup_password = "active" // status
            val signedup_user = Users(1,signup_name,signup_email,sigup_gender, signup_password)
            initialiseViewModel_signup(signedup_user)
        }

    }
    private fun handelGender() {
        val Gender = arrayOf<String?>("Male", "Female", "Other")

        val ad = ArrayAdapter(this@SignUp_Fragment.requireContext(),R.layout.gender_itemview,Gender)
        binding?.textField?.setAdapter(ad)

        binding?.spinner?.editText?.doOnTextChanged { text, start, before, count ->
            //Log.d("Gender", "${binding?.spinner?.editText?.text}")
            gender = binding?.spinner?.editText?.text.toString()
        }
    }
    private fun initialiseViewModel_signup(signedup_user: Users) {

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getCreateNewUserObservable().observe(viewLifecycleOwner) {
            if (it == null) {
                Toast.makeText(this@SignUp_Fragment.requireContext(), "no user found...", Toast.LENGTH_LONG)
                    .show()
            } else {
//                startActivity(Intent(this@SignUp_Fragment.requireContext(), UserShowActivity::class.java).apply {
//                    putExtra("signedup_id", it.id)
//                    putExtra("signedup_name",it.name )
//                    putExtra("signedup_pass", binding?.passwordSignup?.editText?.text.toString())
//                })
                Log.d("sign_upID", "${it.id}")
                val intent = Intent(this@SignUp_Fragment.requireContext(), MainActivity::class.java)
                intent.apply {
                    putExtra("IIID", it.id)
                    putExtra("signedup_name",it.name )
                    putExtra("signedup_pass", binding?.passwordSignup?.editText?.text.toString())
                }
                startActivity(intent)

//                UserViewModel.insertUser(UserDB(it.id?:0, it.name, binding?.passwordSignup?.editText?.text.toString()))
//                startActivity(Intent(this@SignUp_Fragment.requireContext(), UserShow::class.java))
            }
        }
        viewModel.createUser(signedup_user)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}