package com.geek.kotlin_api_revistas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class activity_Volumenes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volumenes)

        val bundle=intent.extras
        probandoVolley(bundle?.getString("dato_revista").toString())
    }

    fun probandoVolley(parm: String) {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        //val url: String = "https://revistas.uteq.edu.ec/ws/issues.php?j_id=1"
        val url: String = "https://revistas.uteq.edu.ec/ws/issues.php?j_id="+parm

        val txtresul = findViewById<TextView>(R.id.lbl_titulo_volumenes)
        //txtresul.text=parm
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
                var list_volumenes_id = arrayListOf<String>()
                var list_volumenes_doi = arrayListOf<String>()
                var list_volumenes_portadas = arrayListOf<String>()
                var list_volumenes_date = arrayListOf<String>()
                var list_volumenes_volumenes = arrayListOf<String>()

                //Extraemos Elementos de eiquetas
                var elemento: JSONObject =str.getJSONObject(index)
                while (index<n)
                {
                    var elemento: JSONObject =str.getJSONObject(index)

                    list_volumenes_id.add(elemento.getString("issue_id"))
                    list_volumenes_date.add(elemento.getString("date_published"))
                    list_volumenes_doi.add(elemento.getString("doi"))
                    list_volumenes_portadas.add(elemento.getString("cover"))
                    list_volumenes_volumenes.add("Volumen: "+ elemento.getString("volume")+ " Numero: " +elemento.getString("number"))
                    index++
                }
                VisualizaCardview_(list_volumenes_id,list_volumenes_doi,list_volumenes_volumenes,list_volumenes_date,list_volumenes_portadas)

            },
            { Log.d("API", "that didn't work") })
        queue.add(stringReq)
    }

    private fun VisualizaCardview_(list1: List<String>,
                                   list2: List<String>,
                                   list3: List<String>,
                                   list4: List<String>,
                                   list5: List<String>)
    {
        val recyclerView_ : RecyclerView =findViewById(R.id.recycler_volumenes)
        val adapter_=CustomerAdapter_Volumenes(this, list1,list2,list3,list4,list5)

        recyclerView_.layoutManager= LinearLayoutManager(this)
        recyclerView_.adapter=adapter_
    }
}