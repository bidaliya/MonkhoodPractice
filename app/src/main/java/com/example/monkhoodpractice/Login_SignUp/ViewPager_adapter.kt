package com.example.monkhoodpractice.Login_SignUp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPager_adapter(fragmentManager: FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fragmentManager,lifecycle) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> {
                Login_Fragment()
            }
            1 -> {
                SignUp_Fragment()
            }
            else -> {
                Fragment()
            }
        }

    }
}