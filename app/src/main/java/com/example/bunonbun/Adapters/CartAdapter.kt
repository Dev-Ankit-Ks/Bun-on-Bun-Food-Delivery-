package com.example.bunonbun.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bunonbun.Models.CartItems
import com.example.bunonbun.R
import java.util.*
import kotlin.collections.ArrayList

class CartAdapter(val context: Context , private val food_retrive_list : ArrayList<CartItems> , val totalPriceTextView: TextView) : RecyclerView.Adapter<CartAdapter.MyViewHolder>() {


    ice = itemView.findViewById<TextView>(R.id.textView13)
    val minus = itemV
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val food_name = itemView.findViewById<TextView>(R.id.textView9)
        val food_priew.findViewById<ImageView>(R.id.imageView10)
        val plus = itemView.findViewById<ImageView>(R.id.imageView9)
        val indicator = itemView.findViewById<TextView>(R.id.textView12)
        val food_image = itemView.findViewById<ImageView>(R.id.imageView8)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cart_item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return food_retrive_list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cartItem = food_retrive_list[position]
        holder.food_name.text = cartItem.FoodName
        holder.food_price.text = "${cartItem.FoodPrice}"
        holder.indicator.text = cartItem.quantity.toString()
        Glide.with(context).load(cartItem.FoodImage).into(holder.food_image)

        holder.plus.setOnClickListener {
            cartItem.quantity++
            holder.indicator.text = cartItem.quantity.toString()
            updateTotalPrice()
        }

        holder.minus.setOnClickListener {
            if (cartItem.quantity > 1) {
                cartItem.quantity--
                holder.indicator.text = cartItem.quantity.toString()
                updateTotalPrice()
            }
        }

    }

    private fun updateTotalPrice() {
        val totalPrice = food_retrive_list.sumOf { it.FoodPrice!!*it.quantity }
        totalPriceTextView.text = "Total price : $totalPrice/-"
    }
    fun getUpdatedCartItems(): ArrayList<CartItems> {
        return food_retrive_list
    }


}