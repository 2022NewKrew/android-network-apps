package com.survivalcoding.network_apps.feature_basic.presentation.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.survivalcoding.network_apps.feature_basic.data.datasource.local.LocalTodoDataSource
import com.survivalcoding.network_apps.feature_basic.data.repository.TodoRepositoryImpl
import com.survivalcoding.network_apps.feature_basic.domain.repository.TodoRepository
import com.survivalcoding.network_apps.feature_basic.domain.usecase.GetTodoUseCase
import com.survivalcoding.network_apps.feature_basic.presentation.BasicViewModel

class BasicViewModelProvider(
    private val repository: TodoRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BasicViewModel::class.java)) {
            return BasicViewModel(
                GetTodoUseCase(repository)
            ) as T
        }
        return super.create(modelClass)
    }
}