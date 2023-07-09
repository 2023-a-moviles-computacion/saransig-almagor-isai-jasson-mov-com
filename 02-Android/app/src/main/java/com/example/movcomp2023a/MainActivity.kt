package com.example.movcomp2023a

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.Contacts
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    val callbackContenidoIntentExplicito =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            result ->
            if(result.resultCode == Activity.RESULT_OK){
                if(result.data != null){
                    //Logica de Negocio
                    val data = result.data
                    "${data?.getStringExtra("Nombre Modificado")}"
                }
            }
        }

    val callbackIntentPickUri =
    registerForActivityResult(
    ActivityResultContracts.StartActivityForResult()
    ){
        result ->
        if(result.resultCode === RESULT_OK){
            if(result.data != null){
                if(result.data!!.data!= null){
                    val uri: Uri = result.data!!.data!!
                    val cursor = contentResolver.query(uri, null, null, null, null, null)
                    cursor?.moveToFirst()
                    val indiceTelefono = cursor?.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    )
                    val telefono = cursor?.getString(indiceTelefono!!)
                    cursor?.close()
                    "Telefono ${telefono}"
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonCicloVida = findViewById<Button>(R.id.btn_ciclo_vida)
        botonCicloVida
            .setOnClickListener {
                irActividad(AACicloVida::class.java)
            }

        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonListView
            .setOnClickListener {
                irActividad(BListView::class.java)
            }

        val botonIntentoImplicito = findViewById<Button>(R.id.btn_ir_intent_implicito)
        botonIntentoImplicito
            .setOnClickListener {
                val intentConRespuesta = Intent(
                    Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                )
                callbackIntentPickUri.launch(intentConRespuesta)
            }
        val botonIntentoExplicito = findViewById<Button>(R.id.btn_ir_intent_explicito)
        botonIntentoExplicito
            .setOnClickListener {
                abrirActividadConParametros(CIntentExplicitoParametros::class.java)
            }

    }

    fun abrirActividadConParametros(
        clase: Class <*>
    ){
        val intentExplicito = Intent(this, clase)
        //Enviar parametros, solamente variables primitivas
        intentExplicito.putExtra("nombre", "Isai")
        intentExplicito.putExtra("apellido", "Saransig")
        intentExplicito.putExtra("edad", "22")
        callbackContenidoIntentExplicito.launch(intentExplicito)
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}