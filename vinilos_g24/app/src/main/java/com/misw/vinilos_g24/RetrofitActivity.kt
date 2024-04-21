package com.misw.vinilos_g24

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import com.misw.vinilos_g24.brokers.RetrofitBroker

class RetrofitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        val getButton: Button = findViewById(R.id.fetch_button_2)
        val getResultTextView : TextView = findViewById(R.id.get_result_text_2)
        Log.d("error", getResultTextView.toString())
        getButton.setOnClickListener {
            RetrofitBroker.getRequest(onResponse = {
                getResultTextView.text = it
            }, onFailure = {
                getResultTextView.text = it
            })
            print(getResultTextView)
        }

        /*val postButton: Button = findViewById(R.id.post_button_2)
        val postResultTextView : TextView = findViewById(R.id.post_result_text_2)
        postButton.setOnClickListener {
            val mailTxt : TextInputEditText = findViewById(R.id.txt_post_mail_2)
            val nameTxt : TextInputEditText = findViewById(R.id.txt_post_name_2)
            val phoneTxt : TextInputEditText = findViewById(R.id.txt_post_phone_2)
            val postParams = mapOf<String, String>(
                "name" to nameTxt.text.toString(),
                "telephone" to phoneTxt.text.toString(),
                "email" to mailTxt.text.toString()
            )
            RetrofitBroker.postRequest(postParams,
                onResponse = {
                    postResultTextView.text = it
                }, onFailure = {
                    postResultTextView.text = it
                })
        }*/
    }

    fun Retrofit(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        supportActionBar!!.title = "Vinilos"
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.fetch_button_2 -> {
                // Create an intent with a destination of the other Activity
                val intent = Intent(this, RetrofitActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}