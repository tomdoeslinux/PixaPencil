package com.therealbluepandabear.pixapencil.activities.main.oncreate

import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.databinding.ActivityMainBinding

fun MainActivity.setBindings() {
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
}