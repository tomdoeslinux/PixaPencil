package com.therealbluepandabear.pixapencil.activities.main.oncreate

import com.therealbluepandabear.pixapencil.activities.main.MainActivity

fun MainActivity.observePixelArtData() {
    pixelArtViewModel.getAll().observe(this) {
        adapter.submitList(it)
        pixelArtData = it
    }
}
 