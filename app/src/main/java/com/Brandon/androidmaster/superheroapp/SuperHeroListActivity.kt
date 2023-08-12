package com.Brandon.androidmaster.superheroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.Brandon.androidmaster.R
import com.Brandon.androidmaster.databinding.ActivitySuperHeroListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

class SuperHeroListActivity : AppCompatActivity() {

    //binding
    private lateinit var binding: ActivitySuperHeroListBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: SuperheroAdapter

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            //se llama cuando pulsemos al botón de buscar
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            //se llama cuando estemos tecleando/escribiendo
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        adapter = SuperheroAdapter() { navigateToDetail(it) }
        binding.rvSuperHero.setHasFixedSize(true)
        binding.rvSuperHero.layoutManager = LinearLayoutManager(this)
        binding.rvSuperHero.adapter = adapter
    }

    //Todo controloar los errores 1:18:30
    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            //todo lo que realicemos dentro de estas llaves se va a realizar en un hilo secundario
            //llamada a la api
            val myResponse: Response<SuperHeroDataResponse> =
                retrofit.create(ApiService::class.java).getSuperheroes(query)
            if (myResponse.isSuccessful) {
                val responseBody: SuperHeroDataResponse? = myResponse.body()
                Log.i("Responseretrofit", "funciona")
                if (responseBody != null && responseBody.response != "error") {
                    Log.i("Responseretrofit", responseBody.toString())
                    runOnUiThread {
                        adapter.updateList(responseBody.superheroes)
                        binding.progressBar.isVisible = false
                    }
                } else {
                    runOnUiThread {
                        binding.progressBar.isVisible = false
                        showError()
                    }
                }
            } else {
                Log.i("Responseretrofit", "No funciona")
                runOnUiThread {
                    showError()
                }
            }
        }

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToDetail(id: String) {
        Log.i("IdSuper", id)
        val intent = Intent(this, DetailSuperheroActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}