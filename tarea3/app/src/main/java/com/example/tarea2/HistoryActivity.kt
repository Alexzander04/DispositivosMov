package com.example.tarea3

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_history)

        val toolbar: Toolbar = findViewById(R.id.historyToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        val rvOrders: RecyclerView = findViewById(R.id.rvOrders)
        val tvEmptyHistory: TextView = findViewById(R.id.tvEmptyHistory)

        val dbHelper = DatabaseHelper(this)
        val orders = dbHelper.getAllOrders().toMutableList()

        val adapter = HistoryAdapter(orders) { order ->
            // Lógica para eliminar
            val result = dbHelper.deleteOrder(order.id)
            if (result > 0) {
                val updatedOrders = dbHelper.getAllOrders()
                (findViewById<RecyclerView>(R.id.rvOrders).adapter as HistoryAdapter).updateList(updatedOrders)
                
                if (updatedOrders.isEmpty()) {
                    tvEmptyHistory.visibility = View.VISIBLE
                    rvOrders.visibility = View.GONE
                }
                android.widget.Toast.makeText(this, "Pedido eliminado", android.widget.Toast.LENGTH_SHORT).show()
            }
        }

        if (orders.isEmpty()) {
            tvEmptyHistory.visibility = View.VISIBLE
            rvOrders.visibility = View.GONE
        } else {
            tvEmptyHistory.visibility = View.GONE
            rvOrders.visibility = View.VISIBLE
            rvOrders.layoutManager = LinearLayoutManager(this)
            rvOrders.adapter = adapter
        }
    }
}
