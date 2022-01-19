package com.survivalcoding.network_apps.feature_basic.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.databinding.ActivityBasicBinding
import com.survivalcoding.network_apps.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasicActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityBasicBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}