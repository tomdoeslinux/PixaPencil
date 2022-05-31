package com.therealbluepandabear.pixapencil.activities.canvas

import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import com.takusemba.spotlight.OnTargetListener
import com.takusemba.spotlight.Spotlight
import com.takusemba.spotlight.Target
import com.takusemba.spotlight.shape.Circle
import com.takusemba.spotlight.shape.RoundedRectangle
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.activities.canvas.onactionup.extendedOnActionUp
import com.therealbluepandabear.pixapencil.activities.canvas.oncreate.onCreate
import com.therealbluepandabear.pixapencil.activities.canvas.ondonebuttonpressed.extendedOnDoneButtonPressed
import com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected.extendedOnCreateOptionsMenu
import com.therealbluepandabear.pixapencil.activities.canvas.onoptionsitemselected.extendedOnOptionsItemSelected
import com.therealbluepandabear.pixapencil.activities.canvas.onpixeltapped.extendedOnPixelTapped
import com.therealbluepandabear.pixapencil.activities.canvas.ontapped.*
import com.therealbluepandabear.pixapencil.activities.canvas.viewmodel.CanvasActivityViewModel
import com.therealbluepandabear.pixapencil.adapters.ColorPaletteColorPickerAdapter
import com.therealbluepandabear.pixapencil.algorithms.AlgorithmInfoParameter
import com.therealbluepandabear.pixapencil.algorithms.SprayAlgorithm
import com.therealbluepandabear.pixapencil.databinding.ActivityCanvasBinding
import com.therealbluepandabear.pixapencil.enums.Tool
import com.therealbluepandabear.pixapencil.fragments.brushes.BrushesFragment
import com.therealbluepandabear.pixapencil.fragments.colorpalettes.ColorPalettesFragment
import com.therealbluepandabear.pixapencil.fragments.filters.FiltersFragment
import com.therealbluepandabear.pixapencil.fragments.outercanvas.OuterCanvasFragment
import com.therealbluepandabear.pixapencil.fragments.tools.ToolsFragment
import com.therealbluepandabear.pixapencil.listeners.*
import com.therealbluepandabear.pixapencil.models.BitmapAction
import com.therealbluepandabear.pixapencil.models.Brush
import com.therealbluepandabear.pixapencil.models.ColorPalette
import com.therealbluepandabear.pixapencil.models.Coordinates
import com.therealbluepandabear.pixapencil.utility.constants.IntConstants
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

lateinit var binding: ActivityCanvasBinding

