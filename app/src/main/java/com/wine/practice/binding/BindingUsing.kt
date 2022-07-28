package com.wine.practice.binding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dailypractice.databinding.BindingUsingBinding

/**
 * author: created by song on 2022/6/29 14:32
 * description: TODO
 */
class BindingUsing : AppCompatActivity() {
    private lateinit var binding:BindingUsingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BindingUsingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvOne.text = "1"
    }
}