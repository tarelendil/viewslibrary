package com.stas.android.curtainview.extensions

import android.animation.Animator
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.ViewTreeObserver


fun ViewPropertyAnimator.setAnimationEndListener(action: () -> Unit): ViewPropertyAnimator =
    setListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {}
        override fun onAnimationEnd(animation: Animator?) {
            setListener(null)
            action()
        }

        override fun onAnimationCancel(animation: Animator?) {}
        override fun onAnimationStart(animation: Animator?) {}
    })

fun ViewPropertyAnimator.setAnimationStartAndEndListener(actionStart: () -> Unit, actionEnd: () -> Unit): ViewPropertyAnimator =
    setListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {}
        override fun onAnimationEnd(animation: Animator?) {
            setListener(null)
            actionEnd()
        }

        override fun onAnimationCancel(animation: Animator?) {}
        override fun onAnimationStart(animation: Animator?) {
            actionStart()
        }
    })

fun View.setOneTimeGlobalLayoutObserver(action: () -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            viewTreeObserver.removeOnGlobalLayoutListener(this)
            action()
        }
    })
}

fun View.setGlobalLayoutObserver(predicate: () -> Boolean) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (predicate()) viewTreeObserver.removeOnGlobalLayoutListener(this)
        }
    })
}