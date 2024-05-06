
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.misw.vinilos_g24.R
import com.misw.vinilos_g24.models.Album
import com.misw.vinilos_g24.ui.albumes.AlbumesDetailFragment
import com.squareup.picasso.Picasso


class AlbumListAdapter : RecyclerView.Adapter<AlbumListAdapter.AlbumViewHolder>() {

    private var albums: List<Album> = emptyList()

    fun setData(albums: List<Album>) {
        this.albums = albums
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return AlbumViewHolder(view)
    }


    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albums[position]
        holder.bind(album)
        holder.itemView.setOnClickListener {
            val fragmentManager = (holder.itemView.context as FragmentActivity).supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            val fragment = AlbumesDetailFragment()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    inner class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val albumImageView: ImageView = itemView.findViewById(R.id.albumImageView)
        private val albumNameTextView: TextView = itemView.findViewById(R.id.albumNameTextView)
        private val genreTextView: TextView = itemView.findViewById(R.id.genreTextView)
        private val releaseTextView: TextView = itemView.findViewById(R.id.releaseTextView)

        fun bind(album: Album) {
            albumNameTextView.text = album.name
            if (album.cover != null) {
                Picasso.get()
                    .load(album.cover)
                    .into(albumImageView)
            } else {
                // Set placeholder image if cover URL is null
                Picasso.get()
                    .load("https://placehold.co/")  .into(albumImageView)
            }

            val performersString = album.performers.joinToString(", ")
            releaseTextView.text = album.releaseDate
            genreTextView.text = album.genre
        }
    }
}