package com.example.android.videoapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.videoapp.R

/**
 * This is a single activity application that uses the Navigation library. Content is displayed
 * by Fragments.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}