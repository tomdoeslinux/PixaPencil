package com.therealbluepandabear.pixapencil.activities.main.eventlisteners

import androidx.fragment.app.commit
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.fragments.newproject.NewProjectFragment

fun MainActivity.newProjectButtonSetOnClickListener() {
    binding.activityMainNewProjectButton.setOnClickListener {
        mainSpotlight?.finish()

        if (supportFragmentManager.backStackEntryCount == 0) {
            supportFragmentManager.commit {
                replace(R.id.activityMain_primaryFragmentHost, NewProjectFragment.newInstance(mainSpotlight != null))
                addToBackStack(null)
            }
        }
    }
}