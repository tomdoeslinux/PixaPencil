package layout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.realtomjoney.pyxlmoose.CanvasFragmentListener
import com.realtomjoney.pyxlmoose.Pixel
import com.realtomjoney.pyxlmoose.R
import com.realtomjoney.pyxlmoose.SquareFrameLayout

class RecyclerViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.pixel_layout, parent, false)) {
        val tileParent: SquareFrameLayout = itemView.findViewById(R.id.pixelParent)
}

class CanvasRecyclerAdapter(private val pixels: List<Pixel>,
                            private val caller: CanvasFragmentListener) :
    RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}