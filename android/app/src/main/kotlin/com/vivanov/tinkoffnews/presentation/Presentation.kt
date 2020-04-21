package com.vivanov.tinkoffnews.presentation

import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.core.view.isVisible

/**
 * @author Vladimir Ivanov
 */
const val ANIMATION_DURATION: Long = 500L

fun View.fadeIn() {
    val fade = AlphaAnimation(0.0f, 1.0f)
    fade.duration = ANIMATION_DURATION
    fade.setAnimationListener(object : EmptyAnimationListener() {

        override fun onAnimationStart(animation: Animation) {
            isVisible = true
        }
    })
    startAnimation(fade)
}

private open class EmptyAnimationListener : Animation.AnimationListener {

    override fun onAnimationStart(animation: Animation) = Unit

    override fun onAnimationRepeat(animation: Animation) = Unit

    override fun onAnimationEnd(animation: Animation) = Unit
}

fun View.fadeOut() {
    val fade = AlphaAnimation(1.0f, 0.0f)
    fade.duration = ANIMATION_DURATION
    fade.setAnimationListener(object : EmptyAnimationListener() {

        override fun onAnimationEnd(animation: Animation) {
            isVisible = false
        }
    })
    startAnimation(fade)
}
