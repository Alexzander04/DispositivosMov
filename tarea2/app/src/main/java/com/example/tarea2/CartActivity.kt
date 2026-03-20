package com.example.tarea2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cart)

        val cartMain = findViewById<View>(R.id.cart_main)
        if (cartMain != null) {
            ViewCompat.setOnApplyWindowInsetsListener(cartMain) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        val btnConfirmOrder = findViewById<Button>(R.id.btnConfirmOrder)
        btnConfirmOrder.setOnClickListener {
            Toast.makeText(this, "Order Confirmed!", Toast.LENGTH_SHORT).show()
        }
    }
}