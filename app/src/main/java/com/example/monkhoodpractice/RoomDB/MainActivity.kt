package com.example.monkhoodpractice.RoomDB

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.monkhoodpractice.Login_SignUp.Login_SignUP
import com.example.monkhoodpractice.R

class MainActivity : AppCompatActivity() {
    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as WordsApplication).repository)
    }
    private lateinit var word: Word
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter(this@MainActivity)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        Log.d("enteredIntoMainActivity", "Yes")

        if(intent.getIntExtra("code",0)!=2){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val id = Html.fromHtml(
                    "<b>" + " <font color = '#FC3397'> ID = </font>" + "${
                        intent.getIntExtra(
                            "IIID",
                            0
                        )
                    }" + "</b>", Html.FROM_HTML_MODE_LEGACY
                ).toString()

                val name = Html.fromHtml(
                    "<b>" + "<font color = '#F08080'> Name = </font>" + intent.getStringExtra("signedup_name")
                        .toString() + "</b>", Html.FROM_HTML_MODE_LEGACY
                ).toString()

                val pass = Html.fromHtml(
                    "<b>" + "<font color = '#F08080'> Pass = </font>" + intent.getStringExtra("signedup_pass")
                        .toString() + "</b>", Html.FROM_HTML_MODE_LEGACY
                ).toString()

                word = Word(0, id, name, pass)
            }
            wordViewModel.insert(word)
        }

        wordViewModel.allWords.observe(this) { words ->
            // Update the cached copy of the words in the adapter.
            words.let { adapter.updateList(it) }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@MainActivity, Login_SignUP::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }
}