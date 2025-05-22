package com.example.bunonbun.Activity

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.bunonbun.API.RetrofitInstance
import com.example.bunonbun.Models.CartItems
import com.example.bunonbun.OrderRequest
import com.example.bunonbun.R
import com.example.bunonbun.databinding.ActivityInformationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

class InformationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val auth = FirebaseAuth.getInstance().currentUser

        val updatedCartItems = intent.getSerializableExtra("updated_cart_items") as ArrayList<CartItems>

        val updates = hashMapOf<String, Any>(
            "Address" to binding.address.text.toString(),
            "LandMark" to binding.landmark.text.toString(),
            "Phone number" to binding.phoneNumber.text.toString()
        )

        val db = Firebase.firestore.collection("users")
            .document(auth!!.uid)

        binding.button4.setOnClickListener {

            val userDetail = mapOf<String , Any>(
                "Name" to auth!!.displayName.toString(),
                "Address" to binding.address.text.toString(),
                "LandMark" to binding.landmark.text.toString(),
                "Phone number" to binding.phoneNumber.text.toString()
            )
            if (binding.address.text!!.isEmpty() || binding.landmark.text!!.isEmpty() || binding.phoneNumber.text!!.isEmpty()){
                MotionToast.darkColorToast(this,"Data issue",
                    "Please fill your all data",
                    MotionToastStyle.INFO,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(this,R.font.baloo))
            }
            else{

                val orderRequest = OrderRequest(updatedCartItems, "Ankit", "dighi")
                val call = RetrofitInstance.apiInterface.sendOrder(orderRequest)

                call.enqueue(object : retrofit2.Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            // Order sent successfully
                            // Handle success case here
                            Toast.makeText(applicationContext, "Order placed successfully!", Toast.LENGTH_SHORT).show()
                        } else {
                            // Handle API error
                            val errorResponse = response.errorBody()?.string()
                            val errorMessage = if (errorResponse.isNullOrEmpty()) {
                                "Unknown error occurred"
                            } else {
                                try {
                                    val jsonObject = JSONObject(errorResponse)
                                    jsonObject.getString("message")
                                } catch (e: JSONException) {
                                    "Unknown error occurred"
                                }
                            }
                            Toast.makeText(applicationContext, "Failed to place order: $errorMessage", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        // Handle network failure
                        Toast.makeText(applicationContext, "Failed to connect to the server", Toast.LENGTH_SHORT).show()
                    }
                })
//                db.update(userDetail)
//                val cartCollectionRef = FirebaseFirestore.getInstance().collection("People carts")
//                val totalPrice = updatedCartItems.sumOf { it.FoodPrice!! * it.quantity }
//                val cartItemsMapList = updatedCartItems.map { it.toMap() }
//
//                val data = mapOf(
//                    "cart_items" to cartItemsMapList,
//                    "total_price" to totalPrice,
//                    "Status" to "Pending"
//                )
//                val combininng = data.plus(userDetail)
//
//                val newDocRef = cartCollectionRef.document(auth.uid)
//                newDocRef.set(combininng)
//                    .addOnSuccessListener {
//                        val main = Firebase.firestore
//
//                        main.collection(auth.uid).get().addOnSuccessListener { querySnapshot ->
//                            for (document in querySnapshot.documents) {
//                                document.reference.delete()
//                            }
//                        }
//                        MotionToast.darkColorToast(this,"Placing order",
//                            "\uD83D\uDE03\uD83D\uDE03 Oder is placed !! \uD83E\uDD24\uD83E\uDD24",
//                            MotionToastStyle.SUCCESS,
//                            MotionToast.GRAVITY_BOTTOM,
//                            MotionToast.LONG_DURATION,
//                            ResourcesCompat.getFont(this,R.font.baloo))
//                        startActivity(Intent(this , orderActivity::class.java))
//                        finish()
//                    }
//                    .addOnFailureListener { e ->
//                        Toast.makeText(this, "failure", Toast.LENGTH_SHORT).show()
//                    }
            }
        }

        db.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val phonenumber = document.getString("Phone number")
                    val address = document.getString("Address")
                    val landmak = document.getString("LandMark")
                    binding.address.setText(address)
                    binding.phoneNumber.setText(phonenumber)
                    binding.landmark.setText(landmak)
                } else {
                    Toast.makeText(this, "Please fill your information", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { exception -> }


    }
}