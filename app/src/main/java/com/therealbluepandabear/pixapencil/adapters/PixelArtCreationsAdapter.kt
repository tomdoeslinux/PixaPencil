package com.therealbluepandabear.pixapencil.adapters

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.databinding.RecentCreationsLayoutBinding
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.showSnackbar
import com.therealbluepandabear.pixapencil.listeners.RecentCreationsListener
import com.therealbluepandabear.pixapencil.models.PixelArt
import com.therealbluepandabear.pixapencil.utility.FileHelperUtilities
import com.therealbluepandabear.pixapencil.viewholders.ViewHolder

class PixelArtCreationsAdapter(private val data: List<PixelArt>, private val listener: RecentCreationsListener) : RecyclerView.Adapter<ViewHolder<FrameLayout>>() {
    private lateinit var binding: RecentCreationsLayoutBinding

    var userHasLongPressed = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<FrameLayout> {
        binding = RecentCreationsLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding.recentCreationsLayoutRootLayout)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: ViewHolder<FrameLayout>, position: Int) = data.forEach { _ ->
        binding.recentCreationsLayoutMaterialCardView.apply parent@{
            val item = data[position]

            binding.apply {
                val fileHelperUtilities = FileHelperUtilities.createInstanceFromContext(context)

                val bitmap = fileHelperUtilities.getBitmapFromInternalStorage(item.coverBitmapFilePath)

                recentCreationsLayoutImageView.setImageBitmap(bitmap)
                recentCreationsLayoutImageView.invalidate()

                recentCreationsLayoutSubtitle.text = "${item.width}x${item.height}"

                recentCreationsLayoutTitle.apply {
                    if (data[position].title.length > 6) {
                        ellipsize = TextUtils.TruncateAt.MARQUEE
                        isSelected = true
                        isSingleLine = true
                        (item.title + " ".repeat(10)).repeat(200).also { text = it }
                    } else {
                        text = item.title
                    }
                }

                this@parent.setOnClickListener {
                    if (!userHasLongPressed) listener.onCreationTapped(item)
                }

                this@parent.setOnLongClickListener {
                    listener.onCreationLongTapped(item)
                    true
                }

                changeStarredIndicator(recentCreationsLayoutFavoriteButton, item)

                recentCreationsLayoutFavoriteButton.setOnClickListener {
                    if (item.starred) {
                        unFavouriteRecentCreation(this@parent, item)
                        AppData.pixelArtDB.pixelArtCreationsDao().updatePixelArtCreationStarred(false, item.objId)
                    } else {
                        favouriteRecentCreation(this@parent, item)
                        AppData.pixelArtDB.pixelArtCreationsDao().updatePixelArtCreationStarred(true, item.objId)
                    }
                    changeStarredIndicator((it as ImageButton), item)
                }
            }
        }
    }

    private fun changeStarredIndicator(imageButton: ImageButton, pixelArt: PixelArt) {
        imageButton.apply {
            if (pixelArt.starred) setImageResource(R.drawable.ic_baseline_star_24)
//            else setImageResource(R.drawable.ic_baseline_star_border_24)
        }
    }
    private fun favouriteRecentCreation(contextView: View, pixelArt: PixelArt) {
        contextView.showSnackbar(contextView.context.getString(R.string.snackbar_pixel_art_project_saved_to_starred_items_in_code_str, pixelArt.title), SnackbarDuration.Default)
        pixelArt.starred = true
    }

    private fun unFavouriteRecentCreation(contextView: View, pixelArt: PixelArt) {
        contextView.showSnackbar(contextView.context.getString(R.string.snackbar_pixel_art_project_removed_from_starred_items_in_code_str, pixelArt.title), SnackbarDuration.Default)
        pixelArt.starred = false
    }

    override fun getItemCount(): Int {
        return data.size
    }
}