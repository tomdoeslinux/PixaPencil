package com.realtomjoney.pyxlmoose.activities.canvas

import com.realtomjoney.pyxlmoose.utility.StringConstants

fun CanvasActivity.getExtras() {
    index = intent.getIntExtra(StringConstants.INDEX_EXTRA, -1)
    title = (intent.getStringExtra(StringConstants.PROJECT_TITLE_EXTRA))
    width = intent.getIntExtra(StringConstants.WIDTH_EXTRA, width)
    height = intent.getIntExtra(StringConstants.HEIGHT_EXTRA, width)
    projectTitle = title.toString()
}