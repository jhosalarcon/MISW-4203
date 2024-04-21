import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdaptador(private val albumLista: List<CantanteModelo>) :
    RecyclerView.Adapter<RecyclerViewAdaptador.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreAlbum: TextView = itemView.findViewById(R.id.tvAlbum)
        val cantante: TextView = itemView.findViewById(R.id.tvCantante)
        val imgAlbum: ImageView = itemView.findViewById(R.id.imageAlbum)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cover, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nombreAlbum.text = albumLista[position].nombreAlbum
        holder.cantante.text = albumLista[position].cantante
        holder.imgAlbum.setImageResource(albumLista[position].imgAlbum)
    }

    override fun getItemCount(): Int {
        return albumLista.size
    }
}