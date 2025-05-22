package com.example.bunonbun.Fragments

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
import com.example.bunonbun.Activity.MainActivity
import com.example.bunonbun.Models.CartItems
import com.example.bunonbun.Models.MainMenuItem
import com.example.bunonbun.R
import com.example.bunonbun.databinding.FragmentOrderStatusBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

class OrderStatusFragment : Fragment() {

    private lateinit var binding : FragmentOrderStatusBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().actionBar!!.hide()
        binding = FragmentOrderStatusBinding.inflate(layoutInflater)
        binding.button2.setOnClickListener {
            val dialog = Dialog(requireActivity())
            dialog.setContentView(R.layout.sure)
            dialog.findViewById<Button>(R.id.button10).setOnClickListener {
                dialog.dismiss()
                Firebase.firestore.collection("People carts")
                    .document(FirebaseAuth.getInstance().currentUser!!.uid).delete().addOnSuccessListener {
                        MotionToast.darkColorToast(requireActivity(),"cancellation",
                            "Your Order canceled Successfully",
                            MotionToastStyle.INFO,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(requireContext(),R.font.baloo))
                        val intent = Intent(requireActivity() , MainActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                        binding.constraintLayout4.visibility = View.GONE
                        binding.cardView14.visibility = View.GONE
                        binding.textView28.visibility = View.GONE
                        binding.imageView15.visibility = View.VISIBLE
                    }
            }
            dialog.findViewById<Button>(R.id.button11).setOnClickListener {
                dialog.dismiss()
            }
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.create()
            dialog.show()
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("People carts").document(FirebaseAuth.getInstance().currentUser!!.uid)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val price = document.getDouble("total_price")
                    if (price == null){
                        binding.constraintLayout4.visibility = View.GONE
                        binding.cardView14.visibility = View.GONE
                        binding.textView28.visibility = View.GONE
                    }else{
                        binding.constraintLayout4.visibility = View.VISIBLE
                        binding.cardView14.visibility = View.VISIBLE
                        binding.textView28.visibility = View.VISIBLE
                        binding.textView15.text = price.toString() + "/-"
                        binding.textView25.text = price.toString() + "/-"
                        binding.imageView15.visibility = View.GONE
                    }
                }
                else
                {}
            }
            .addOnFailureListener { exception ->
                Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
            }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().actionBar!!.show()
    }


}