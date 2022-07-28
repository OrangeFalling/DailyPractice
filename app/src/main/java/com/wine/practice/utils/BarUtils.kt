package com.wine.practice.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager

/**
 * author: created by song on 2022/6/28 15:52
 * description:
 */
object BarUtils {
    /**
     *
     */
    fun changeStatusBarBg(activity: Activity, statusBarColor: Int, isDarkText: Boolean){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val window = activity.window
            val decorView = window.decorView
            //5.0开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = statusBarColor
            //android 6.0以上才能设置状态栏字体、图标颜色. 如果设置了透明底色又不能深色字体,状态栏就GG了
            if (isDarkText && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            }
            return
        }

        val statusBarView = View(activity)
        statusBarView.setBackgroundColor(statusBarColor)
        val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity))
        val systemContent = activity.findViewById<ViewGroup>(android.R.id.content)
        if (systemContent.childCount > 0) {
            systemContent.getChildAt(0).fitsSystemWindows = true
            systemContent.addView(statusBarView, 0, lp)
        }
    }

    /**
     * 获取状态栏高度
     */
    private fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier(
            "status_bar_height", "dimen",
            "android"
        )
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}