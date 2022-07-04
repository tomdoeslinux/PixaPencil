package com.therealbluepandabear.pixapencil.activities.main

fun MainActivity.observePixelArtData() {
    pixelArtViewModel.getAll().observe(this) {
        adapter.submitList(it)
        pixelArtData = it
    }
}
 