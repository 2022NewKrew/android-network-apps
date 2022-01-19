package com.survivalcoding.network_apps.feature_conference_app_1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.databinding.ActivityConferenceApp1Binding
import com.survivalcoding.network_apps.feature_conference_app_1.ui.conferences.ConferencesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConferenceApp1Activity : AppCompatActivity() {

    private val binding by lazy { ActivityConferenceApp1Binding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager.commit {
            setReorderingAllowed(false)
            replace<ConferencesFragment>(R.id.mainFcv)
        }
    }
}