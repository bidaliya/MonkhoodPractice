package com.example.monkhoodpractice.Login_SignUp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.monkhoodpractice.R
import com.example.monkhoodpractice.databinding.FragmentLoginBinding


class Login_Fragment : Fragment() {
    private  var binding: FragmentLoginBinding? = null
    private var viewModel  = MainActivityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // using Retrofit library
        binding?.loginButton1?.setOnClickListener {
            getuser()
        }

    }

    private fun getuser() {
        if(!TextUtils.isEmpty(binding?.emailLogin?.editText?.text.toString())){
            initialiseViewModel()
        }
        else{
            Toast.makeText(this@Login_Fragment.requireContext(), "Enter Email", Toast.LENGTH_LONG).show()
        }
    }

    private fun initialiseViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getLoadUserObservable().observe(viewLifecycleOwner) {
            if (it == null) {
                Toast.makeText(this@Login_Fragment.requireContext(), "no user found...", Toast.LENGTH_LONG)
                    .show()
            } else {
//                startActivity(Intent(this@Login_Fragment.requireContext(), MainActivity::class.java).apply {
//                    putExtra("login_name", it.name)
//                    putExtra("code", 0)
//                })
                Log.d("FromLogin", "${it.name}")
            }
        }
        viewModel.getUser(binding?.emailLogin?.editText?.text.toString())

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}