package com.therealbluepandabear.pixapencil.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.therealbluepandabear.pixapencil.utility.LongConstants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun Activity.navigateHome(fragmentManager: FragmentManager, fragmentInstance: Fragment, rootLayout: View, fragmentHost: FrameLayout, newTitle: String) {
    (rootLayout as ViewGroup).forEach { view -> view.visibility = View.VISIBLE }

    rootLayout.findViewTreeLifecycleOwner()!!.lifecycleScope.launch {
        delay(LongConstants.DefaultHandlerDelay)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
    }

    fragmentHost.visibility = View.VISIBLE

    removeFragmentByInstance(fragmentManager, fragmentInstance, newTitle)
}

var pastReqOrientation: Int = 0

/**
 * I have suppressed this because it's a pain in the ass to support orientation changes for Fragments.
 */

@SuppressLint("SourceLockedOrientationActivity")

fun Activity.navigateTo(fragmentManager: FragmentManager, fragmentInstance: Fragment, fragmentInstanceId: Int, newTitle: String, hostView: FrameLayout, rootLayout: View, lockScreenOrientationToPrev: Boolean = true) {
    (rootLayout as ViewGroup).forEach { view -> view.visibility = View.GONE }

    rootLayout.findViewTreeLifecycleOwner()!!.lifecycleScope.launch {
        delay(LongConstants.DefaultHandlerDelay)
        requestedOrientation = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        } else {
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    }

    if (lockScreenOrientationToPrev) {
        val act = hostView.context.activity()
        pastReqOrientation = act?.requestedOrientation ?: ActivityInfo.SCREEN_ORIENTATION_SENSOR
        val curOrientation =  this.resources.configuration.orientation
        act?.requestedOrientation = curOrientation
    }

    hostView.visibility = View.VISIBLE
    (fragmentManager.beginTransaction()).replace(fragmentInstanceId, fragmentInstance).commit()

    title = newTitle
}

private const val defFragmentTransition = FragmentTransaction.TRANSIT_NONE

fun Activity.removeFragmentByInstance(fragmentManager: FragmentManager, fragmentInstance: Fragment, newTitle: String) {
    with(fragmentManager.beginTransaction()) {
        remove(fragmentInstance)
        commit()
        setTransition(defFragmentTransition)
    }

    title = newTitle

    fragmentInstance.activity?.requestedOrientation = pastReqOrientation
}