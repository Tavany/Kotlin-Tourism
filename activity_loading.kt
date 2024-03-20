package com.eja.tugasbesar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.eja.tugasbesar.databinding.ActivityLoadingBinding

class activity_loading : AppCompatActivity() {
    private val binding: ActivityLoadingBinding by lazy {
        ActivityLoadingBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fullName = intent.getStringExtra(FULL_NAME)
        binding.lblFullName.text = "Welcome $fullName!"

        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, 2000)
    }
    companion object {
        const val FULL_NAME = "full_name"
    }
}
