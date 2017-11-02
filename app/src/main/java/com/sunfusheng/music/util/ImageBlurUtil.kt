package com.sunfusheng.music.util

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import android.widget.ImageView

/**
 * @author sunfusheng on 2017/11/1.
 */
object ImageBlurUtil {

    private val BLUR_RADIUS = 100

    fun blur(imageView: ImageView): Bitmap? {
        return blur(imageView.drawable)
    }

    fun blur(drawable: Drawable?): Bitmap? {
        if (drawable == null) {
            return null
        }

        return blur(drawableToBitmap(drawable))
    }

    fun blur(bitmap: Bitmap?): Bitmap? {
        if (bitmap == null) {
            return null
        }

        return try {
            blur(bitmap, BLUR_RADIUS)
        } catch (e: Exception) {
            bitmap
        }
    }

    private fun blur(source: Bitmap, radius: Int): Bitmap? {
        val bitmap = source.copy(source.config, true)

        if (radius < 1) {
            return null
        }

        val w = bitmap.width
        val h = bitmap.height

        val pix = IntArray(w * h)
        bitmap.getPixels(pix, 0, w, 0, 0, w, h)

        val wm = w - 1
        val hm = h - 1
        val wh = w * h
        val div = radius + radius + 1

        val r = IntArray(wh)
        val g = IntArray(wh)
        val b = IntArray(wh)
        var rSum: Int
        var gSum: Int
        var bSum: Int
        var x: Int
        var y: Int
        var i: Int
        var p: Int
        var yp: Int
        var yi: Int
        var yw: Int
        val vMin = IntArray(Math.max(w, h))

        var divSum = div + 1 shr 1
        divSum *= divSum
        val dv = IntArray(256 * divSum)
        i = 0
        while (i < 256 * divSum) {
            dv[i] = i / divSum
            i++
        }

        yi = 0
        yw = yi

        val stack = Array(div) { IntArray(3) }
        var stackPointer: Int
        var stackStart: Int
        var sir: IntArray
        var rbs: Int
        val r1 = radius + 1
        var rOutSum: Int
        var gOutSum: Int
        var bOutSum: Int
        var rInSum: Int
        var gInSum: Int
        var bInSum: Int

        y = 0
        while (y < h) {
            bSum = 0
            gSum = bSum
            rSum = gSum
            bOutSum = rSum
            gOutSum = bOutSum
            rOutSum = gOutSum
            bInSum = rOutSum
            gInSum = bInSum
            rInSum = gInSum
            i = -radius
            while (i <= radius) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))]
                sir = stack[i + radius]
                sir[0] = p and 0xff0000 shr 16
                sir[1] = p and 0x00ff00 shr 8
                sir[2] = p and 0x0000ff
                rbs = r1 - Math.abs(i)
                rSum += sir[0] * rbs
                gSum += sir[1] * rbs
                bSum += sir[2] * rbs
                if (i > 0) {
                    rInSum += sir[0]
                    gInSum += sir[1]
                    bInSum += sir[2]
                } else {
                    rOutSum += sir[0]
                    gOutSum += sir[1]
                    bOutSum += sir[2]
                }
                i++
            }
            stackPointer = radius

            x = 0
            while (x < w) {
                r[yi] = dv[rSum]
                g[yi] = dv[gSum]
                b[yi] = dv[bSum]

                rSum -= rOutSum
                gSum -= gOutSum
                bSum -= bOutSum

                stackStart = stackPointer - radius + div
                sir = stack[stackStart % div]

                rOutSum -= sir[0]
                gOutSum -= sir[1]
                bOutSum -= sir[2]

                if (y == 0) {
                    vMin[x] = Math.min(x + radius + 1, wm)
                }
                p = pix[yw + vMin[x]]

                sir[0] = p and 0xff0000 shr 16
                sir[1] = p and 0x00ff00 shr 8
                sir[2] = p and 0x0000ff

                rInSum += sir[0]
                gInSum += sir[1]
                bInSum += sir[2]

                rSum += rInSum
                gSum += gInSum
                bSum += bInSum

                stackPointer = (stackPointer + 1) % div
                sir = stack[stackPointer % div]

                rOutSum += sir[0]
                gOutSum += sir[1]
                bOutSum += sir[2]

                rInSum -= sir[0]
                gInSum -= sir[1]
                bInSum -= sir[2]

                yi++
                x++
            }
            yw += w
            y++
        }
        x = 0
        while (x < w) {
            bSum = 0
            gSum = bSum
            rSum = gSum
            bOutSum = rSum
            gOutSum = bOutSum
            rOutSum = gOutSum
            bInSum = rOutSum
            gInSum = bInSum
            rInSum = gInSum
            yp = -radius * w
            i = -radius
            while (i <= radius) {
                yi = Math.max(0, yp) + x

                sir = stack[i + radius]

                sir[0] = r[yi]
                sir[1] = g[yi]
                sir[2] = b[yi]

                rbs = r1 - Math.abs(i)

                rSum += r[yi] * rbs
                gSum += g[yi] * rbs
                bSum += b[yi] * rbs

                if (i > 0) {
                    rInSum += sir[0]
                    gInSum += sir[1]
                    bInSum += sir[2]
                } else {
                    rOutSum += sir[0]
                    gOutSum += sir[1]
                    bOutSum += sir[2]
                }

                if (i < hm) {
                    yp += w
                }
                i++
            }
            yi = x
            stackPointer = radius
            y = 0
            while (y < h) {
                pix[yi] = -0x1000000 and pix[yi] or (dv[rSum] shl 16) or (dv[gSum] shl 8) or dv[bSum]

                rSum -= rOutSum
                gSum -= gOutSum
                bSum -= bOutSum

                stackStart = stackPointer - radius + div
                sir = stack[stackStart % div]

                rOutSum -= sir[0]
                gOutSum -= sir[1]
                bOutSum -= sir[2]

                if (x == 0) {
                    vMin[y] = Math.min(y + r1, hm) * w
                }
                p = x + vMin[y]

                sir[0] = r[p]
                sir[1] = g[p]
                sir[2] = b[p]

                rInSum += sir[0]
                gInSum += sir[1]
                bInSum += sir[2]

                rSum += rInSum
                gSum += gInSum
                bSum += bInSum

                stackPointer = (stackPointer + 1) % div
                sir = stack[stackPointer]

                rOutSum += sir[0]
                gOutSum += sir[1]
                bOutSum += sir[2]

                rInSum -= sir[0]
                gInSum -= sir[1]
                bInSum -= sir[2]

                yi += w
                y++
            }
            x++
        }

        bitmap.setPixels(pix, 0, w, 0, 0, w, h)
        return bitmap
    }

    fun drawableToBitmap(drawable: Drawable): Bitmap {
        val bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                if (drawable.opacity != PixelFormat.OPAQUE)
                    Bitmap.Config.ARGB_8888
                else
                    Bitmap.Config.RGB_565)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        drawable.draw(canvas)
        return bitmap
    }
}