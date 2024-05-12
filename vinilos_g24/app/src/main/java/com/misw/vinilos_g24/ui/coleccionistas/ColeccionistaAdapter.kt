import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misw.vinilos_g24.R
import com.misw.vinilos_g24.models.Coleccionista

class ColeccionistaAdapter : RecyclerView.Adapter<ColeccionistaAdapter.ColeccionistasViewHolder>() {

    private var collectors: List<Coleccionista> = emptyList()

    fun setData(albums: List<Coleccionista>) {
        this.collectors = albums
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColeccionistasViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_collector, parent, false)
        return ColeccionistasViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColeccionistasViewHolder, position: Int) {
        val coleccionista = collectors[position]
        holder.bind(coleccionista)
    }

    override fun getItemCount(): Int {
        return collectors.size
    }

    inner class ColeccionistasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val collectorNameTextView: TextView = itemView.findViewById(R.id.collectorNameTextView)
        private val emailTextView: TextView = itemView.findViewById(R.id.emailTextView)

        fun bind(coleccionista: Coleccionista) {
            collectorNameTextView.text = coleccionista.name
            emailTextView.text = coleccionista.email // Assuming 'coleccionista' has a 'email' property
        }
    }
}