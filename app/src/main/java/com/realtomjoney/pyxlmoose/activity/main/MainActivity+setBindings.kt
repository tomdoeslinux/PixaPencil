package com.realtomjoney.pyxlmoose.activity.main

import com.realtomjoney.pyxlmoose.databinding.ActivityMainBinding

fun MainActivity.extendedSetBindings() {
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
}