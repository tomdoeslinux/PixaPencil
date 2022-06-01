package com.therealbluepandabear.pixapencil.activities.main

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.lifecycleScope
import com.takusemba.spotlight.Spotlight
import com.therealbluepandabear.pixapencil.adapters.PixelArtCreationsAdapter
import com.therealbluepandabear.pixapencil.databinding.ActivityMainBinding
import com.therealbluepandabear.pixapencil.listeners.NewProjectFragmentListener
import com.therealbluepandabear.pixapencil.listeners.RecentCreationsListener
import com.therealbluepandabear.pixapencil.models.PixelArt
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), RecentCreationsListener, NewProjectFragmentListener {
    lateinit var sharedPreferenceObject: SharedPreferences
    lateinit var adapter : PixelArtCreationsAdapter

    val pixelArtList = mutableListOf<PixelArt>()

    lateinit var binding: ActivityMainBinding
    lateinit var menu: Menu

    var showLargeCanvasSizeWarning = true
    var firstLaunch = false
    var darkMode = false
    var mainSpotlight: Spotlight? = null

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return extendedOnCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return extendedOnOptionsItemSelected(item)
    }

    override fun onDoneButtonPressed(projectName: String, width: Int, height: Int, spotLightInProgress: Boolean) {
        extendedOnDoneButtonPressed(projectName, width, height, spotLightInProgress)
    }
}

