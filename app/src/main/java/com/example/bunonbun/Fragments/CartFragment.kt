package com.example.bunonbun.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.bunonbun.Activity.InformationActivity
import com.example.bunonbun.Adapters.CartAdapter
import com.example.bunonbun.Models.CartItems
import com.example.bunonbun.Interface.SwipeToDeleteCallBack
import com.example.bunonbun.R
import com.example.bunonbun.databinding.FragmentCartBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.Serializable

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var list : ArrayList<CartItems>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(layoutInflater)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding.imageView12.visibility = View.VISIBLE
        binding.button3.isEnabled = false

        CoroutineScope(Dispatchers.IO).launch{
            list = ArrayList()
            val db = Firebase.firestore
            db.collection(FirebaseAuth.getInstance().currentUser!!.uid).get()
                .addOnSuccessListener {
                    if (!it.isEmpty){
                        for (data in it.documents){
                            val cartItems = CartItems(
                                FoodName = data.getString("FoodName"),
                                FoodImage = data.getString("FoodImage"),
                                FoodPrice = data.getDouble("FoodPrice"),
                                key = data.getString("key"),
                                quantity = data.getLong("quantity")!!.toInt())
                            list.add(cartItems)
                            binding.imageView12.visibility = View.GONE
                            binding.button3.isEnabled = true
                        }
                        val totalPrice = list.sumOf { it.FoodPrice!! * it.quantity}
                        binding.textView10.text = "Total price : $totalPrice/-"
                        binding.cartRec.adapter = context?.let { it1 -> CartAdapter(it1, list, binding.textView10) }

                    }
                }
        }

        val swipeToDeleteCallBack = object : SwipeToDeleteCallBack(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val auth = FirebaseAuth.getInstance().currentUser!!.uid
                val position = viewHolder.adapterPosition
                list.removeAt(position)
                val db = Firebase.firestore
                val collectionRef = db.collection(auth)
                val query = collectionRef.whereEqualTo("Key", auth).limit(1)

                query.get().addOnSuccessListener { snapshot ->
                    if (snapshot.isEmpty) {
                        return@addOnSuccessListener
                    }

                    val document = snapshot.documents[0]
                    document.reference.delete().addOnSuccessListener {
                        Toast.makeText(activity, "\uD83D\uDE1E Item Removed \uD83D\uDE1E", Toast.LENGTH_SHORT).show()
                        if (list.isEmpty()){
                            binding.button3.isEnabled = false
                            binding.imageView12.visibility = View.VISIBLE
                        }else{
                            binding.imageView12.visibility = View.GONE
                        }
                        updateTotalPrice()
                    }.addOnFailureListener { e ->
                        // Handle any errors that occurred while deleting the document
                    }
                }.addOnFailureListener { e ->
                    // Handle any errors that occurred while performing the query
                }



                binding.cartRec.adapter?.notifyItemRemoved(position)

            }

        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallBack)
        itemTouchHelper.attachToRecyclerView(binding.cartRec)



        binding.button3.setOnClickListener {

            val updatedCartItems = CartAdapter(requireContext() , list , binding.textView10).getUpdatedCartItems()

            val intent = Intent(requireContext(), InformationActivity::class.java)
            intent.putExtra("updated_cart_items", updatedCartItems as Serializable)
            startActivity(intent)

        }

        return binding.root




    }
    private fun updateTotalPrice() {
        val totalPrice = list.sumOf { it.FoodPrice!!*it.quantity }
        binding.textView10.text = "Total price : $totalPrice/-"
    }

}