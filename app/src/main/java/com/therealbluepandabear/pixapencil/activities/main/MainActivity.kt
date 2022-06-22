package com.therealbluepandabear.pixapencil.activities.main

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputLayout
import com.takusemba.spotlight.Spotlight
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.adapters.PixelArtCreationsAdapter
import com.therealbluepandabear.pixapencil.converters.BitmapConverter
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.databinding.ActivityMainBinding
import com.therealbluepandabear.pixapencil.extensions.activity
import com.therealbluepandabear.pixapencil.extensions.getNumberOfUniqueColors
import com.therealbluepandabear.pixapencil.extensions.showDialog
import com.therealbluepandabear.pixapencil.listeners.BottomSheetDialogListener
import com.therealbluepandabear.pixapencil.listeners.NewProjectFragmentListener
import com.therealbluepandabear.pixapencil.listeners.RecentCreationsListener
import com.therealbluepandabear.pixapencil.models.PixelArt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), RecentCreationsListener, NewProjectFragmentListener, BottomSheetDialogListener {
    lateinit var sharedPreferenceObject: SharedPreferences
    lateinit var adapter : PixelArtCreationsAdapter

    val pixelArtList = mutableListOf<PixelArt>()

    lateinit var binding: ActivityMainBinding
    lateinit var menu: Menu

    var showLargeCanvasSizeWarning = true
    var firstLaunch = false
    var darkMode = false
    var mainSpotlight: Spotlight? = null

    lateinit var bottomSheet: BottomSheetDialog

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

    override fun onBackPressed() {
        super.onBackPressed()
        findViewById<BottomNavigationView>(R.id.activityMain_bottomNavigationView)?.visibility = View.VISIBLE
    }

    override fun onViewDetailsTapped(pixelArt: PixelArt) {
        val details: ConstraintLayout =
            activity()?.layoutInflater?.inflate(R.layout.project_details_alert_box, activity()?.findViewById(android.R.id.content),false)
                    as ConstraintLayout

        details.findViewById<TextView>(R.id.projectDetailsAlertBox_width).text = pixelArt.width.toString()
        details.findViewById<TextView>(R.id.projectDetailsAlertBox_height).text = pixelArt.height.toString()
        details.findViewById<TextView>(R.id.projectDetailsAlertBox_colors).text = BitmapConverter.convertStringToBitmap(pixelArt.bitmap)?.getNumberOfUniqueColors().toString()
        details.findViewById<TextView>(R.id.projectDetailsAlertBox_created).text = pixelArt.dateCreated

        showDialog(
            getString(R.string.dialog_project_details_title_in_code_str),
            null,
            getString(R.string.generic_ok_in_code_str), { _, _ ->
            }, null, null, view = details, dimBackground = false
        )
    }

    override fun onRenameTapped(pixelArt: PixelArt) {
        val textInput: TextInputLayout =
            activity()?.layoutInflater?.inflate(R.layout.save_file_under_new_name_alert, activity()?.findViewById(android.R.id.content),false)
                    as TextInputLayout

        showDialog(
            getString(R.string.dialog_rename_title_in_code_str),
            null,
            getString(R.string.generic_ok_in_code_str), { _, _ ->
                val input: String = textInput.editText?.text.toString()

                CoroutineScope(Dispatchers.IO).launch {
                    if (input.isNotBlank()) {
                        pixelArt.title = input
                        AppData.pixelArtDB.pixelArtCreationsDao().updatePixelArtCreation(pixelArt)
                        bottomSheet.dismiss()
                    }
                }
           }, getString(R.string.generic_cancel_in_code_str), null, view = textInput, dimBackground = false
        )
    }

    override fun onDeleteTapped(pixelArt: PixelArt) {
        val title = pixelArt.title

        showDialog(
            getString(R.string.dialog_delete_pixel_art_project_title_in_code_str, title),
            getString(R.string.dialog_delete_pixel_art_project_text_in_code_str, title),
            getString(R.string.generic_ok_in_code_str), { _, _ ->
                bottomSheet.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    AppData.pixelArtDB.pixelArtCreationsDao().deletePixelArtCreation(pixelArt)
                }
            },  getString(R.string.generic_cancel_in_code_str), null, dimBackground = false
        )
    }
}

