package com.geek.kotlin_api_revistas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var imagelogo: ImageView= findViewById(R.id.image_login)

       Picasso.get().load("https://revistas.uteq.edu.ec/public/site/pageHeaderTitleImage_es_ES.png").into(imagelogo);


        var boton: Button = findViewById(R.id.BtnEnviar)

        boton.setOnClickListener {
            val intent = Intent(this, activity_Revistas::class.java)
            startActivity(intent)
        }
    }



}