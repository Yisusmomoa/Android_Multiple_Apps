package com.Brandon.androidmaster.superheroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import com.Brandon.androidmaster.R
import com.Brandon.androidmaster.databinding.ActivityDetailSuperheroBinding
import com.Brandon.androidmaster.superheroapp.SuperHeroListActivity.Companion.EXTRA_ID
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperheroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailSuperheroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperheroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperheroInformation(id)
    }

    private fun getSuperheroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            //llamada a la api
            val superHeroDetail = getRetrofit().create(ApiService::class.java).getSuperheroById(id)
            if (superHeroDetail.isSuccessful && superHeroDetail.body() != null) {
                Log.i("SuperheroDetail", superHeroDetail.body().toString())
                runOnUiThread {
                    createUI(superHeroDetail.body()!!)
                    //2:36:30
                }
            }
        }
    }

    private fun createUI(superhero: SuperHeroDetailResponse) {
        Picasso.get().load(superhero.image.url).into(binding.ivSuperhero)
        binding.tvsuperheroName.text = superhero.name
        prepareStats(superhero.powerStats)
        binding.tvSuperheroRealName.text=superhero.biography.fullName
        binding.tvSuperheroPublisher.text=superhero.biography.publisher
    }

    private fun prepareStats(powerStats: PowerStatsResponse) {
        //params son los paramteros de la vista o del componente
        updateHeight(binding.viewCombat, powerStats.power)
        updateHeight(binding.viewDurability, powerStats.durability)
        updateHeight(binding.viewIntelligence, powerStats.intelligence)
        updateHeight(binding.viewPower, powerStats.power)
        updateHeight(binding.viewSpeed, powerStats.speed)
        updateHeight(binding.viewStrength, powerStats.strength)
    }

    private fun updateHeight(view: View, stat: String) {
        val params = view.layoutParams
        params.height = pxToDp(stat.toFloat())
        view.layoutParams = params
    }

    private fun pxToDp(px: Float): Int {
        //pasar de "pixeles" a dp
        //resources.displayMetrics : dependiendo de la pantalla esos "pixeles" va a equivaler un numero de dps
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}