package com.example.dailypractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dailypractice.statusbar.ImmersiveBar
import kotlinx.android.synthetic.main.activity_main.*

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        click()
    }

    private fun click(){
        btn_immersive_status_bar.setOnClickListener {
            var intent = Intent(this,ImmersiveBar::class.java)
            startActivity(intent)
        }
    }
}