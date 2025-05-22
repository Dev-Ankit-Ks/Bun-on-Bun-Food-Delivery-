package com.example.bunonbun.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.bunonbun.API.RetrofitInstance
import com.example.bunonbun.Models.Quotes
import com.example.bunonbun.R
import com.example.bunonbun.databinding.FragmentMemeBinding
import com.example.bunonbun.databinding.FragmentMenuBinding
import okhttp3.CacheControl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private fun <T> Call<T>.enqueue(callback: Callback<List<T>>) {

}

class MemeFragment : Fragment() {

    private lateinit var binding: FragmentMemeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMemeBinding.inflate(layoutInflater)

        fetchData()

        return binding.root
    }

    private fun fetchData() {
        RetrofitInstance.apiInterface.getData().enqueue(object : Callback<List<Quotes>> {

            override fun onResponse(call: Call<List<Quotes>>, response: Response<List<Quotes>>) {
                binding.textView32.visibility = View.VISIBLE
                binding.textView32.text = response.body()!![0].content+"\n"+"- "+ response.body()!![0].author
            }

            override fun onFailure(call: Call<List<Quotes>>, t: Throwable) {
                Toast.makeText(requireActivity(), t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

}