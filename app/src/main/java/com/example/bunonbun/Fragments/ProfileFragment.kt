package com.example.bunonbun.Fragments

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bunonbun.Activity.BunonBunActivity
import com.example.bunonbun.Activity.DeveloperActivity
import com.example.bunonbun.Activity.SigninActivity
import com.example.bunonbun.R
import com.example.bunonbun.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().actionBar!!.hide()
       binding = FragmentProfileBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding.shareApp.setOnClickListener {
                OpeningDialog()
        }

        val db = Firebase.firestore.collection("users")
            .document(FirebaseAuth.getInstance().currentUser!!.uid)

        db.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val phonenumber = document.getString("Phone number")
                    val address = document.getString("Address")
                    binding.loca.setText(address)
                    binding.pho.setText(phonenumber)
                } else {

                }
            }
            .addOnFailureListener { exception -> }

        (activity as AppCompatActivity).supportActionBar?.hide()
        val auth = FirebaseAuth.getInstance()

        binding.name.setText(auth.currentUser!!.displayName)
        binding.email.setText(auth.currentUser!!.email)
        Glide.with(requireActivity()).load(auth.currentUser!!.photoUrl).apply(RequestOptions().circleCrop()).into(binding.profileImage)
        binding.userLogout.setOnClickListener {
            auth.signOut()
            activity?.startActivity(Intent(requireActivity() , SigninActivity::class.java))
            activity?.finish()

            MotionToast.darkColorToast(requireActivity(),"Logging out !!!",
                "Logout successfuly",
                MotionToastStyle.WARNING,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(requireActivity(),R.font.baloo))

        }

        return binding.root
    }
    private fun OpeningDialog() {
       val dialog = Dialog(requireActivity())
        dialog.setContentView(R.layout.info)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.findViewById<Button>(R.id.button6).setOnClickListener {
            startActivity(Intent(requireActivity() , DeveloperActivity::class.java))
        }
        dialog.findViewById<Button>(R.id.button5).setOnClickListener {
            startActivity(Intent(requireActivity() , BunonBunActivity::class.java))
        }
        dialog.show()

    }


    override fun onDestroy() {
        super.onDestroy()
        requireActivity().actionBar!!.show()
    }

}