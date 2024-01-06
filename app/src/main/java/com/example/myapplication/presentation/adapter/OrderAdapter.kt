package com.example.myapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.DbModel.OrderModel

class OrderAdapter(private var orderList: List<OrderModel>) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productNameTextView: TextView = itemView.findViewById(R.id.productNameTextView)
        val productPriceTextView: TextView = itemView.findViewById(R.id.productPriceTextView)
        val productQuantityTextView: TextView = itemView.findViewById(R.id.productQuantityTextView)
        val finalPriceTextView: TextView = itemView.findViewById(R.id.finalPriceTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_factor, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val currentOrder = orderList[position]

        holder.productNameTextView.text = currentOrder.food_name
        holder.productPriceTextView.text = currentOrder.base_price.toString() + " دلار"
        holder.productQuantityTextView.text = "x${currentOrder.count.toInt()}"
        holder.finalPriceTextView.text = "قیمت نهایی: ${currentOrder.final_price} دلار"
    }

    override fun getItemCount(): Int {
        return orderList.size
    }
    fun setData(orderlist:List<OrderModel>){
        this.orderList=orderlist
        this.notifyDataSetChanged()
    }
}
