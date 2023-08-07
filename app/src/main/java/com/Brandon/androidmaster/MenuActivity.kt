package com.Brandon.androidmaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.Brandon.androidmaster.firstapp.FirstAppActivity
import com.Brandon.androidmaster.imccalculator.ImcCalculatorActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val btnSaludarApp=findViewById<Button>(R.id.btnSaludarApp)
        val btnIMCApp=findViewById<Button>(R.id.btnIMCApp)

        btnSaludarApp.setOnClickListener { navigateToSaludarApp() }
        btnIMCApp.setOnClickListener { navigateToIMCApp() }
    }

    private fun navigateToIMCApp() {
        val intent=Intent(this, ImcCalculatorActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSaludarApp(){
        val intent=Intent(this, FirstAppActivity::class.java)
        startActivity(intent)
    }
}