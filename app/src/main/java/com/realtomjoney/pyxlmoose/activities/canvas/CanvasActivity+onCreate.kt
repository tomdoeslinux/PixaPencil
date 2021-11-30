package com.realtomjoney.pyxlmoose.activities.canvas

import android.view.View
import com.realtomjoney.pyxlmoose.database.PixelArtDatabase
import com.realtomjoney.pyxlmoose.utility.StringValues

fun CanvasActivity.extendedOnCreate() {
    spanCount = intent.getIntExtra(StringValues.SPAN_COUNT_EXTRA, spanCount)
    index = intent.getIntExtra(StringValues.INDEX_EXTRA, -1)

    if (index != -1) data = PixelArtDatabase.toList()[index!!].pixelData

    setBindings()
    setUpFragment()
    setUpRecyclerView()
    setOnClickListeners()
    setColours()

    binding.colorPickerFragmentHost.visibility = View.INVISIBLE
    binding.activityCanvasCanvasTitleEditText.setText(intent.getStringExtra("PROJECT_TITLE"))
}