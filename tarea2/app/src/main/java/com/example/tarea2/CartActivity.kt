package com.example.tarea2

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cart)
        
        // Configurar Toolbar
        val toolbar: Toolbar = findViewById(R.id.cartToolbar)
        setSupportActionBar(toolbar)

        val cartMain = findViewById<View>(R.id.cart_main)
        if (cartMain != null) {
            ViewCompat.setOnApplyWindowInsetsListener(cartMain) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        // Referencias a elementos de UI
        val rgDelivery = findViewById<RadioGroup>(R.id.rgDelivery)
        val rgPayment = findViewById<RadioGroup>(R.id.rgPayment)
        val cbBeverages = findViewById<CheckBox>(R.id.cbBeverages)
        val cbDessert = findViewById<CheckBox>(R.id.cbDessert)
        val cbSauces = findViewById<CheckBox>(R.id.cbSauces)
        val cbTerms = findViewById<CheckBox>(R.id.cbTerms)
        val etNotes = findViewById<EditText>(R.id.etNotes)
        val btnConfirmOrder = findViewById<Button>(R.id.btnConfirmOrder)
        val btnCancelOrder = findViewById<Button>(R.id.btnCancelOrder)

        // Listener para cambios en tipo de entrega
        rgDelivery.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbPickup -> Toast.makeText(this, "Retiro en tienda seleccionado", Toast.LENGTH_SHORT).show()
                R.id.rbDelivery -> Toast.makeText(this, "Entrega a domicilio seleccionada", Toast.LENGTH_SHORT).show()
            }
        }

        // Listener para cambios en método de pago
        rgPayment.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbCash -> Toast.makeText(this, "Efectivo seleccionado", Toast.LENGTH_SHORT).show()
                R.id.rbCard -> Toast.makeText(this, "Tarjeta seleccionada", Toast.LENGTH_SHORT).show()
                R.id.rbWallet -> Toast.makeText(this, "Billetera digital seleccionada", Toast.LENGTH_SHORT).show()
            }
        }

        // Listeners para checkboxes de opciones
        cbBeverages.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Bebidas agregadas al carrito", Toast.LENGTH_SHORT).show()
            }
        }

        cbDessert.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Postre agregado al carrito", Toast.LENGTH_SHORT).show()
            }
        }

        cbSauces.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Salsas extra agregadas", Toast.LENGTH_SHORT).show()
            }
        }

        // Botón Confirmar Pedido
        btnConfirmOrder.setOnClickListener {
            val termsAccepted = cbTerms.isChecked
            val notes = etNotes.text.toString()
            
            if (!termsAccepted) {
                Toast.makeText(this, "Por favor acepta los términos y condiciones", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Obtener selecciones
            val deliveryType = if (findViewById<RadioButton>(R.id.rbPickup).isChecked) "Retiro" else "Entrega a domicilio"
            val paymentMethod = when (rgPayment.checkedRadioButtonId) {
                R.id.rbCash -> "Efectivo"
                R.id.rbCard -> "Tarjeta"
                R.id.rbWallet -> "Billetera Digital"
                else -> "No seleccionado"
            }

            val message = "¡Pedido Confirmado!\n" +
                    "Entrega: $deliveryType\n" +
                    "Pago: $paymentMethod\n" +
                    "Total: $19.80"
            
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }

        // Botón Cancelar
        btnCancelOrder.setOnClickListener {
            Toast.makeText(this, "Pedido cancelado", Toast.LENGTH_SHORT).show()
            finish() // Cierra la actividad
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_cart, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_modify_order -> {
                val message = "Modificar pedido"
                Log.i("CartActivity", message)
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_resend_order -> {
                val message = "Reenviar pedido"
                Log.i("CartActivity", message)
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_cancel_order -> {
                val message = "Cancelar pedido"
                Log.i("CartActivity", message)
                Toast.makeText(this, "Pedido cancelado", Toast.LENGTH_SHORT).show()
                finish()
                true
            }
            R.id.menu_order_history -> {
                val message = "Ver historial de pedidos"
                Log.i("CartActivity", message)
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}