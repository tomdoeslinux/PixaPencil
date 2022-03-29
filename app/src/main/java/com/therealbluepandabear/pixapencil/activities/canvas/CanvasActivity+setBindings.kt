package com.therealbluepandabear.pixapencil.activities.canvas

import com.therealbluepandabear.pixapencil.databinding.ActivityCanvasBinding


fun CanvasActivity.setBindings() {
    binding = ActivityCanvasBinding.inflate(layoutInflater)
    setContentView(binding.root)
}