package com.therealbluepandabear.pixapencil.activities.main

import androidx.fragment.app.commit
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.fragments.newproject.NewProjectFragment

fun MainActivity.newProjectButtonSetOnClickListener() {
    binding.activityMainNewProjectButton.setOnClickListener {
        if (supportFragmentManager.backStackEntryCount == 0) {
            supportFragmentManager.commit {
                replace(R.id.activityMain_primaryFragmentHost, NewProjectFragment.newInstance())
                addToBackStack(null)
            }
        }
    }
}