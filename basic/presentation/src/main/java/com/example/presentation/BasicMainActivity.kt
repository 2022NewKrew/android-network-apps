package com.example.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.presentation.conferences.ConferencesFragment
import com.example.presentation.databinding.ActivityBasicMainBinding

class BasicMainActivity : AppCompatActivity() {

    private val binding: ActivityBasicMainBinding by lazy {
        ActivityBasicMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(binding.fragmentContainerView.id, ConferencesFragment.newInstance())
                .commit()
        }
    }
}