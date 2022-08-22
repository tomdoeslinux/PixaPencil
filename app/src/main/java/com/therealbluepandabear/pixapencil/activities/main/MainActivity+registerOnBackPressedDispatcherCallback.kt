package com.therealbluepandabear.pixapencil.activities.main

import android.view.View
import androidx.activity.OnBackPressedCallback

fun MainActivity.registerOnBackPressedDispatcherCallback() {
    onBackPressedDispatcher.addCallback(
        this,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // If we remove this, when the user would press the back button the bottom navigation view will disappear
                binding.activityMainBottomNavigationView.visibility = View.VISIBLE

                if (supportFragmentManager.backStackEntryCount > 0) {
                    supportFragmentManager.popBackStackImmediate()
                }
            }
        })
}