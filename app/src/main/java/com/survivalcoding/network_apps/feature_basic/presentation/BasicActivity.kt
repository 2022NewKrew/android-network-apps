package com.survivalcoding.network_apps.feature_basic.presentation

import android.content.ContentResolver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.loader.app.LoaderManager
import com.survivalcoding.network_apps.R

class BasicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)

        val cursor = contentResolver.query(
            MediaStore.Images.Media.INTERNAL_CONTENT_URI,

            null, null, null, null
        )

        cursor?.close()
    }
}