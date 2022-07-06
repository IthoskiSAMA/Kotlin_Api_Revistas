package com.geek.kotlin_api_revistas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class activity_Revistas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_revistas)
            probandoVolley()
    }


    fun probandoVolley() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url: String = "https://revistas.uteq.edu.ec/ws/journals.php"
        val txtresul = findViewById<TextView>(R.id.lbl_titulo_revistas)
        // Request a string response from the provided URL.
        val stringReq = StringRequest(
            Request.Method.GET, url,
            { response ->
                var strResp = response.toString()
                var str: JSONArray = JSONArray(strResp)

                //Contador
                var index=0
                //Cantidad de Elementos
                var n=str.length()
                //Listas que usaremos
                var list_revistas_id = arrayListOf<String>()
                var list_revistas_nombres = arrayListOf<String>()
                var list_revistas_portadas = arrayListOf<String>()
                var list_revistas_abreviatura = arrayListOf<String>()
                //Extraemos Elementos de eiquetas
                var elemento: JSONObject =str.getJSONObject(index)
                while (index<n)
                {
                    var elemento: JSONObject =str.getJSONObject(index)

                    list_revistas_id.add(elemento.getString("journal_id"))
                    list_revistas_nombres.add(elemento.getString("name"))
                    list_revistas_portadas.add(elemento.getString("portada"))
                    list_revistas_abreviatura.add(elemento.getString("abbreviation"))
                    index++
                }
                    VisualizaCardview_(list_revistas_id,list_revistas_nombres,list_revistas_abreviatura,list_revistas_portadas)
            },
            { Log.d("API", "that didn't work") })
        queue.add(stringReq)
    }


    private fun VisualizaCardview_(list1: List<String>,
                                   list2: List<String>,
                                   list3: List<String>,
                                   list4: List<String>)
    {
        val a= Intent(this, activity_Volumenes::class.java)
        val recyclerView_ : RecyclerView =findViewById(R.id.recycler_revista)
        val adapter_=CustomerAdapter_Revistas(this, list1,list2,list3,list4,a)

        recyclerView_.layoutManager= LinearLayoutManager(this)
        recyclerView_.adapter=adapter_
    }


}