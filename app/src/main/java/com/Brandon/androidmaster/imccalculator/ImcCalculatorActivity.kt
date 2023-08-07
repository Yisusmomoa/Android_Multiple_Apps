package com.Brandon.androidmaster.imccalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.Brandon.androidmaster.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class ImcCalculatorActivity : AppCompatActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentWeight: Int = 60
    private var currentAge:Int=26
    private var currentHeight:Int=120

    //para poder acceder a las cardView desde toda la class
    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView

    private lateinit var tvHeight: TextView
    private lateinit var rgHeight: RangeSlider

    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var tvWeight: TextView

    private lateinit var btnSubtractAge:FloatingActionButton
    private lateinit var btnPlusAge:FloatingActionButton
    private lateinit var tvAge:TextView

    private lateinit var btnCalculate:Button

    //lo que este dentro de aqui todo mundo puede acceder a el, es como los métodos static de java o js
    companion object{
        const val IMC_KEY="IMC"
    }

    //lateinit: no te inices ahora, te inicias cuando yo tediga
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)
        initComponents()
        initListeners()
        initUI()
    }

    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rgHeight = findViewById(R.id.rgHeight)
        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        tvWeight = findViewById(R.id.tvWeight)
        btnSubtractAge=findViewById(R.id.btnSubtractAge)
        btnPlusAge=findViewById(R.id.btnPlusAge)
        tvAge=findViewById(R.id.tvAge)
        btnCalculate=findViewById(R.id.btnCalculate)
    }

    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }

        //cada vez que se mueva el slider se llama este método
        //regresa: el slider el componente, el valor y el fromUser
        rgHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()
            tvHeight.text = "$currentHeight cm"
        }

        btnPlusWeight.setOnClickListener {
            currentWeight+=1
            setWeight()
        }
        btnSubtractWeight.setOnClickListener {
            currentWeight-=1
            setWeight()
        }
        btnPlusAge.setOnClickListener {
            currentAge+=1
            setAge()
        }
        btnSubtractAge.setOnClickListener {
            currentAge-=1
            setAge()
        }

        btnCalculate.setOnClickListener {
            val result=calculateIMC()
            navigateToResult(result)
        }
    }

    private fun calculateIMC(): Double {
        val df=DecimalFormat("#.##")
        val imc=currentWeight/(currentHeight.toDouble()/100*currentHeight.toDouble()/100)
       return df.format(imc).toDouble()
    }

    private fun navigateToResult(result:Double){
        val intent=Intent(this, ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun setAge() {
        tvAge.text=currentAge.toString()
    }

    private fun setWeight() {
        tvWeight.text=currentWeight.toString()
    }

    private fun changeGender() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setGenderColor() {
        //esto de aquí espera un color
        viewMale.setBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {
        val referenceColor = if (isSelectedComponent) {
            //esto es una referencia al color, más no el color
            R.color.background_component_selected
        } else {
            R.color.background_component
        }
        return ContextCompat.getColor(this, referenceColor)
    }

    private fun initUI() {
        setGenderColor()
        setWeight()
    }

}