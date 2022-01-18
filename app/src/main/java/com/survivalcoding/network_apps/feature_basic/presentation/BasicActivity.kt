package com.survivalcoding.network_apps.feature_basic.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.survivalcoding.network_apps.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)
    }
}