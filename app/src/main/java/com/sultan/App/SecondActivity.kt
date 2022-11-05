package com.sultan.App

import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sultan.App.databinding.ErrorpageBinding

private lateinit var binding : ErrorpageBinding
class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ErrorpageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tryAgainButton.setOnClickListener {
            if (isNetworkAvailable()){
                startActivity(Intent(this , MainActivity::class.java))
            }
        }

    }
    private fun isNetworkAvailable(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)

        return (capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET))

    }
}