package com.sunfusheng.music.util

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * @author sunfusheng on 2017/11/2.
 */
object DisplayUtil {

    val STANDARD_SCREEN_WIDTH = 720
    val STANDARD_SCREEN_HEIGHT = 1280

    private lateinit var windowManager: WindowManager

    private fun getWindowManager(context: Context): WindowManager {
        if (null == windowManager) {
            windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        }
        return windowManager
    }

    // 根据手机的分辨率将dp的单位转成px(像素)
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    // 根据手机的分辨率将px(像素)的单位转成dp
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    // 将px值转换为sp值
    fun px2sp(context: Context, pxValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    // 将sp值转换为px值
    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    // 屏幕宽度（像素）
    fun getWindowWidth(context: Activity): Int {
        val metric = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(metric)
        return metric.widthPixels
    }

    // 屏幕高度（像素）
    fun getWindowHeight(activity: Activity): Int {
        val metric = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metric)
        return metric.heightPixels
    }

    fun getScreenWidth(context: Context): Int {
        val defaultDisplay = getWindowManager(context).defaultDisplay

        val displayMetrics = DisplayMetrics()
        defaultDisplay.getMetrics(displayMetrics)
        if (displayMetrics.widthPixels > 0) {
            return displayMetrics.widthPixels
        }

        val screenSize = Point()
        defaultDisplay.getSize(screenSize)
        if (screenSize.x > 0) {
            return screenSize.x
        }

        return STANDARD_SCREEN_WIDTH
    }

    fun getScreenHeight(context: Context): Int {
        val defaultDisplay = getWindowManager(context).defaultDisplay

        val displayMetrics = DisplayMetrics()
        defaultDisplay.getMetrics(displayMetrics)
        if (displayMetrics.heightPixels > 0) {
            return displayMetrics.heightPixels
        }

        val screenSize = Point()
        defaultDisplay.getSize(screenSize)
        if (screenSize.y > 0) {
            return screenSize.y
        }

        return STANDARD_SCREEN_HEIGHT
    }

    fun getScreenDensity(context: Context): Float {
        val displayMetrics = DisplayMetrics()
        getWindowManager(context).defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.density
    }

    fun getDensityDpi(context: Context): Int {
        val displayMetrics = DisplayMetrics()
        getWindowManager(context).defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.densityDpi
    }
}