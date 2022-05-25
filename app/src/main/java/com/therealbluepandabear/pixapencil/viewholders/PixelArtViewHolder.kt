package com.therealbluepandabear.pixapencil.viewholders

import android.content.Context
import android.graphics.Bitmap
import android.text.TextUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.databinding.RecentCreationsLayoutBinding
import com.therealbluepandabear.pixapencil.models.PixelArt
import java.io.File


class PixelArtViewHolder(val binding: RecentCreationsLayoutBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {
    private fun loadPixelArtCoverImage(pixelArt: PixelArt) {
        val requestOptions: RequestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .dontAnimate()
            .priority(Priority.IMMEDIATE)
            .encodeFormat(Bitmap.CompressFormat.PNG)
            .override(750, 750)
            .centerInside()
            .format(DecodeFormat.DEFAULT)

        Glide.with(itemView.context)
            .setDefaultRequestOptions(requestOptions)
            .load(File(itemView.context.getFileStreamPath(pixelArt.coverBitmapFilePath).absolutePath))
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.transparent_placeholder)
            .into(binding.recentCreationsLayoutImageView)
    }

    private fun loadPixelArtTitle(pixelArt: PixelArt) {
        if (pixelArt.title.length > 6) {
            binding.recentCreationsLayoutTitle.ellipsize = TextUtils.TruncateAt.MARQUEE
            binding.recentCreationsLayoutTitle.isSelected = true
            binding.recentCreationsLayoutTitle.isSingleLine = true
            (pixelArt.title + " ".repeat(10)).repeat(200).also {  binding.recentCreationsLayoutTitle.text = it }
        } else {
            binding.recentCreationsLayoutTitle.text = pixelArt.title
        }
    }

    private fun loadPixelArtStarred(pixelArt: PixelArt) {
        binding.recentCreationsLayoutFavoriteButton.setImageResource(
            if (pixelArt.starred) {
                R.drawable.ic_baseline_star_24
            } else {
                R.drawable.ic_baseline_star_border_24
            }
        )
    }

    fun bind(pixelArt: PixelArt){
        loadPixelArtCoverImage(pixelArt)
        binding.recentCreationsLayoutSubtitle.text = context.getString(R.string.recentCreationsLayoutSubtitle_str, pixelArt.width, pixelArt.height)
        loadPixelArtStarred(pixelArt)
        loadPixelArtTitle(pixelArt)
    }
}