package com.sultan.App

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sultan.App.databinding.ActivityLoginBinding

private lateinit var binding: ActivityLoginBinding

class loginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}