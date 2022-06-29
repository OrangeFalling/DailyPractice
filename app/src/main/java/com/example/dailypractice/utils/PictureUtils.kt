package com.example.dailypractice.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.PixelFormat
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import com.example.dailypractice.R
import java.io.ByteArrayOutputStream


/**
 * author: created by song on 2022/6/29 10:43
 * description: TODO
 */
object PictureUtils {

    fun drawableToBitmap(drawable: Drawable?): Bitmap? {
        if (drawable == null) {
            return null
        }

        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }

        val w = drawable.intrinsicWidth
        val h = drawable.intrinsicHeight

        val config = if (drawable.opacity == PixelFormat.OPAQUE) Bitmap.Config.RGB_565 else Bitmap.Config.ARGB_8888
        val bitmap = Bitmap.createBitmap(w, h, config)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, w, h)
        drawable.draw(canvas)
        return bitmap
    }


    /**
     * bitmap转化成byte数组
     * @param bm 需要转换的Bitmap
     * @return
     */
    fun bitmap2Bytes(bm: Bitmap?): ByteArray? {
        if (bm == null) {
            return null
        }
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos)
        return baos.toByteArray()
    }

    fun imageToBitmap(context: Context, bitmap: Int): Bitmap {
        return BitmapFactory.decodeResource(context.resources, bitmap)
    }
}