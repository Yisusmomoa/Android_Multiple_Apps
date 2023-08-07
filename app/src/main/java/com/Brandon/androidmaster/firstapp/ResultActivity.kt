package com.Brandon.androidmaster.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.Brandon.androidmaster.R

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val tvResult=findViewById<TextView>(R.id.tvResult)
        val name= intent.extras?.get("name")
        tvResult.text="Hola $name"
    }
}