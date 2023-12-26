package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Models.FoodMainModel

class MainFoodAdapter(val context:Context,val foodList:List<FoodMainModel>):RecyclerView.Adapter<MainFoodAdapter.ViewHolder>() {
    // ViewHolder برای نگه‌داری ویوهای هر آیتم در RecyclerView استفاده می‌شود.
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.food_tv_name)
        val itemIngredients: TextView = itemView.findViewById(R.id.food_tv_ingredients)
        val itemPrice: TextView = itemView.findViewById(R.id.food_tv_price)
        val itemImg: ImageView = itemView.findViewById(R.id.food_iv)

    }

    // این متد برای ایجاد ViewHolder جدید بر اساس layout آیتم استفاده می‌شود.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false)
        return ViewHolder(view)
    }

    // این متد برای اتصال داده‌ها به ViewHolder اجرا می‌شود.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val foodList = foodList[position]
        holder.itemName.text = foodList.name
        holder.itemIngredients.text=foodList.ingredients
        holder.itemPrice.text=foodList.price

        val imageResId = context.resources.getIdentifier(foodList.imv, "drawable", context.packageName)

        // تنظیم تصویر با استفاده از شناسه منبع
        holder.itemImg.setImageResource(imageResId)
    }


    // تعداد کل آیتم‌ها در RecyclerView را بازمی‌گرداند.
    override fun getItemCount(): Int {
        return foodList.size
    }
}