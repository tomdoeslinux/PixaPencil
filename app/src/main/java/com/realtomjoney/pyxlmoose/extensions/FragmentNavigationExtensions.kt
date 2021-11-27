package com.realtomjoney.pyxlmoose.extensions

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun Activity.navigateHome(fragmentManager: FragmentManager, fragmentInstance: Fragment, rootLayout: View, fragmentHost: FrameLayout, newTitle: String) {
    (rootLayout as ViewGroup).doSomethingWithChildElements { view ->
        view.visibility = View.VISIBLE
    }
    fragmentHost.visibility = View.GONE
    removeFragmentByInstance(fragmentManager, fragmentInstance, newTitle)
}

fun Activity.navigateTo(fragmentManager: FragmentManager, fragmentInstance: Fragment, fragmentInstanceId: Int, newTitle: String, hostView: FrameLayout, rootLayout: View) {
    (rootLayout as ViewGroup).doSomethingWithChildElements { view ->
        view.visibility = View.GONE
    }

    hostView.visibility = View.VISIBLE
    (fragmentManager.beginTransaction()).replace(fragmentInstanceId, fragmentInstance).commit()

    title = newTitle
}

fun Activity.removeFragmentByInstance(fragmentManager: FragmentManager, fragmentInstance: Fragment, newTitle: String) {
    with(fragmentManager.beginTransaction()) {
        remove(fragmentInstance)
        commit()
        setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
    }

    title = newTitle
}