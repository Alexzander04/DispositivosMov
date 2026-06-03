package com.example.tarea3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(
    private var orders: MutableList<Order>,
    private val onDeleteClick: (Order) -> Unit
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvOrderId: TextView = view.findViewById(R.id.tvOrderId)
        val tvOrderDate: TextView = view.findViewById(R.id.tvOrderDate)
        val tvOrderItems: TextView = view.findViewById(R.id.tvOrderItems)
        val tvOrderDetails: TextView = view.findViewById(R.id.tvOrderDetails)
        val tvOrderTotal: TextView = view.findViewById(R.id.tvOrderTotal)
        val btnDelete: ImageButton = view.findViewById(R.id.btnDeleteOrder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = orders[position]
        holder.tvOrderId.text = "Pedido #${order.id}"
        holder.tvOrderDate.text = order.timestamp
        
        val items = StringBuilder()
        if (order.burger > 0) items.append("Burger x${order.burger}, ")
        if (order.wings > 0) items.append("Alitas x${order.wings}, ")
        if (order.fries > 0) items.append("Papas x${order.fries}, ")
        if (order.hotdog > 0) items.append("Hotdog x${order.hotdog}, ")
        if (order.burrito > 0) items.append("Burrito x${order.burrito}, ")
        if (order.salchipapa > 0) items.append("Salchipapa x${order.salchipapa}, ")
        if (order.milanesa > 0) items.append("Milanesa x${order.milanesa}, ")
        
        holder.tvOrderItems.text = items.toString().trim().removeSuffix(",")
        holder.tvOrderDetails.text = "Entrega: ${order.delivery} | Pago: ${order.payment}"
        holder.tvOrderTotal.text = String.format("Total: $%.2f", order.total)

        holder.btnDelete.setOnClickListener {
            onDeleteClick(order)
        }
    }

    override fun getItemCount() = orders.size

    fun updateList(newOrders: List<Order>) {
        orders = newOrders.toMutableList()
        notifyDataSetChanged()
    }
}
