package com.example.monkhoodpractice.Update_Delete

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.monkhoodpractice.Login_SignUp.MainActivityViewModel
import com.example.monkhoodpractice.databinding.FragmentDeletefragmentBinding


class deletefragment : Fragment() {

    private  var binding: FragmentDeletefragmentBinding? = null
    private var viewModel  = MainActivityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDeletefragmentBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}