package com.example.dailypractice.statusbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dailypractice.R

/**
 * Themes: NoActionBar
 * Add: implementation 'com.jaeger.statusbarutil:library:1.5.1'
 */
class ImmersiveBar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_immersive_bar)
    }
}