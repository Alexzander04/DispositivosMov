package com.example.tarea3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView = findViewById<NavigationView>(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    Log.i("MainActivity", "Ir a Inicio")
                    Toast.makeText(this, "Inicio", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_orders -> {
                    val intent = Intent(this, HistoryActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_promos -> {
                    Log.i("MainActivity", "Ver Promociones")
                    Toast.makeText(this, "Promociones", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_profile -> {
                    Log.i("MainActivity", "Acceder a Mi Perfil")
                    Toast.makeText(this, "Mi Perfil", Toast.LENGTH_SHORT).show()
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })
        
        val mainView = findViewById<android.view.View>(R.id.main)
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
                insets
            }
        }

        val btnAddToCart = findViewById<Button>(R.id.btnAddToCart)
        
        // Setup quantity buttons
        setupQtyButtons(R.id.btnMinusBurger, R.id.btnPlusBurger, R.id.etQtyBurger)
        setupQtyButtons(R.id.btnMinusWings, R.id.btnPlusWings, R.id.etQtyWings)
        setupQtyButtons(R.id.btnMinusFries, R.id.btnPlusFries, R.id.etQtyFries)
        setupQtyButtons(R.id.btnMinusHotdog, R.id.btnPlusHotdog, R.id.etQtyHotdog)
        setupQtyButtons(R.id.btnMinusBurrito, R.id.btnPlusBurrito, R.id.etQtyBurrito)
        setupQtyButtons(R.id.btnMinusSalchipapa, R.id.btnPlusSalchipapa, R.id.etQtySalchipapa)
        setupQtyButtons(R.id.btnMinusMilanesa, R.id.btnPlusMilanesa, R.id.etQtyMilanesa)

        btnAddToCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            
            // Obtener cantidades de los EditText
            val qtyBurger = findViewById<android.widget.EditText>(R.id.etQtyBurger).text.toString().toIntOrNull() ?: 0
            val qtyWings = findViewById<android.widget.EditText>(R.id.etQtyWings).text.toString().toIntOrNull() ?: 0
            val qtyFries = findViewById<android.widget.EditText>(R.id.etQtyFries).text.toString().toIntOrNull() ?: 0
            val qtyHotdog = findViewById<android.widget.EditText>(R.id.etQtyHotdog).text.toString().toIntOrNull() ?: 0
            val qtyBurrito = findViewById<android.widget.EditText>(R.id.etQtyBurrito).text.toString().toIntOrNull() ?: 0
            val qtySalchipapa = findViewById<android.widget.EditText>(R.id.etQtySalchipapa).text.toString().toIntOrNull() ?: 0
            val qtyMilanesa = findViewById<android.widget.EditText>(R.id.etQtyMilanesa).text.toString().toIntOrNull() ?: 0

            intent.putExtra("QTY_BURGER", qtyBurger)
            intent.putExtra("QTY_WINGS", qtyWings)
            intent.putExtra("QTY_FRIES", qtyFries)
            intent.putExtra("QTY_HOTDOG", qtyHotdog)
            intent.putExtra("QTY_BURRITO", qtyBurrito)
            intent.putExtra("QTY_SALCHIPAPA", qtySalchipapa)
            intent.putExtra("QTY_MILANESA", qtyMilanesa)

            startActivity(intent)
        }
    }

    private fun setupQtyButtons(minusId: Int, plusId: Int, editId: Int) {
        val btnMinus = findViewById<Button>(minusId)
        val btnPlus = findViewById<Button>(plusId)
        val etQty = findViewById<android.widget.EditText>(editId)

        btnMinus.setOnClickListener {
            val current = etQty.text.toString().toIntOrNull() ?: 0
            if (current > 0) {
                etQty.setText((current - 1).toString())
            }
        }

        btnPlus.setOnClickListener {
            val current = etQty.text.toString().toIntOrNull() ?: 0
            etQty.setText((current + 1).toString())
        }
    }
}