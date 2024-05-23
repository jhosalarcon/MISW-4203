package com.misw.vinilos_g24.ui.coleccionistas

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misw.vinilos_g24.R
import com.misw.vinilos_g24.models.Coleccionista

class ColeccionistaDetailAdapter : RecyclerView.Adapter<ColeccionistaDetailAdapter.ColeccionistaDetailViewModel>() {

    private var coleccionista: Coleccionista? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColeccionistaDetailViewModel {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_detail_coleccionista, parent, false)
        return ColeccionistaDetailViewModel(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ColeccionistaDetailViewModel, position: Int) {
        val nameTextView = holder.itemView.findViewById<TextView>(R.id.collectorNameTextView)
        val descTextView = holder.itemView.findViewById<TextView>(R.id.collectorTlfTextView)
        val emailTextView = holder.itemView.findViewById<TextView>(R.id.collectorEmailTextView)
        coleccionista?.let {
            nameTextView.text = it.name
            descTextView.text = it.telephone
            emailTextView.text = it.email
        }
    }

    override fun getItemCount(): Int {
        return if (coleccionista != null) 1 else 0
    }

    fun updateColeccionista(coleccionista: Coleccionista) {
        this.coleccionista = coleccionista
        notifyDataSetChanged()
    }

    class ColeccionistaDetailViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}