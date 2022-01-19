package com.survivalcoding.network_apps.conference.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.survivalcoding.network_apps.databinding.ActivityConferenceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConferenceActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityConferenceBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}