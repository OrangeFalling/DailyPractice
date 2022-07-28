package com.wine.practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dailypractice.databinding.ActivityMainBinding
import com.wine.practice.bar.ImmersiveBar
import com.wine.practice.surface.SurfaceActivity
import com.wine.practice.binding.BindingUsing

class MenuActivity : AppCompatActivity() {
    private lateinit var layoutBinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(layoutBinding.root)
        click()
    }

    private fun click(){
        layoutBinding.btnImmersiveStatusBar.setOnClickListener {
            var intent = Intent(this, ImmersiveBar::class.java)
            startActivity(intent)
        }
        layoutBinding.btnViewBindingUsing.setOnClickListener {
            startActivity(Intent(this, BindingUsing::class.java))
        }
        layoutBinding.btnSurfaceView.setOnClickListener {
            startActivity(Intent(this, SurfaceActivity::class.java))
        }
    }
}