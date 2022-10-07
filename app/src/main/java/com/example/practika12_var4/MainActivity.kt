package com.example.practika12_var4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var button2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.add_button)
        button.setOnClickListener{
            val intent: Intent = Intent(this, dobavit_odejdy_activity::class.java)
            startActivity(intent)
        }

        button2 = findViewById(R.id.show_button)
        button2.setOnClickListener{
            val intent = Intent(this, pokazat_odejdy_activity::class.java)
            startActivity(intent)
        }
    }
}