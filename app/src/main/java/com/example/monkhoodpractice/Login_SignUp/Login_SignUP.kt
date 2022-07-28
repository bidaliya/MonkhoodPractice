package com.example.monkhoodpractice.Login_SignUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.viewpager2.widget.ViewPager2
import com.example.monkhoodpractice.R
import com.example.monkhoodpractice.RoomDB.MainActivity
import com.example.monkhoodpractice.Update_Delete.update_delete
import com.example.monkhoodpractice.databinding.ActivityLoginSignUpBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class Login_SignUP : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var fb: ImageView
    private lateinit var google: FloatingActionButton
    private lateinit var twitter: FloatingActionButton
    private  var binding: ActivityLoginSignUpBinding? = null
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginSignUpBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view) // bind the variables to their views
        tabLayout = binding!!.tabLayout
        viewPager2 = binding!!.viewPager2
        fb = binding!!.fabFb
        google = binding!!.fabGoogle
        twitter = binding!!.fabTwitter


        val adapter = ViewPager_adapter(supportFragmentManager, lifecycle)
        binding!!.viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Login"
                }
                1 -> {
                    tab.text = "SignUp"
                }
            }

        }.attach()

        // navigation drawer

        val drawerLayout = binding!!.drawerLayout
        val nav_view = binding!!.navigationView

        toggle =
            ActionBarDrawerToggle(this@Login_SignUP, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nav_view.setNavigationItemSelectedListener {
            it.isChecked = true


            when (it.itemId) {

                R.id.update_user -> {
                    startActivity(Intent(this@Login_SignUP, update_delete::class.java).apply {
                        putExtra("code", 0) // 0 for update
                    })
                    drawerLayout.closeDrawers()
                }

                R.id.delete_user -> {
                    startActivity(Intent(this@Login_SignUP, update_delete::class.java).apply {
                        putExtra("code", 1) // 1 for delete
                    })
                    drawerLayout.closeDrawers()
                }

                R.id.database ->{
                    startActivity(Intent(this@Login_SignUP, MainActivity::class.java).apply {
                        putExtra("code", 2) // 2 for show DB
                    })
                    drawerLayout.closeDrawers()
                }
            }
            true
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        binding = null
        finish()
    }
}