class CanvasActivity :
    AppCompatActivity(),
    CanvasFragmentListener,
    ColorPaletteColorPickerListener,
    ColorPickerFragmentListener,
    FindAndReplaceFragmentListener,
    ToolsFragmentListener,
    FiltersFragmentListener,
    ColorPalettesFragmentListener,
    NewColorPaletteFragmentListener,
    BrushesFragmentListener,
    SprayToolSettingsFragmentListener {

    var previousView: View? = null
    var projectTitle: String? = null

    lateinit var outerCanvasInstance: OuterCanvasFragment

    val viewModel: CanvasActivityViewModel by viewModels()

    var index: Int? = null

    var primaryColor: Int = Color.BLACK
    var secondaryColor: Int = Color.BLUE

    var width = IntConstants.DefaultCanvasWidthHeight
    var height = IntConstants.DefaultCanvasWidthHeight

    var isPrimaryColorSelected = true

    var isSelected = false
    var background: Drawable? = null

    var currentTool: Tool = Tool.defaultTool

    var saved = true

    lateinit var menu: Menu

    var toolsFragmentInstance: ToolsFragment? = null
    var filtersFragmentInstance: FiltersFragment? = null
    var colorPalettesFragmentInstance: ColorPalettesFragment? = null
    var brushesFragmentInstance: BrushesFragment? = null

    var lineModeHasLetGo = false
    var rectangleModeHasLetGo = false
    var circleModeHasLetGo = false

    lateinit var sharedPreferenceObject: SharedPreferences

    lateinit var sprayAlgorithmInstance: SprayAlgorithm
    var sprayAlgorithmInstanceInitialized = ::sprayAlgorithmInstance.isInitialized

    var prevOrientation: Int = 0
    var prevBitmapFilePathStr: String? = null
    var prevPrimaryColor: Int? = null
    var prevSecondaryColor: Int? = null
    var prevToolStr: String? = null
    var prevBrushStr: Int = 0
    var prevTab: Int = 0
    var prevUndoToolbarButtonDisabledEnabledState: Boolean = false // false means it's disabled
    var prevRedoToolbarButtonDisabledEnabledState: Boolean = false
    var prevSymmetryModeStr: String? = null
    var prevRotation: Int = 0

    var currentTab = 0

    lateinit var primaryAlgorithmInfoParameter: AlgorithmInfoParameter
    val primaryAlgorithmInfoParameterInitialized = ::primaryAlgorithmInfoParameter.isInitialized

    var selectedColorPaletteIndex: Int = 0

    var shadingToolMode = StringConstants.ShadingToolModes.LightenShadingToolMode

    var sharedPreferenceShowSprayToolTip = true
    var sharedPreferenceShowShadingToolTip = true

    inner class CanvasCommandsHelper(val baseReference: CanvasActivity = this@CanvasActivity)

    val canvasCommandsHelperInstance: CanvasCommandsHelper = CanvasCommandsHelper()

    val colorPaletteColorPickerData = mutableListOf<Int>()
    lateinit var adapter: ColorPaletteColorPickerAdapter
    val adapterInitialized = ::adapter.isInitialized

    var spotLightInProgress: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreate()
        configureSavedInstanceState(savedInstanceState)

        if (spotLightInProgress) {
            binding.activityCanvasColorPrimaryView.doOnPreDraw {
                startSpotLight()
            }
        }
    }

    private fun startSpotLight() {
        val firstRoot = FrameLayout(this)
        val lyt = layoutInflater.inflate(R.layout.layout_target, firstRoot)
        val text = lyt.findViewById<TextView>(R.id.layoutTarget_text)
        val targets = ArrayList<Target>()

        val firstTarget = Target.Builder()
            .setAnchor(binding.activityCanvasColorPrimaryView)
            .setOnTargetListener(object : OnTargetListener {
                override fun onEnded() {}

                override fun onStarted() {
                    text.text = "Long tap on either your primary or secondary color to get to the color picker"
                }

            })
            .setShape(Circle(300f))
            .setOverlay(lyt)
            .build()

        targets.add(firstTarget)

        val secondTarget = Target.Builder()
            .setAnchor(binding.activityCanvasTabLayoutFragmentHost)
            .setOnTargetListener(object : OnTargetListener {
                override fun onEnded() {}

                override fun onStarted() {
                    lyt.y -= 400
                    text.text = "Here you will find your tools"
                }

            })
            .setShape(RoundedRectangle(binding.activityCanvasTabLayoutFragmentHost.measuredHeight.toFloat(), binding.activityCanvasTabLayoutFragmentHost.measuredWidth.toFloat(), 20f))
            .setOverlay(lyt)
            .build()

        targets.add(secondTarget)

        val thirdTarget = Target.Builder()
            .setAnchor(binding.activityCanvasTabLayoutFragmentHost)
            .setOnTargetListener(object : OnTargetListener {
                override fun onEnded() {}

                override fun onStarted() {
                    lyt.y -= 200
                    text.text = "Tap on 'Filters' to try out some project filters"
                }

            })
            .setShape(RoundedRectangle(binding.activityCanvasTabLayoutFragmentHost.measuredHeight.toFloat() + 300, binding.activityCanvasTabLayoutFragmentHost.measuredWidth.toFloat(), 20f))
            .setOverlay(lyt)
            .build()

        targets.add(thirdTarget)

        val fourthTarget = Target.Builder()
            .setAnchor(binding.activityCanvasTabLayoutFragmentHost)
            .setOnTargetListener(object : OnTargetListener {
                override fun onEnded() {}

                override fun onStarted() {
                    lyt.y += 200
                    text.text = "Here you can try different canvas filters"
                }

            })
            .setShape(RoundedRectangle(binding.activityCanvasTabLayoutFragmentHost.measuredHeight.toFloat(), binding.activityCanvasTabLayoutFragmentHost.measuredWidth.toFloat(), 20f))
            .setOverlay(lyt)
            .build()

        targets.add(fourthTarget)

        val fifthTarget = Target.Builder()
            .setAnchor(binding.activityCanvasTabLayoutFragmentHost)
            .setOnTargetListener(object : OnTargetListener {
                override fun onEnded() {}

                override fun onStarted() {
                    lyt.y -= 200
                    text.text = "Tap on 'Palettes' to view your palettes"
                }

            })
            .setShape(RoundedRectangle(binding.activityCanvasTabLayoutFragmentHost.measuredHeight.toFloat() + 300, binding.activityCanvasTabLayoutFragmentHost.measuredWidth.toFloat(), 20f))
            .setOverlay(lyt)
            .build()

        targets.add(fifthTarget)

        val sixthTarget = Target.Builder()
            .setAnchor(binding.activityCanvasTabLayoutFragmentHost)
            .setOnTargetListener(object : OnTargetListener {
                override fun onEnded() {}

                override fun onStarted() {
                    lyt.y += 200
                    text.text = "You only have the default palette, but you can always create a new one by tapping the three dots at the top right of your screen"
                }

            })
            .setShape(RoundedRectangle(binding.activityCanvasTabLayoutFragmentHost.measuredHeight.toFloat(), binding.activityCanvasTabLayoutFragmentHost.measuredWidth.toFloat(), 20f))
            .setOverlay(lyt)
            .build()

        targets.add(sixthTarget)

        val seventhTarget = Target.Builder()
            .setAnchor(binding.activityCanvasTabLayoutFragmentHost)
            .setOnTargetListener(object : OnTargetListener {
                override fun onEnded() {}

                override fun onStarted() {
                    lyt.y -= 200
                    text.text = "Tap on 'Brushes' to view your brushes"
                }

            })
            .setShape(RoundedRectangle(binding.activityCanvasTabLayoutFragmentHost.measuredHeight.toFloat() + 300, binding.activityCanvasTabLayoutFragmentHost.measuredWidth.toFloat(), 20f))
            .setOverlay(lyt)
            .build()

        targets.add(seventhTarget)

        val eighthTarget = Target.Builder()
            .setAnchor(binding.activityCanvasTabLayoutFragmentHost)
            .setOnTargetListener(object : OnTargetListener {
                override fun onEnded() {}

                override fun onStarted() {
                    lyt.y += 200
                    text.text = "PixaPencil has five brushes you can try out"
                }

            })
            .setShape(RoundedRectangle(binding.activityCanvasTabLayoutFragmentHost.measuredHeight.toFloat(), binding.activityCanvasTabLayoutFragmentHost.measuredWidth.toFloat(), 20f))
            .setOverlay(lyt)
            .build()

        targets.add(eighthTarget)

        val spotlight = Spotlight.Builder(this)
            .setTargets(targets)
            .setBackgroundColorRes(R.color.spotlightBackground)
            .setDuration(1000L)
            .setAnimation(DecelerateInterpolator(2f))
            .build()

        lyt.findViewById<Button>(R.id.layoutTarget_nextButton).setOnClickListener {
            spotlight.next()
        }

        lyt.findViewById<Button>(R.id.layoutTarget_closeButton).setOnClickListener {
            spotlight.finish()
        }

        spotlight.start()
    }

    var replacedBMP = false

    override fun onStart() {
        super.onStart()
        savePrevOrientationInfo()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return extendedOnCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return extendedOnOptionsItemSelected(item)
    }

    override fun onPause() {
        extendedOnPause()
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        extendedOnSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onViewLoaded() {
        extendedOnViewLoaded()
    }

    override fun onPixelTapped(coordinatesTapped: Coordinates) {
        extendedOnPixelTapped(coordinatesTapped)
    }

    override fun onActionUp() {
        extendedOnActionUp()
    }

    override fun dispatchTouchEvent() {
        if (viewModel.currentBitmapAction == null) {
            viewModel.currentBitmapAction = BitmapAction(mutableListOf())
        }
    }

    override fun onColorTapped(colorTapped: Int, view: View) {
        extendedOnColorTapped(colorTapped, view)
    }

    override fun onColorLongTapped(colorPalette: ColorPalette, colorIndex: Int) {
        extendedOnColorLongTapped(colorPalette, colorIndex)
    }

    override fun onColorAdded(colorPalette: ColorPalette) {
        extendedOnAddColorTapped(colorPalette)
    }

    override fun onDoneButtonPressed(selectedColor: Int, colorPalette: ColorPalette?) {
        extendedOnDoneButtonPressed(selectedColor, colorPalette)
    }

    override fun onDoneButtonPressed(colorToFind: Int?, colorToReplace: Int?) {
        extendedOnDoneButtonPressed(colorToFind, colorToReplace)
    }

    override fun onBackPressed() {
        extendedOnBackPressed()
    }

    override fun onToolTapped(toolName: String) {
        extendedOnToolTapped(toolName)
    }

    override fun onToolLongTapped(toolName: String) {
        extendedOnToolLongTapped(toolName)
    }

    override fun onFilterTapped(filterName: String) {
        extendedOnFilterTapped(filterName)
    }

    override fun onColorPaletteTapped(selectedColorPalette: ColorPalette) {
        extendedOnColorPaletteTapped(selectedColorPalette)
    }

    override fun onColorPaletteLongTapped(selectedColorPalette: ColorPalette) {
        extendedOnColorPaletteLongTapped(selectedColorPalette)
    }

    override fun onDoneButtonPressed(colorPaletteTitle: String, extractColorPaletteFromCanvas: Boolean) {
        extendedOnDoneButtonPressed(colorPaletteTitle, extractColorPaletteFromCanvas)
    }

    override fun onBrushTapped(selectedBrush: Brush) {
        extendedOnBrushTapped(selectedBrush)
    }

    override fun onDoneButtonPressed(radius: String, strength: String) {
        extendedOnDoneButtonPressed(radius, strength)
    }
}
