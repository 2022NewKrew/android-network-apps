package com.survivalcoding.network_apps.conference_app_1.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.conference_app_1.data.datasource.ConferenceRemoteDataSource
import com.survivalcoding.network_apps.conference_app_1.data.network.ConferencesApi
import com.survivalcoding.network_apps.conference_app_1.data.repository.ConferenceRepositoryImpl

class ListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}