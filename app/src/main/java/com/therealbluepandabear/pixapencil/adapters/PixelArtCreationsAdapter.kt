package com.therealbluepandabear.pixapencil.adapters

import android.graphics.Bitmap
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.databinding.RecentCreationsLayoutBinding
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.showSnackbar
import com.therealbluepandabear.pixapencil.listeners.RecentCreationsListener
import com.therealbluepandabear.pixapencil.models.PixelArt
import com.therealbluepandabear.pixapencil.viewholders.ViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File


class PixelArtCreationsAdapter(
    private val snackbarView: View,
    private val data: List<PixelArt>,
    private val listener: RecentCreationsListener
) : RecyclerView.Adapter<ViewHolder<FrameLayout>>() {
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
                val requestOptions: RequestOptions = RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .dontAnimate()
                    .priority(Priority.IMMEDIATE)
                    .encodeFormat(Bitmap.CompressFormat.PNG)
                    .override(750, 750)
                    .centerInside()
                    .format(DecodeFormat.DEFAULT)

                Glide.with(context)
                    .setDefaultRequestOptions(requestOptions)
                    .load(File(context.getFileStreamPath(item.coverBitmapFilePath).absolutePath))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.transparent_placeholder)
                    .into(recentCreationsLayoutImageView)

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
                        unFavouriteRecentCreation(snackbarView, item)
                        item.starred = false

                        CoroutineScope(Dispatchers.IO).launch {
                            AppData.pixelArtDB.pixelArtCreationsDao().updatePixelArtCreation(item)
                        }
                    } else {
                        favouriteRecentCreation(snackbarView, item)
                        item.starred = true

                        CoroutineScope(Dispatchers.IO).launch {
                            AppData.pixelArtDB.pixelArtCreationsDao().updatePixelArtCreation(item)
                        }
                    }
                    changeStarredIndicator((it as ImageButton), item)
                }
            }
        }
    }

    private fun changeStarredIndicator(imageButton: ImageButton, pixelArt: PixelArt) {
        imageButton.apply {
            if (pixelArt.starred) setImageResource(R.drawable.ic_baseline_star_24)
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