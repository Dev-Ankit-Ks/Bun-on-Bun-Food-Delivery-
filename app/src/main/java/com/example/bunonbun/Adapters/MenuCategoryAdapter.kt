package com.example.bunonbun.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bunonbun.Models.MenuItem
import com.example.bunonbun.R

//
//class MenuCategoryAdapter(val list: ArrayList<MenuItem>): RecyclerView.Adapter<MenuCategoryAdapter.MyViewHolder>() {
//
//    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//        val foodname = itemView.findViewById<TextView>(R.id.textView3)
//        val foodimage = itemView.findViewById<ImageView>(R.id.imageView6)
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        return MyViewHolder(
//            LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false)
//        )
//    }
//
//    override fun getItemCount(): Int {
//        return list.size
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.foodname.text = list[position].Foodname
//        holder.foodimage.setImageResource(list[position].Foodimage)
//    }
//}