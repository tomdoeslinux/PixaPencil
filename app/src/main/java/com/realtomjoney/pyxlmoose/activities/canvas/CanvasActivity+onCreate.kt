package com.realtomjoney.pyxlmoose.activities.canvas

import android.view.View
import com.realtomjoney.pyxlmoose.models.PixelArt
import com.realtomjoney.pyxlmoose.utility.StringConstants

lateinit var currentPixelArtObj: PixelArt

fun CanvasActivity.extendedOnCreate() {
    spanCount = intent.getIntExtra(StringConstants.SPAN_COUNT_EXTRA, spanCount)
    index = intent.getIntExtra(StringConstants.INDEX_EXTRA, -1)

    setUpFragment()
    setBindings()
    setUpRecyclerView()
    setOnClickListeners()
    setColors()

    binding.activityCanvasCanvasTitleEditText.setText(intent.getStringExtra("PROJECT_TITLE"))
}