package com.example.proyecto_iib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem





class catalogoActivity : AppCompatActivity() {
    val list = mutableListOf<CarouselItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogo)

        initCarousel()
        iniciarRecyclerView()

        //menu


        //coleccion
        val btn_hombre = findViewById<Button>(R.id.btn_h)
        btn_hombre.setOnClickListener {
            irActividad(coleccionActivity::class.java, "hombre")
        }

        val btn_mujer = findViewById<Button>(R.id.btn_m)
        btn_mujer.setOnClickListener {
                irActividad(coleccionActivity::class.java, "mujer")
            }
        //tipo
        val btn_camiseta = findViewById<Button>(R.id.btn_camiseta)
        btn_camiseta.setOnClickListener {
                irActividad(tipoActivity::class.java, "camiseta")
            }

        val btn_pantalon = findViewById<Button>(R.id.btn_pantalon)
        btn_pantalon.setOnClickListener {
            irActividad(tipoActivity::class.java, "pantalon")
        }
        val btn_chaquetas = findViewById<Button>(R.id.btn_chaquetas)
        btn_chaquetas.setOnClickListener {
            irActividad(tipoActivity::class.java, "chaqueta")
        }

        val btn_hoodies = findViewById<Button>(R.id.btn_hoodies)
        btn_hoodies.setOnClickListener {
            irActividad(tipoActivity::class.java, "hoodie")
        }

    }

    fun iniciarRecyclerView(){
        val recyclerView =findViewById<RecyclerView>(R.id.rv_destacados)
        FirestoreDB.arregloDestacados.clear()
        val adaptador = ropaAdapter(this,
            FirestoreDB.arregloDestacados,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false )
        COFirebase.firebase!!.readDestacados("HubStyle", adaptador)
        adaptador.notifyDataSetChanged()
    }


    private fun initCarousel(){
        //Carousel
        list.add(CarouselItem("https://images.hugoboss.com/is/image/hugobosscsprod/230821_HBME_110_BOSS_FW23_Global_Campaign_Matteo_HBexperience_hugobossnewsletter_bott-xvUfWjtEUM?%24large%24&fmt=webp&align=0,-1&fit=crop,1&ts=1692607494490&qlt=80&wid=1280&hei=254"))
        list.add(CarouselItem("https://static.zara.net/photos///contents/mkt/spots/aw23-north-man-coats/subhome-xmedia-37//w/1280/IMAGE-landscape-fill-112d57b1-df32-47fa-a597-d39814310d24-default_0.jpg?ts=1694432200198"))
        list.add(CarouselItem("https://static.zara.net/photos///contents/mkt/spots/aw23-north-woman-collection/subhome-xmedia-37//w/1280/IMAGE-landscape-fill-ff47df01-c1b8-4336-80c6-dec4de4fc00a-default_0.jpg?ts=1694421438085"))
        list.add(CarouselItem("https://images.hugoboss.com/is/image/hugobosscsprod/230824_BWO_Dresses_1920x880?%24large%24&fmt=webp&align=0,-1&fit=crop,1&ts=1694179375744&qlt=80&wid=1280&hei=586"))
        val carousel: ImageCarousel = findViewById(R.id.carousel)
        carousel.addData(list)

        setupArrowAnimation(
            findViewById(R.id.cl_colection_tittle),
            findViewById(R.id.cl_colection),
            findViewById(R.id.imageView)
        )

        setupArrowAnimation(
            findViewById(R.id.cl_tipo_tittle),
            findViewById(R.id.cl_tipo),
            findViewById(R.id.imageView2)
        )
    }

    private fun setupArrowAnimation(
        colarrow: ConstraintLayout,
        cl: ConstraintLayout,
        btn_row: ImageView
    ) {
        colarrow.setOnClickListener {
            if (cl.visibility == View.GONE) {
                cl.visibility = View.VISIBLE
                btn_row.setImageResource(R.drawable.ic_arrow_up)
            } else {
                cl.visibility = View.GONE
                btn_row.setImageResource(R.drawable.ic_arrow_down)
            }
        }
    }


    fun irActividad(
        clase: Class<*>,
        nombre: String?
    ){
        val intent = Intent(this, clase)
        intent.putExtra("nombre", nombre)
        startActivity(intent)
        overridePendingTransition(0, 0);
        finish()
    }
}