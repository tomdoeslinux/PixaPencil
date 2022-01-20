package com.realtomjoney.pyxlmoose.activities.canvas

fun hideMenuItems() {
    for (i in 0 until menu.size()) {
        menu.getItem(i).isVisible = false
    }
}