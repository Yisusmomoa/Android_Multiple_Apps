package com.Brandon.androidmaster.settings

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.Brandon.androidmaster.R
import com.Brandon.androidmaster.databinding.ActivitySettingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

//las funciones de extensión nos permiten a traves de un componente crear métodos
//si tengo un textview puedo crear una función de extensión aplicarla a un textview y se aplicaria para todos

//el context es practicamente el this, Context es para estar dentro del contexto de android y ya dentro del contexto llamamos a datasroe
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Settings") // name es el nombre de la base de datos
//by es delegado nos permite crear una unica instancia de la base de datos

class SettingsActivity : AppCompatActivity() {

    companion object {
        const val VOLUME_LVL = "volume_lvl"
        const val KEY_BLUETOOTH = "key_bluetooth"
        const val KEY_DARK_MODE = "key_dark_mode"
        const val KEY_VIBRATION = "key_vibration"
    }

    private lateinit var binding: ActivitySettingsBinding
    private var firstTime: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        CoroutineScope(Dispatchers.IO).launch {
            getSettings().filter { firstTime }.collect { settingsModel ->
                //me engancho al flow y cada vez que haya un dato, me lo das
                //obtengo los datos
                //cada vez que haya un cambio me avisa
                if (settingsModel != null) {
                    runOnUiThread {
                        //recuperamos los valores y asignamos a la pantalla
                        binding.switchVibration.isChecked = settingsModel.vibration
                        binding.switchBluetooth.isChecked = settingsModel.bluetooth
                        binding.switchDarkMode.isChecked = settingsModel.darkMode
                        binding.rsVolume.setValues(settingsModel.volume.toFloat())
                        firstTime != firstTime
                    }
                }
            }
        }

        initUI()
    }

    private fun initUI() {
        binding.rsVolume.addOnChangeListener { _, value, _ ->
            Log.i("Slider", value.toString())
            CoroutineScope(Dispatchers.IO).launch {
                saveVolume(value.toInt())
            }
        }
        //value es el boolean el valor que cambia
        binding.switchBluetooth.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_BLUETOOTH, value)
            }
        }
        binding.switchVibration.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_VIBRATION, value)
            }
        }
        binding.switchDarkMode.setOnCheckedChangeListener { _, value ->
            if(value){enableDarkMode()}
            else{disableDarkMode()}
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_DARK_MODE, value)

            }
        }
    }

    private suspend fun saveVolume(value: Int) {
        //guardar el volumen
        dataStore.edit { preferences ->
            preferences[intPreferencesKey(VOLUME_LVL)] = value
        }
    }

    private suspend fun saveOptions(key: String, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }

    //los flows es un canal que nos permite estar escuchando continuamenete un valor
    //permite que en lugar de añadir un elemento a una lista de un recycler view mejor mandarlo a la base de datos
    private fun getSettings(): Flow<SettingsModel?> {
        return dataStore.data.map { preferences ->
            //preferences es donde estan los valores
            //? -> Operador elvis
            SettingsModel(
                volume = preferences[intPreferencesKey(VOLUME_LVL)] ?: 50,
                bluetooth = preferences[booleanPreferencesKey(KEY_BLUETOOTH)] ?: true,
                darkMode = preferences[booleanPreferencesKey(KEY_DARK_MODE)] ?: false,
                vibration = preferences[booleanPreferencesKey(KEY_VIBRATION)] ?: true
            )

            //me regresa todo el rato el valor de volumen almacenado
//            preferences[intPreferencesKey(VOLUME_LVL)]
//            preferences[booleanPreferencesKey(KEY_BLUETOOTH)]
            //solo regresa un valor o tipo para estos casos se crea un objeto que encapsule todos los valores que necesite
        }
    }

    private fun enableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        delegate.applyDayNight()
    }

    private fun disableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
    }
}