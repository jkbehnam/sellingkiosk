package com.example.myapplication.presentation.adapter

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.DbModel.FoodModel
import com.example.myapplication.data.DbModel.OrderModel

class MainFoodAdapter(
    val context: Context,
    var foodList: List<FoodModel>,
    var foodOrder: List<OrderModel>,
    val onclickListener: (FoodModel, ImageView) -> Unit,
    val onaddlickListener: (FoodModel,Int) -> Unit

) : RecyclerView.Adapter<MainFoodAdapter.ViewHolder>() {
    // ViewHolder برای نگه‌داری ویوهای هر آیتم در RecyclerView استفاده می‌شود.
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.food_tv_name)
        val itemIngredients: TextView = itemView.findViewById(R.id.food_tv_ingredients)
        val itemPrice: TextView = itemView.findViewById(R.id.food_tv_price)
        val itemImg: ImageView = itemView.findViewById(R.id.food_iv)
        val itemCardview: CardView = itemView.findViewById(R.id.cardView)
        val itemAdd: ImageView = itemView.findViewById(R.id.item_food_Add)
        val itemCountLL: LinearLayout = itemView.findViewById(R.id.food_count_ll)
        val itemCountivplus: ImageButton = itemView.findViewById(R.id.order_add)
        val itemCountivneg: ImageButton = itemView.findViewById(R.id.order_remove)
        val itemCounttv: TextView = itemView.findViewById(R.id.order_count)


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
            onaddlickListener(foodItem,1)
        })
        holder.itemCountivplus.setOnClickListener(View.OnClickListener {
            onaddlickListener(foodItem,1)
        })

        holder.itemCountivneg.setOnClickListener(View.OnClickListener {
            onaddlickListener(foodItem,-1)

        })
        val foundOrder: OrderModel? = foodOrder.find { it.food_Id == foodItem.id }

        if (foundOrder != null) {
            holder.itemCountLL.visibility = View.VISIBLE
            holder.itemAdd.visibility = View.INVISIBLE
            holder.itemCounttv.text=foundOrder.count.toString()
            //  holder.itemImg
        } else {
            holder.itemAdd.visibility = View.VISIBLE
            holder.itemCountLL.visibility = View.INVISIBLE
            if (foundOrder != null) {
                holder.itemCounttv.text= foundOrder.count.toString()
            }

        }
    }

    fun setOrderd(foodOrder: List<OrderModel>) {
        this.foodOrder = foodOrder
        this.notifyDataSetChanged()
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
