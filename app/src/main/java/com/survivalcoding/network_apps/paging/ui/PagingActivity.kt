package com.survivalcoding.network_apps.paging.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.survivalcoding.network_apps.R
import com.survivalcoding.network_apps.databinding.ActivityPagingBinding
import com.survivalcoding.network_apps.paging.ui.posts.PostsFragment
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PagingActivity : AppCompatActivity() {

    private val binding by lazy { ActivityPagingBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace<PostsFragment>(R.id.pagingFcv)
            }
        }
    }
}