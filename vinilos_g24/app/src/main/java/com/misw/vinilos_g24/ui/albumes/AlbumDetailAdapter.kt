import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misw.vinilos_g24.R
import com.misw.vinilos_g24.models.Album
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class AlbumDetailAdapter : RecyclerView.Adapter<AlbumDetailAdapter.AlbumDetailViewHolder>() {

    private var album: Album? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumDetailViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_detail_album, parent, false)
        return AlbumDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumDetailViewHolder, position: Int) {
        val albumView = holder.itemView.findViewById<ImageView>(R.id.albumImageView)
        val nameTextView = holder.itemView.findViewById<TextView>(R.id.albumNameTextView)
        val genreTextView = holder.itemView.findViewById<TextView>(R.id.releaseTextView)
        val releaseDateTextView = holder.itemView.findViewById<TextView>(R.id.genreTextView)

        album?.let {
            Picasso.get()
                .load(it.cover)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(albumView)
            nameTextView.text = it.name
            genreTextView.text = it.genre
            releaseDateTextView.text = it.releaseDate
        }
    }

    override fun getItemCount(): Int {
        return if (album != null) 1 else 0
    }

    fun updateAlbum(album: Album) {
        this.album = album
        notifyDataSetChanged()
    }

    class AlbumDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}