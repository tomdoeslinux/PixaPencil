package com.realtomjoney.pyxlmoose

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.ImageButton
import androidx.core.graphics.ColorUtils
import com.google.android.material.snackbar.Snackbar

class RecentCreationsAdapter(private var data: List<SavedPixelArt>, private val listener: RecentCreationsListener) : RecyclerView.Adapter<RecentCreationsAdapter.RecentCreationsViewHolder>() {
    class RecentCreationsViewHolder(val frame: FrameLayout) : RecyclerView.ViewHolder(frame)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecentCreationsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recent_creations_layout, parent, false) as FrameLayout)

    override fun onBindViewHolder(holder: RecentCreationsViewHolder, position: Int) = data.forEach { _ ->
        val rootLayout = holder.frame.findViewById<FrameLayout>(R.id.rootFrameLayout)


        with (rootLayout.findViewById<CardView>(R.id.mCard))  {
            findViewById<ImageView>(R.id.mImageView).setImageBitmap(data[position].bitmap)
            findViewById<TextView>(R.id.mdate).text = data[position].dateCreated
            val title: TextView = findViewById(R.id.mtext)

            if (data[position].title.length > 6) {
                title.ellipsize = TextUtils.TruncateAt.MARQUEE
                title.isSelected = true
                title.isSingleLine = true
                title.text = (data[position].title + "                    ").repeat(200)
            } else {
                title.text = data[position].title
            }

            this.setOnClickListener {
                listener.onCreationTapped(data[position])
            }

            this.setOnLongClickListener {
                listener.onCreationLongTapped(data[position])
                true
            }

            changeStarredIndicator((findViewById(R.id.mFavouriteButton)), data[position])

            this.findViewById<ImageButton>(R.id.mFavouriteButton).setOnClickListener {
                if (!data[position].isFavourited) favouriteRecentCreation(this, data[position])
                else unFavouriteRecentCreation(this, data[position])

                changeStarredIndicator((it as ImageButton), data[position])
            }
        }

    }

    private fun changeStarredIndicator(imageButton: ImageButton, savedPixelArt: SavedPixelArt) {
        if (savedPixelArt.isFavourited) imageButton.setImageResource(R.drawable.ic_baseline_star_24)
        else imageButton.setImageResource(R.drawable.ic_baseline_star_border_24)
    }

    private fun favouriteRecentCreation(contextView: View, savedPixelArt: SavedPixelArt) {
        Snackbar.make(contextView,
            "Saved ${savedPixelArt.title} to favourites.",
            Snackbar.LENGTH_LONG)
            .setTextColor(Color.BLACK)
            .setBackgroundTint(Color.parseColor("#eaddff"))
            .show()

        savedPixelArt.isFavourited = true
    }

    private fun unFavouriteRecentCreation(contextView: View, savedPixelArt: SavedPixelArt) {
        Snackbar.make(contextView,
            "You have removed ${savedPixelArt.title} from your favourites.",
            Snackbar.LENGTH_LONG)
            .setTextColor(Color.BLACK)
            .setBackgroundTint(Color.parseColor("#eaddff"))
            .show()

        savedPixelArt.isFavourited = false
    }

    override fun getItemCount() = data.size
}