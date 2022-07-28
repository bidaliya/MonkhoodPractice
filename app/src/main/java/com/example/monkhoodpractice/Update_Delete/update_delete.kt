package com.example.monkhoodpractice.Update_Delete

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.TextUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.monkhoodpractice.Login_SignUp.Login_SignUP
import com.example.monkhoodpractice.Login_SignUp.MainActivityViewModel
import com.example.monkhoodpractice.R
import com.example.monkhoodpractice.databinding.ActivityUpdateDeleteBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class update_delete : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateDeleteBinding
    private lateinit var entered_userid: String
    private var viewModel  = MainActivityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateDeleteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val code = intent.getIntExtra("code",0)

        if(code == 0){
            binding.updateDeleteButton.setText(R.string.update)
            // check if the user id is correct or not when click on button
        }
        else {
            binding.updateDeleteButton.setText(R.string.delete)
            //replacefragment(deletefragment(), "Delete")
        }

        binding.updateDeleteButton.setOnClickListener {
            entered_userid = binding.enterUserId.editText?.text.toString()

            if(TextUtils.isEmpty(entered_userid) || entered_userid.length<4 || entered_userid.length>4){
                binding.enterUserId.editText?.error = "required 4 digit ID"
                binding.enterUserId.editText?.requestFocus()
            }
            else{
                ValidateUser(code,entered_userid )
                binding.enterUserId.isEnabled=false
            }
        }

    }

    private fun ValidateUser(code: Int, userid: String) {
        viewModel = ViewModelProvider(this@update_delete).get(MainActivityViewModel::class.java)
        viewModel.getLoadUserObservable().observe(this@update_delete) {
            if (it == null) {
                Toast.makeText(this@update_delete, "no user found...", Toast.LENGTH_LONG)
                    .show()
            }
            else {
                if(code==0){
                    replacefragment(updatefragment(), "Update", userid)
                }
                else{
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        MaterialAlertDialogBuilder(this@update_delete)
                            .setTitle(resources.getString(R.string.title))
                            //.setMessage("Are you sure you want to delete ${it.name}")
                            .setMessage(
                                Html.fromHtml("Are you sure you want to delete "+"<b>"
                                    +"<font color = '#FC3397'> ${it.name}</font>"+"</b>"
                                , Html.FROM_HTML_MODE_LEGACY))
                            .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                                binding.enterUserId.isEnabled=true
                                Snackbar.make(binding.root, "Deletion cancel", Snackbar.LENGTH_LONG).show()
                            }
                            .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                                initialiseviewmodel_delete(userid)
                            }
                            .show()
                    }
                }
            }
        }
        viewModel.getUser(userid)

    }

    private fun initialiseviewmodel_delete(userid: String) {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getDeleteUserObservable().observe(this@update_delete, Observer<Any?> {
            if (it == null) {
                Toast.makeText(this@update_delete, "no user found...", Toast.LENGTH_LONG)
                    .show()
                binding.enterUserId.isEnabled = true
            } else {
                replacefragment(deletefragment(), "Delete", userid)
                //Toast.makeText(this@update_delete, "User Deleted successfully", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.deleteUser(userid)
    }
    private fun replacefragment(fragment: Fragment, title: String, userid: String){

        val fragmentManager = supportFragmentManager
        val bundle = Bundle()
        bundle.putString("userID", userid)
        fragment.arguments = bundle
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.update_delete_FrameLayout, fragment).commit()
        setTitle(title)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@update_delete, Login_SignUP::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }
}