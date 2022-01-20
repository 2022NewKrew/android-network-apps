package com.survivalcoding.network_apps.conferences.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.survivalcoding.network_apps.R

class ConferencesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conferences)
        supportActionBar?.hide()
    }
}