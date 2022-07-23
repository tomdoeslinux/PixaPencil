package com.therealbluepandabear.pixapencil.activities.main.oncreate

import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import com.therealbluepandabear.pixapencil.activities.canvas.CanvasActivity
import com.therealbluepandabear.pixapencil.activities.main.MainActivity
import com.therealbluepandabear.pixapencil.utility.constants.StringConstants

fun MainActivity.initActivityResultLauncher() {
    galleryActivityLauncher = registerForActivityResult(ActivityResultContracts.OpenDocument()) {
        if (it != null) {
            startActivity(
                Intent(this, CanvasActivity::class.java)
                    .putExtra(StringConstants.Extras.PROJECT_TITLE_EXTRA, "Null")
                    .putExtra(StringConstants.Extras.SPOTLIGHT_IN_PROGRESS_EXTRA, false)
                    .putExtra(StringConstants.Extras.BITMAP_URI_EXTRA, it.toString())
            )
        }
    }
}