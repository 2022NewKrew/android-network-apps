package com.survivalcoding.network_apps.feature_conferences.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.feature_conferences.data.datasource.remote.RemoteConferenceDataSource
import com.survivalcoding.network_apps.feature_conferences.data.repository.ConferenceRepositoryImpl

class ConferencesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conferences)
    }
}