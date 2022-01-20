package com.survivalcoding.network_apps.feature_paging.data.data_source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.survivalcoding.network_apps.feature_paging.domain.model.Photo

class PhotoRemotePagingSource(
    private val api: PhotoApi,
) : PagingSource<Int, Photo>() {

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            val nextPage = params.key ?: 1
            val photos = api.getPhotos(nextPage)
            Log.d("PhotoPagingSource", "load: ")

            LoadResult.Page(
                data = photos,
                prevKey = null,
                nextKey = nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}