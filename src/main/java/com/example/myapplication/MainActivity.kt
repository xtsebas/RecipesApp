package com.example.myapplication

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.io.InputStream
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val itemList = mutableListOf<Pair<String, String>>()
    private lateinit var adapter: ArrayAdapter<Pair<String, String>>
    private lateinit var imagen: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val receta = findViewById<EditText>(R.id.Receta)
        val url = findViewById<EditText>(R.id.url)
        val boton = findViewById<Button>(R.id.boton)
        val lista = findViewById<ListView>(R.id.lista)
        imagen = findViewById(R.id.imagen)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemList)
        lista.adapter = adapter

        boton.setOnClickListener {
            val listaReceta = receta.text.toString()
            val listaUrl = url.text.toString()
            if (listaReceta.isNotBlank() && listaUrl.isNotBlank()) {
                itemList.add(Pair(listaReceta, listaUrl))
                adapter.notifyDataSetChanged()
                receta.text.clear()
                url.text.clear()

            } else {
                Toast.makeText(this, "Por favor, ingrese ambos campos", Toast.LENGTH_SHORT).show()
            }
        }

        lista.setOnItemClickListener { parent, view, position, id ->
            val clickedItem = itemList[position]
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Eliminar")
            alertDialogBuilder.setMessage("¿Deseas eliminar ${clickedItem.first}?")
            alertDialogBuilder.setPositiveButton("Sí") { _, _ ->
                itemList.removeAt(position)
                adapter.notifyDataSetChanged()
            }
            alertDialogBuilder.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }
}
