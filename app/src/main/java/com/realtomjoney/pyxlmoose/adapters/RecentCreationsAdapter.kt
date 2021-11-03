package com.realtomjoney.pyxlmoose.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.ImageButton
import com.realtomjoney.pyxlmoose.*
import com.realtomjoney.pyxlmoose.databinding.RecentCreationsLayoutBinding
import com.realtomjoney.pyxlmoose.extensions.SnackbarDuration
import com.realtomjoney.pyxlmoose.extensions.showSnackbar
import com.realtomjoney.pyxlmoose.listeners.RecentCreationsListener
import com.realtomjoney.pyxlmoose.models.PixelArt
import com.realtomjoney.pyxlmoose.viewholders.RecentCreationsViewHolder

class RecentCreationsAdapter(private var data: List<PixelArt>, private val listener: RecentCreationsListener) : RecyclerView.Adapter<RecentCreationsViewHolder>() {
    private lateinit var binding: RecentCreationsLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentCreationsViewHolder {
        binding = RecentCreationsLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return RecentCreationsViewHolder(binding.rootFrameLayout)
    }

    override fun onBindViewHolder(holder: RecentCreationsViewHolder, position: Int) = data.forEach { _ ->
        with (binding.mCard)  {
            val item = data[position]

            binding.mImageView.setImageBitmap(item.bitmap)
            binding.mdate.text = item.dateCreated

            if (data[position].title.length > 6) {
                binding.mtext.ellipsize = TextUtils.TruncateAt.MARQUEE
                binding.mtext.isSelected = true
                binding.mtext.isSingleLine = true
                binding.mtext.text = (item.title + " ".repeat(10)).repeat(200)
            } else {
                binding.mtext.text = item.title
            }

            this.setOnClickListener {
                listener.onCreationTapped(item)
            }

            this.setOnLongClickListener {
                listener.onCreationLongTapped(item)
                true
            }

            changeStarredIndicator(binding.mFavouriteButton, item)

            binding.mFavouriteButton.setOnClickListener {
                if (item.isFavourited) unFavouriteRecentCreation(this, item)
                else favouriteRecentCreation(this, item)

                changeStarredIndicator((it as ImageButton), item)
            }
        }
    }

    private fun changeStarredIndicator(imageButton: ImageButton, pixelArt: PixelArt) {
        if (pixelArt.isFavourited) imageButton.setImageResource(R.drawable.ic_baseline_star_24)
        else imageButton.setImageResource(R.drawable.ic_baseline_star_border_24)
    }
    private fun favouriteRecentCreation(contextView: View, pixelArt: PixelArt) {
        contextView.showSnackbar("Saved ${pixelArt.title} to favourites.", SnackbarDuration.DEFAULT)
        pixelArt.isFavourited = true
    }

    private fun unFavouriteRecentCreation(contextView: View, pixelArt: PixelArt) {
        contextView.showSnackbar("You have removed ${pixelArt.title} from your favourites.",
            SnackbarDuration.DEFAULT
        )
        pixelArt.isFavourited = false
    }

    override fun getItemCount() = data.size
}