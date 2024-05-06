package com.misw.vinilos_g24.ui.albumes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.misw.vinilos_g24.R
import com.misw.vinilos_g24.models.Album
import com.squareup.picasso.Picasso

class AlbumListAdapter(private val listener: OnAlbumClickListener) :
    RecyclerView.Adapter<AlbumListAdapter.AlbumViewHolder>() {

    private var albums: List<Album> = emptyList()

    fun setData(albums: List<Album>) {
        this.albums = albums
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return AlbumViewHolder(view)
    }

    interface OnAlbumClickListener {
        fun onAlbumClick(album: Int)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albums[position]
        holder.bind(album)

        holder.itemView.setOnClickListener {
            listener.onAlbumClick(album.id)
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
            Picasso.get()
                .load(album.cover)
                .into(albumImageView)

            releaseTextView.text = album.releaseDate
            genreTextView.text = album.genre
        }
    }
}