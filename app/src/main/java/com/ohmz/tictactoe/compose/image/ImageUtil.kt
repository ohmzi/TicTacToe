package com.ohmz.tictactoe.compose.image

import android.graphics.drawable.Drawable
import com.ohmz.tictactoe.core.models.Shape
import nl.dionsegijn.konfetti.xml.image.DrawableImage

object ImageUtil {
    @JvmStatic
    fun loadDrawable(
        drawable: Drawable,
        tint: Boolean = true,
        applyAlpha: Boolean = true,
    ): Shape.DrawableShape {
        val width = drawable.intrinsicWidth
        val height = drawable.intrinsicHeight
        val drawableImage = DrawableImage(drawable, width, height)
        return Shape.DrawableShape(drawableImage, tint, applyAlpha)
    }
}
