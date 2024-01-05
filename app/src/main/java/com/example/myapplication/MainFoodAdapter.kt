package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Models.FoodModel
import com.example.myapplication.Models.OrderModel
import okhttp3.internal.notify

class MainFoodAdapter(
    val context: Context,
    var foodList: List<FoodModel>,
    val foodCount:MutableMap<Int ,Double> ,
    val onclickListener: (FoodModel, ImageView) -> Unit,
    val onaddlickListener: (FoodModel) -> Unit

) : RecyclerView.Adapter<MainFoodAdapter.ViewHolder>() {
    // ViewHolder برای نگه‌داری ویوهای هر آیتم در RecyclerView استفاده می‌شود.
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.food_tv_name)
        val itemIngredients: TextView = itemView.findViewById(R.id.food_tv_ingredients)
        val itemPrice: TextView = itemView.findViewById(R.id.food_tv_price)
        val itemImg: ImageView = itemView.findViewById(R.id.food_iv)
        val itemCardview: CardView = itemView.findViewById(R.id.cardView)
        val itemAdd: ImageView = itemView.findViewById(R.id.item_food_Add)

    }

    // این متد برای ایجاد ViewHolder جدید بر اساس layout آیتم استفاده می‌شود.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false)
        return ViewHolder(view)
    }

    // این متد برای اتصال داده‌ها به ViewHolder اجرا می‌شود.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val foodItem = foodList[position]
        holder.itemName.text = foodItem.name
        holder.itemIngredients.text = foodItem.ingredients
        holder.itemPrice.text = foodItem.price.toString()
        val imageResId =
            context.resources.getIdentifier(foodItem.imv, "drawable", context.packageName)
        holder.itemImg.setImageResource(imageResId)

        holder.itemImg.transitionName = foodItem.imv
        // تنظیم تصویر با استفاده از شناسه منبع
        holder.itemCardview.setOnClickListener(View.OnClickListener {
            onclickListener(foodItem, holder.itemImg)
        })

        holder.itemAdd.setOnClickListener(View.OnClickListener {
            onaddlickListener(foodItem)
        })
        val count=foodCount.get(foodItem.id)
        if (count != null) {

        }

    }

    fun setData(foodList: List<FoodModel>) {
        this.foodList = foodList
        this.notifyDataSetChanged()
    }

    // تعداد کل آیتم‌ها در RecyclerView را بازمی‌گرداند.
    override fun getItemCount(): Int {
        return foodList.size
    }
}
