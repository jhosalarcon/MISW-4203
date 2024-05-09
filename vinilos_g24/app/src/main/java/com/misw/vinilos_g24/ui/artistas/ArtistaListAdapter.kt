
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misw.vinilos_g24.R
import com.misw.vinilos_g24.models.Artista
import com.squareup.picasso.Picasso

class ArtistaListAdapter(private val listener: ArtistaListAdapter.OnArtistaClickListener)
    : RecyclerView.Adapter<ArtistaListAdapter.ArtistViewHolder>() {

    private var artists: List<Artista> = emptyList()

    fun setData(albums: List<Artista>) {
        this.artists = albums
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_artist, parent, false)
        return ArtistViewHolder(view)
    }

    interface OnArtistaClickListener {
        fun onArtistaClick(artista: Int)

    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val artista = artists[position]
        holder.bind(artista)
        holder.itemView.setOnClickListener {
            listener.onArtistaClick(artista.id)

        }

    }

    override fun getItemCount(): Int {
        return artists.size
    }

    inner class ArtistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val artistImageView: ImageView = itemView.findViewById(R.id.artistImageView)
        private val artistNameTextView: TextView = itemView.findViewById(R.id.artistNameTextView)
        private val birthDateTextView: TextView = itemView.findViewById(R.id.birthDateTextView)

        fun bind(artista: Artista) {

            Picasso.get()
                .load(artista.image)
                .into(artistImageView)
            artistNameTextView.text = artista.name
            birthDateTextView.text = artista.birthDate.toString() // Assuming 'artista' has a 'birthDate' property
        }
    }
}