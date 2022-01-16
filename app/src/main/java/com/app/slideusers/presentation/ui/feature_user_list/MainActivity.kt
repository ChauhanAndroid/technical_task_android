package com.app.slideusers.presentation.ui.feature_user_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.slideusers.R
import com.app.slideusers.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}