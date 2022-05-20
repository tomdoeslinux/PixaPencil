package com.therealbluepandabear.pixapencil.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.therealbluepandabear.pixapencil.R
import com.therealbluepandabear.pixapencil.database.AppData
import com.therealbluepandabear.pixapencil.databinding.RecentCreationsLayoutBinding
import com.therealbluepandabear.pixapencil.enums.SnackbarDuration
import com.therealbluepandabear.pixapencil.extensions.showSnackbar
import com.therealbluepandabear.pixapencil.listeners.RecentCreationsListener
import com.therealbluepandabear.pixapencil.models.PixelArt
import com.therealbluepandabear.pixapencil.viewholders.PixelArtViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PixelArtCreationsAdapter(
    private val snackbarView: View,
    private val data: MutableList<PixelArt>,
    private val listener: RecentCreationsListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mainScope = CoroutineScope(Dispatchers.Main.immediate)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = RecentCreationsLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return PixelArtViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) /*= data.forEach*/ { //_ ->
        val item = data[position]
        if( holder is PixelArtViewHolder ) {
            holder.bind(item)

            holder.binding.recentCreationsLayoutMaterialCardView.setOnClickListener {
                listener.onCreationTapped(item)
            }

            holder.binding.recentCreationsLayoutMaterialCardView.setOnLongClickListener {
                listener.onCreationLongTapped(item)
                true
            }
            holder.binding.recentCreationsLayoutFavoriteButton.setOnClickListener {
                if (item.starred) {
                    unFavouriteRecentCreation(snackbarView, item)
                    item.starred = false

                    mainScope.launch {
                        AppData.pixelArtDB.pixelArtCreationsDao().updatePixelArtCreation(item)
                    }
                } else {
                    favouriteRecentCreation(snackbarView, item)
                    item.starred = true

                    mainScope.launch {
                        AppData.pixelArtDB.pixelArtCreationsDao().updatePixelArtCreation(item)
                    }
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

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateDataSource(list:List<PixelArt>){
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }
}