package com.example.bunonbun.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.bunonbun.Models.MainMenuItem
import com.example.bunonbun.R
import com.example.bunonbun.Room.MyApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList


class MainFoodAdapter(val context : Context , val foodList : ArrayList<MainMenuItem>) : RecyclerView.Adapter<MainFoodAdapter.MyViewHolder>() {
    private var tts : TextToSpeech? = null

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val food_name = itemView.findViewById<TextView>(R.id.textView8)
//        val food_category = itemView.findViewById<TextView>(R.id.textView6)
        val food_price = itemView.findViewById<TextView>(R.id.textView5)
        val food_image = itemView.findViewById<ImageView>(R.id.imageView7)
        val addTocart = itemView.findViewById<LottieAnimationView>(R.id.button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.main_menu_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.food_name.text = foodList[position].productName
//        holder.food_category.text = foodList[position].category
        holder.food_price.text = foodList[position].productPrice.toString()
//        Glide.with(context).load(foodList[position].productImage).into(holder.food_image)
        holder.food_image.setImageResource(foodList[position].productImage)
        CoroutineScope(Dispatchers.IO).launch {
            holder.addTocart.setOnClickListener {
                val db = MyApplication.db
                val dao = db?.mainMenuItemDao()
                val resource = context.resources

                val items = mutableListOf<MainMenuItem>()

                for (item in foodList) {
                    val imageUri = Uri.parse("android.resource://${context.packageName}/${item.productImage}")
                    val menuItemEntity = MainMenuItem(
                        category = item.category,
                        productName = item.productName,
                        productImage = item.productImage,
                        productPrice = item.productPrice,
                        quantity = item.quantity
                    )
                    items.add(menuItemEntity)
                }

                dao?.insertAll(items)
                Toast.makeText(context, "Sure", Toast.LENGTH_SHORT).show()





//                val db = Firebase.firestore.collection(FirebaseAuth.getInstance().currentUser!!.uid)
//                val key = db.document().id
//                val data = hashMapOf(
//                    "FoodName" to foodList.get(position).productName,
//                    "FoodImage" to foodList.get(position).productImage,
//                    "FoodPrice" to foodList.get(position).productPrice,
//                    "Key" to FirebaseAuth.getInstance().currentUser?.uid,
//                    "quantity" to foodList.get(position).quantity,
//
//                )
//                db.document(key)
//                    .set(data)
//                    .addOnSuccessListener { documentReference ->
//                        holder.addTocart.playAnimation()
//                        Toast.makeText(context, "Item added", Toast.LENGTH_SHORT).show()
//                    }
//                    .addOnFailureListener { e ->
//
//                    }
            }


        }
    }
}