
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misw.vinilos_g24.R
import com.misw.vinilos_g24.models.Artista
import com.squareup.picasso.Picasso

class ArtistaDetailAdapter : RecyclerView.Adapter<ArtistaDetailAdapter.ArtistaDetailViewHolder>() {

    private var artista: Artista? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistaDetailViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_detail_artista, parent, false)
        return ArtistaDetailViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ArtistaDetailViewHolder, position: Int) {
        val artistaView = holder.itemView.findViewById<ImageView>(R.id.artistImageView)
        val nameTextView = holder.itemView.findViewById<TextView>(R.id.artistNameTextView)
        val descTextView = holder.itemView.findViewById<TextView>(R.id.artistDescTextView)
        val birthDateTextView = holder.itemView.findViewById<TextView>(R.id.birthDateTextView)
        artista?.let {
            Picasso.get().load(it.image).into(artistaView)
            nameTextView.text = it.name
            descTextView.text = it.description
            birthDateTextView.text = "Fecha de nacimiento: " + it.birthDate.toString()
        }
    }

    override fun getItemCount(): Int {
        return if (artista != null) 1 else 0
    }

    fun updateArtista(artista: Artista){
        this.artista = artista
        notifyDataSetChanged()
    }
    class ArtistaDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}