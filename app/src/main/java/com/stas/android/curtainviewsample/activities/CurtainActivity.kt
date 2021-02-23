package com.stas.android.curtainviewsample.activities

import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import com.stas.android.curtainview.views.curtain.CurtainContainerView
import com.stas.android.curtainviewsample.R
import com.stas.android.curtainviewsample.databinding.ActivityCurtainBinding

class CurtainActivity : AppCompatActivity() {

    private lateinit var activityCurtainBinding: ActivityCurtainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCurtainBinding = ActivityCurtainBinding.inflate(layoutInflater)
//        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS) // Used to exclude layout components when transitioning
//        window.decorView.setOnSystemUiVisibilityChangeListener {
//            if(it == View.VISIBLE) {
//                hideSystemUi()
//            }
//        }
        setContentView(activityCurtainBinding.root)

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
//        if (hasFocus) hideSystemUi()
    }

    @VisibleForTesting
    fun getLayoutId() = R.layout.activity_curtain

    companion object {
        const val WITH_ACTION_BAR = "WITH_ACTION_BAR"
    }

    fun slideUp(view: View) {
        activityCurtainBinding.containerView.slideUp(true)
    }

    fun hideSystemUi() {
        // Make activity fullscreen and hide buttons at bottom of the screen
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }
}
