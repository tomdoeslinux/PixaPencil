package com.realtomjoney.pyxlmoose.activities.canvas

import android.graphics.Color
import android.view.View
import com.realtomjoney.pyxlmoose.converters.JsonConverter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.models.Pixel
import com.realtomjoney.pyxlmoose.models.PixelArt
import com.realtomjoney.pyxlmoose.utility.StringValues
import kotlin.math.sqrt

lateinit var currentPixelArtObj: PixelArt
lateinit var pixelData: List<Pixel>
var pixelDataAsViews = mutableListOf<View>()

fun CanvasActivity.extendedOnCreate() {
    spanCount = intent.getIntExtra(StringValues.SPAN_COUNT_EXTRA, spanCount)
    index = intent.getIntExtra(StringValues.INDEX_EXTRA, -1)

    if (index != -1) {
        AppData.db.pixelArtCreationsDao().getAllPixelArtCreations().observe(context, {
            currentPixelArtObj = it[index!!]

            pixelData = JsonConverter.convertJsonStringToPixelList(currentPixelArtObj.pixelData)
            spanCount = sqrt(pixelData.size.toDouble()).toInt()

            val temp = mutableListOf<View>()

            for (data in pixelData) {
                val view = View(context)

                if (data.pixelColor != null) view.setBackgroundColor(Color.parseColor("#" + Integer.toHexString(data.pixelColor)))
                temp.add(view)
            }
            pixelDataAsViews = temp

            extendedSetUpFragment()
        })

    } else {
        setUpFragment()
    }

    setBindings()
    setUpRecyclerView()
    setOnClickListeners()
    setColours()

    binding.colorPickerFragmentHost.visibility = View.INVISIBLE
    binding.activityCanvasCanvasTitleEditText.setText(intent.getStringExtra("PROJECT_TITLE"))
}