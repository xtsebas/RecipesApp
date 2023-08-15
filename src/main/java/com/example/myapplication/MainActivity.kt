package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val Lista = mutableListOf<Pair<String, String>>()
    private lateinit var adapter: ArrayAdapter<Pair<String, String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val receta = findViewById<EditText>(R.id.Receta)
        val url = findViewById<EditText>(R.id.url)
        val boton = findViewById<Button>(R.id.boton)
        val lista = findViewById<ListView>(R.id.lista)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, Lista)
        lista.adapter = adapter

        boton.setOnClickListener {
            val listaReceta = receta.text.toString()
            val listaUrl = url.text.toString()
            if (listaReceta.isNotBlank() && listaUrl.isNotBlank()) {
                Lista.add(Pair(listaReceta, listaUrl))
                adapter.notifyDataSetChanged()
                receta.text.clear()
                url.text.clear()
            } else {
                Toast.makeText(this, "Por favor, ingrese ambos campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
