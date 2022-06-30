package com.example.dailypractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dailypractice.databinding.ActivityMainBinding
import com.example.dailypractice.statusbar.ImmersiveBar
import com.example.dailypractice.viewbinding.BindingUsing

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
            var intent = Intent(this,ImmersiveBar::class.java)
            startActivity(intent)
        }
        layoutBinding.btnViewBindingUsing.setOnClickListener {
            startActivity(Intent(this,BindingUsing::class.java))
        }
    }
}