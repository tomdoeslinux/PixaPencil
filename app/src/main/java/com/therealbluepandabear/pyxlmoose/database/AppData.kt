package com.therealbluepandabear.pyxlmoose.database

class AppData {
    companion object {
        var pixelArtDBFileName = "pixel_art_db"
        lateinit var pixelArtDB: PixelArtDatabase

        var colorPalettesDBFileName = "color_palettes_db"
        lateinit var colorPalettesDB: ColorPalettesDatabase
    }
}
