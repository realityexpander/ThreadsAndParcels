package com.realityexpander.threadsandparcels

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val user = intent.extras?.getParcelable<User>("USER_EXTRA_KEY")

        val tvResult = findViewById<TextView>(R.id.tvResult)
        tvResult.text = user.toString()

    }

    fun goToMainActivity(view: View) {
        finish()
    }

}