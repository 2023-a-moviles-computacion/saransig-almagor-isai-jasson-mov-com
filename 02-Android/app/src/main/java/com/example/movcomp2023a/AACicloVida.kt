package com.example.movcomp2023a

import android.os.Bundle
import android.os.PersistableBundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.movcomp2023a.databinding.ActivityAacicloVidaBinding

class AACicloVida : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAacicloVidaBinding

    var textGlobal = ""

    fun mostrarSnackbar(text: String){
        textGlobal += text
        Snackbar.make(findViewById(R.id.cl_ciclo_vida),
        textGlobal, Snackbar.LENGTH_LONG).setAction("Action", null).show()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAacicloVidaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_aaciclo_vida)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        mostrarSnackbar("onCreate")
    }

    override fun onStart() {
        super.onStart()
        mostrarSnackbar("OnSTART")
    }

    override fun onResume() {
        super.onResume()
        mostrarSnackbar("OnResume")
    }

    override fun onRestart() {
        super.onRestart()
        mostrarSnackbar("onRestart")
    }

    override fun onPause() {
        super.onPause()
        mostrarSnackbar("onPause")
    }

    override fun onStop() {
        super.onStop()
        mostrarSnackbar("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        mostrarSnackbar("onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            //GUARDAR LAS VARIABLES
            //PRIMITIVAS
            putString("StringGuardado", textGlobal)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        //RECUPERAR LAS VARIABLES PRIMITIVAS
        val textRecuperado:String? = savedInstanceState.getString("StringGuardado")
        if (textRecuperado!=null){
            mostrarSnackbar(textRecuperado)
            textGlobal = textRecuperado
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_aaciclo_vida)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}