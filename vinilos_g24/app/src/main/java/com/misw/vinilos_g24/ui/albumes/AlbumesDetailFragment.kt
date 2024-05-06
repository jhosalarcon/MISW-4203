package com.misw.vinilos_g24.ui.albumes

import AlbumDetailAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.misw.vinilos_g24.R
import com.misw.vinilos_g24.models.Album
import com.misw.vinilos_g24.network.NetworkServiceAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AlbumesDetailFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AlbumDetailAdapter
    private val albumId by lazy { arguments?.getInt("ALBUM_ID") ?: 0 }
    private lateinit var album: Album

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_albumes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        adapter = AlbumDetailAdapter()
        recyclerView.adapter = adapter

        loadAlbumDetail()
    }
    private fun loadAlbumDetail() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://146.148.107.196:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(NetworkServiceAdapter::class.java)
        val call = apiService.getAlbumById(albumId)

        call.enqueue(object : Callback<Album> {
            override fun onResponse(call: Call<Album>, response: Response<Album>) {
                    response.body()?.let { adapter.UpdateAlbum(it) }
            }

            override fun onFailure(call: Call<Album>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(context, "Error cargando detalle de album", Toast.LENGTH_SHORT).show()
            }
        })
    }

}