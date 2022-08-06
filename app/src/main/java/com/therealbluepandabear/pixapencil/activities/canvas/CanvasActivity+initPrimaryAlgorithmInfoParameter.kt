package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.algorithms.*
import com.therealbluepandabear.pixapencil.utility.constants.ObjectConstants

fun CanvasActivity.initPrimaryAlgorithmInfoParameter() {
    primaryAlgorithmInfoParameter = AlgorithmInfoParameter.create(
        canvasCommandsHelperInstance,
        binding.activityCanvasPixelGridView.pixelGridViewBitmap,
        viewModel.currentBitmapAction!!,
        getSelectedColor()
    )

    ObjectConstants.PrimaryAlgorithmInfoParameter = primaryAlgorithmInfoParameter

    lineAlgorithm = LineAlgorithm(primaryAlgorithmInfoParameter)
    circleAlgorithm = CircleAlgorithm(primaryAlgorithmInfoParameter)
    filledCircleAlgorithm = CircleAlgorithm(primaryAlgorithmInfoParameter, filledMode = true)
    ellipseAlgorithm = EllipseAlgorithm(primaryAlgorithmInfoParameter)
    filledEllipseAlgorithm = EllipseAlgorithm(primaryAlgorithmInfoParameter, filledMode = true)
    rectangleAlgorithm = RectangleAlgorithm(primaryAlgorithmInfoParameter)
    visibleRectanglePreviewAlgorithm = RectanglePreviewAlgorithm(primaryAlgorithmInfoParameter, false)
    visibleSquarePreviewAlgorithm = SquarePreviewAlgorithm(primaryAlgorithmInfoParameter, invisibleMode = false)
    invisibleRectanglePreviewAlgorithm = RectanglePreviewAlgorithm(primaryAlgorithmInfoParameter, invisibleMode = true)
    filledRectangleAlgorithm = RectangleAlgorithm(primaryAlgorithmInfoParameter)
    invisibleSquarePreviewAlgorithm = SquarePreviewAlgorithm(primaryAlgorithmInfoParameter, invisibleMode = true)
}