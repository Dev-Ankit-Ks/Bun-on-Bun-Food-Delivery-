package com.example.bunonbun.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.bunonbun.R
import com.example.bunonbun.databinding.ActivityInstaBinding

class InstaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInstaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInstaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.web.loadUrl("https://www.instagram.com/bunonbun_restaurant/")
        binding.web.settings.javaScriptEnabled = true
        binding.web.settings.setSupportZoom(true)
        binding.web.clearCache(true)
        binding.web.clearHistory()
        binding.web.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                // Page finished loading
            }

            override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
                Log.d("MAINSWFCSFSC" , error.toString())
            }
        }

    }
}