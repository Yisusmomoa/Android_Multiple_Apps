package com.Brandon.androidmaster.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.Brandon.androidmaster.R

class FirstAppActivity : AppCompatActivity() {
    //onCreate se llama cada vez que lanzamos la pantalla, cada vez que se crea o ejectua la aplicación
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //para esta pantalla o activity le enganchas/relacionas este diseño
        setContentView(R.layout.activity_first_app)

        val btnStart=findViewById<AppCompatButton>(R.id.btnStart)
        val etName=findViewById<AppCompatEditText>(R.id.etName)

        btnStart.setOnClickListener {
            val name=etName.text.toString()
            if(name.isNotEmpty()) {
                val intent=Intent(this, ResultActivity::class.java)
                intent.putExtra("name", name)
                startActivity(intent)
            }

        }
    }
}