import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misw.vinilos_g24.R
import com.misw.vinilos_g24.models.Artista

class ArtistaAdapter : RecyclerView.Adapter<ArtistaAdapter.AlbumViewHolder>() {

    private var artists: List<Artista> = emptyList()

    fun setData(albums: List<Artista>) {
        this.artists = albums
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = artists[position]
        holder.bind(album)
    }

    override fun getItemCount(): Int {
        return artists.size
    }

    inner class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)

        fun bind(artista: Artista) {
            nameTextView.text = artista.name
        }
    }
}