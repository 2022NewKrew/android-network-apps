package com.survivalcoding.network_apps.feature_paging.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.survivalcoding.network_apps.databinding.ActivityMainBinding
import com.survivalcoding.network_apps.databinding.ActivityPagingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PagingActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityPagingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}