package com.example.flickraiexemple.ui.components

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.example.flickraiexemple.R

class CustomLoading @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        private const val DEFAULT_ANIM_DURATION = 250
    }

    enum class Type(val drawable: Int) {
        RECTANGLE(R.drawable.bg_loading_element),
        OVAL(R.drawable.bg_loading_element_oval)
    }

    var type = Type.RECTANGLE

    init {

        val typedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.CustomLoading, 0, 0)
        typedArray.run {
            type = Type.values()[getInt(R.styleable.CustomLoading_loadingType, 0)]
        }

        val animatedBackground = ResourcesCompat.getDrawable(
            resources,
            type.drawable,
            null
        ) as AnimationDrawable

        animatedBackground.apply {
            background = this
            setEnterFadeDuration(DEFAULT_ANIM_DURATION)
            setExitFadeDuration(DEFAULT_ANIM_DURATION)
            isOneShot = false
            start()
        }
    }
}
