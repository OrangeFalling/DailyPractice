package com.wine.practice.surface

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.dailypractice.R

/**
 * author: created by song on 2022/7/28 11:10
 * description: 二维码扫描框
 */
class SurfaceActivity:AppCompatActivity() {
    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
        setContentView(R.layout.activity_surface)
    }
}