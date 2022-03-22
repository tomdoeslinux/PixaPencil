package com.realtomjoney.pyxlmoose.adapters

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.converters.BitmapConverter
import com.realtomjoney.pyxlmoose.database.AppData
import com.realtomjoney.pyxlmoose.databinding.RecentCreationsLayoutBinding
import com.realtomjoney.pyxlmoose.enums.SnackbarDuration
import com.realtomjoney.pyxlmoose.extensions.showSnackbar
import com.realtomjoney.pyxlmoose.listeners.RecentCreationsListener
import com.realtomjoney.pyxlmoose.models.PixelArt
import com.realtomjoney.pyxlmoose.viewholders.RecentCreationsViewHolder

class RecentCreationsAdapter(private val data: List<PixelArt>, private val listener: RecentCreationsListener) : RecyclerView.Adapter<RecentCreationsViewHolder>() {
    private lateinit var binding: RecentCreationsLayoutBinding

    var userHasLongPressed = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentCreationsViewHolder {
        binding = RecentCreationsLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return RecentCreationsViewHolder(binding.recentCreationsLayoutRootLayout)
    }

    override fun onBindViewHolder(holder: RecentCreationsViewHolder, position: Int) = data.forEach { _ ->
        binding.recentCreationsLayoutMaterialCardView.apply parent@{
            val item = data[position]

            binding.apply {
                recentCreationsLayoutImageView.setImageBitmap(BitmapConverter.convertStringToBitmap(item.coverBitmap))
                recentCreationsLayoutSubtitle.text = item.dateCreated

                recentCreationsLayoutTitle.apply {
                    if (data[position].title.length > 6) {
                        ellipsize = TextUtils.TruncateAt.MARQUEE
                        isSelected = true
                        isSingleLine = true
                        text = (item.title + " ".repeat(10)).repeat(200)
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
            else setImageResource(R.drawable.ic_baseline_star_border_24)
        }
    }
    private fun favouriteRecentCreation(contextView: View, pixelArt: PixelArt) {
        contextView.showSnackbar("Saved ${pixelArt.title} to starred items.", SnackbarDuration.DEFAULT)
        pixelArt.starred = true
    }

    private fun unFavouriteRecentCreation(contextView: View, pixelArt: PixelArt) {
        contextView.showSnackbar("You have removed ${pixelArt.title} from your starred items.", SnackbarDuration.DEFAULT)
        pixelArt.starred = false
    }

    override fun getItemCount(): Int {
        return data.size
    }
}