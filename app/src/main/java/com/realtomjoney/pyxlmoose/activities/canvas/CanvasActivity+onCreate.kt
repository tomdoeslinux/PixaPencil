package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.utility.StringConstants

fun CanvasActivity.extendedOnCreate() {
    spanCount = intent.getIntExtra(StringConstants.SPAN_COUNT_EXTRA, spanCount)
    index = intent.getIntExtra(StringConstants.INDEX_EXTRA, -1)

    setUpFragment()
    setBindings()
    setUpRecyclerView()
    setOnClickListeners()
    setColors()

    title = (intent.getStringExtra("PROJECT_TITLE"))
}