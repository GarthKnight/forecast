package com.example.pirates_challenge.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pirates_challenge.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}