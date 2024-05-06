
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misw.vinilos_g24.R
import com.misw.vinilos_g24.models.Album

class AlbumDetailAdapter : RecyclerView.Adapter<AlbumDetailAdapter.AlbumDetailViewHolder>() {

    private var album: Album? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumDetailViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_detail_albumes, parent, false)
        return AlbumDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumDetailViewHolder, position: Int) {
        val nameTextView = holder.itemView.findViewById<TextView>(R.id.albumNameTextView)
        val genreTextView = holder.itemView.findViewById<TextView>(R.id.releaseTextView)
        val releaseDateTextView = holder.itemView.findViewById<TextView>(R.id.genreTextView)
        album?.let {
            nameTextView.text = it.name
            genreTextView.text = it.genre
            releaseDateTextView.text = it.releaseDate
        }
    }

    override fun getItemCount(): Int {
        return if (album != null) 1 else 0
    }

    fun UpdateAlbum(album: Album){
        this.album = album
        notifyDataSetChanged()
    }
    class AlbumDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}