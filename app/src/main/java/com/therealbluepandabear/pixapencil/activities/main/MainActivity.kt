package com.therealbluepandabear.pixapencil.activities.main

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.takusemba.spotlight.Spotlight
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.main.bottomsheet.extendedOnDeleteTapped
import com.therealbluepandabear.pixapencil.activities.main.bottomsheet.extendedOnDuplicateTapped
import com.therealbluepandabear.pixapencil.activities.main.bottomsheet.extendedOnRenameTapped
import com.therealbluepandabear.pixapencil.activities.main.bottomsheet.extendedOnViewDetailsTapped
import com.therealbluepandabear.pixapencil.activities.main.oncreate.root.extendedOnCreate
import com.therealbluepandabear.pixapencil.activities.main.viewmodel.PixelArtViewModel
import com.therealbluepandabear.pixapencil.adapters.PixelArtAdapter
import com.therealbluepandabear.pixapencil.databinding.ActivityMainBinding
import com.therealbluepandabear.pixapencil.listeners.BottomSheetDialogListener
import com.therealbluepandabear.pixapencil.listeners.NewProjectFragmentListener
import com.therealbluepandabear.pixapencil.listeners.RecentCreationsListener
import com.therealbluepandabear.pixapencil.models.PixelArt


class MainActivity : AppCompatActivity(), RecentCreationsListener, NewProjectFragmentListener, BottomSheetDialogListener {
    lateinit var sharedPreferenceObject: SharedPreferences
    lateinit var adapter: PixelArtAdapter

    lateinit var binding: ActivityMainBinding
    lateinit var menu: Menu

    var showLargeCanvasSizeWarning = true
    var firstLaunch = false
    var darkMode = false
    var mainSpotlight: Spotlight? = null

    val pixelArtViewModel: PixelArtViewModel by viewModels()
    lateinit var pixelArtData: List<PixelArt>

    public override fun onCreate(savedInstanceState: Bundle?) {
        initSharedPreferencesObject()
        toggleDarkModeIfApplicable()
        super.onCreate(savedInstanceState)
        extendedOnCreate()
    }

    override fun onCreationTapped(creationTapped: PixelArt) {
        extendedOnCreationTapped(creationTapped)
    }

    override fun onCreationLongTapped(creationTapped: PixelArt) {
        extendedOnCreationLongTapped(creationTapped)
    }

    override fun onStarredTapped(pixelArt: PixelArt) {
        pixelArtViewModel.update(pixelArt)
    }

    override fun onUnstarredTapped(pixelArt: PixelArt) {
        pixelArtViewModel.update(pixelArt)
    }

    override fun onDoneButtonPressed(projectName: String, width: Int, height: Int, spotLightInProgress: Boolean) {
        extendedOnDoneButtonPressed(projectName, width, height, spotLightInProgress)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        findViewById<BottomNavigationView>(R.id.activityMain_bottomNavigationView)?.visibility = View.VISIBLE
    }

    override fun onDuplicateTapped(pixelArt: PixelArt, bottomSheetDialog: BottomSheetDialog) {
        extendedOnDuplicateTapped(pixelArt, bottomSheetDialog)
    }

    override fun onViewDetailsTapped(pixelArt: PixelArt) {
        extendedOnViewDetailsTapped(pixelArt)
    }

    override fun onRenameTapped(pixelArt: PixelArt, bottomSheetDialog: BottomSheetDialog) {
        extendedOnRenameTapped(pixelArt, bottomSheetDialog)
    }

    override fun onDeleteTapped(pixelArt: PixelArt, bottomSheetDialog: BottomSheetDialog) {
        extendedOnDeleteTapped(pixelArt, bottomSheetDialog)
    }
}

