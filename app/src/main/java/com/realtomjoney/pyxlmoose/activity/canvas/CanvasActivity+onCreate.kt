package com.realtomjoney.pyxlmoose.activity.canvas

import android.view.View
import com.realtomjoney.pyxlmoose.PixelArtDatabase
import com.realtomjoney.pyxlmoose.StringValues

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
}