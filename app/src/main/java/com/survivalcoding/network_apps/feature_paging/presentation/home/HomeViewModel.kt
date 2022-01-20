package com.survivalcoding.network_apps.feature_paging.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.survivalcoding.network_apps.feature_paging.domain.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    val photos = photoRepository.getPhotos()
        .cachedIn(viewModelScope)   // 캐싱
}