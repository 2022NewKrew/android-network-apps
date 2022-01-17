package com.survivalcoding.network_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.presentation.BasicMainActivity
import com.survivalcoding.network_apps.feature_basic.presentation.BasicActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ListView>(R.id.app_list_view).apply {
            val apps = listOf(
                AppInfo("Basic") {
                    startActivity(Intent(this@MainActivity, BasicMainActivity::class.java))
                },
                AppInfo("컨퍼런스 앱 1") {

                },
                AppInfo("Paging") {

                },
            )

            adapter =
                ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, apps)
            setOnItemClickListener { _, _, position, _ ->
                apps[position].navigateTo()
            }
        }
    }
}

data class AppInfo(
    val name: String,
    val navigateTo: () -> Unit,
) {
    override fun toString(): String {
        return name
    }
}