package com.therealbluepandabear.pixapencil.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.databinding.RecentCreationsLayoutBinding
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.setOnLongPressListener
import com.therealbluepandabear.pixapencil.extensions.showSnackbar
import com.therealbluepandabear.pixapencil.listeners.RecentCreationsListener
import com.therealbluepandabear.pixapencil.models.PixelArt
import com.therealbluepandabear.pixapencil.viewholders.PixelArtViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PixelArtAdapter(
    private val snackbarView: View,
    private val listener: RecentCreationsListener,
    private val context: Context
) : ListAdapter<PixelArt, RecyclerView.ViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = RecentCreationsLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return PixelArtViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val pixelArt = getItem(position)

        if (holder is PixelArtViewHolder) {
            holder.bind(pixelArt)

            holder.binding.recentCreationsLayoutMaterialCardView.setOnClickListener {
                listener.onCreationTapped(pixelArt)
            }

            holder.binding.recentCreationsLayoutMaterialCardView.setOnLongPressListener {
                listener.onCreationLongTapped(pixelArt)
            }

            holder.binding.recentCreationsLayoutFavoriteButton.setOnClickListener {
                if (pixelArt.starred) {
                    pixelArt.starred = false
                    listener.onUnstarredTapped(pixelArt)

                    unFavouriteRecentCreation(snackbarView, pixelArt)
                    holder.bind(pixelArt)
                } else {
                    pixelArt.starred = true
                    listener.onStarredTapped(pixelArt)

                    favouriteRecentCreation(snackbarView, pixelArt)
                    holder.bind(pixelArt)
                }
            }
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

    companion object {
        val diffCallback: DiffUtil.ItemCallback<PixelArt> = object : DiffUtil.ItemCallback<PixelArt>() {
            override fun areItemsTheSame(oldItem: PixelArt, newItem: PixelArt): Boolean {
                return oldItem.objId == newItem.objId
            }

            override fun areContentsTheSame(oldItem: PixelArt, newItem: PixelArt): Boolean {
                return oldItem.coverBitmapFilePath == newItem.coverBitmapFilePath
            }

        }
    }
}