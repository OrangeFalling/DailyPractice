package com.example.dailypractice.statusbar

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.graphics.ColorUtils
import androidx.core.view.ViewCompat
import androidx.palette.graphics.Palette
import com.example.dailypractice.R
import com.example.dailypractice.utils.BarUtils
import com.example.dailypractice.utils.DeviceUtils
import com.example.dailypractice.utils.PictureUtils
import kotlinx.android.synthetic.main.activity_immersive_bar.*

/**
 * Themes: NoActionBar
 * Add: implementation 'androidx.palette:palette:1.0.0'
 */
class ImmersiveBar : AppCompatActivity() {
    companion object{
        const val TAG = "ImmersiveBar--"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_immersive_bar)

        window.statusBarColor = Color.TRANSPARENT

        /** 布局全屏，状态栏置于布局层之下，初步实现沉浸式状态栏 **/
        val frameLayout = findViewById<FrameLayout>(R.id.root_layout)
        frameLayout.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        /** 处理布局中的控件被状态栏遮挡的问题 -> 偏移systemWindowInsetTop **/
        val container = findViewById<LinearLayout>(R.id.ll_container)
        ViewCompat.setOnApplyWindowInsetsListener(container) { view, insets ->
            val params = view.layoutParams as FrameLayout.LayoutParams
            params.topMargin = insets.systemWindowInsetTop
            insets
        }

        clickEvent()
    }


    private fun clickEvent(){
        findViewById<Button>(R.id.button_dark).setOnClickListener {
            setBgImage(R.mipmap.bg_picture_dark)
        }
        findViewById<Button>(R.id.button_light).setOnClickListener {
            setBgImage(R.mipmap.bg_picture_light)
        }
    }

    /** 动态设置背景图 **/
    private fun setBgImage(image:Int){
        // 将资源文件转为bitmap格式
        val bitmap = PictureUtils.imageToBitmap(this,image)
        Log.i(TAG, "onCreate: bitmap:$bitmap")
        iv_bg.setImageBitmap(bitmap)
        detectBitmapColor(bitmap)
    }

    /** 使用Palette探测出状态栏图片区域是浅色背景还是深色背景 **/
    private fun detectBitmapColor(bitmap: Bitmap) {
        val colorCount = 5
        val left = 0
        val top = 0
        val right = DeviceUtils.getScreenWidth(this)
        val bottom = DeviceUtils.getStatusBarHeight(this)

        Palette
            .from(bitmap)
            .maximumColorCount(colorCount)
            .setRegion(left, top, right, bottom)
            .generate {
                it?.let { palette ->
                    var mostPopularSwatch: Palette.Swatch? = null
                    for (swatch in palette.swatches) {
                        if (mostPopularSwatch == null
                            || swatch.population > mostPopularSwatch.population
                        ) {
                            mostPopularSwatch = swatch
                        }
                    }
                    mostPopularSwatch?.let { swatch ->
                        val luminance = ColorUtils.calculateLuminance(swatch.rgb)
                        // If the luminance value is lower than 0.5, we consider it as dark.
                        if (luminance < 0.5) {
                            setDarkStatusBar()
                        } else {
                            setLightStatusBar()
                        }
                    }
                }
            }
    }

    private fun setLightStatusBar() {
        val flags = window.decorView.systemUiVisibility
        window.decorView.systemUiVisibility = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    private fun setDarkStatusBar() {
        val flags = window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.decorView.systemUiVisibility = flags xor View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }


}