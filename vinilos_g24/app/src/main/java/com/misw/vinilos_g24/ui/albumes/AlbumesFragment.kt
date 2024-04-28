package com.misw.vinilos_g24.ui.albumes

import AlbumAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misw.vinilos_g24.R
import com.misw.vinilos_g24.databinding.FragmentAlbumesBinding
import com.misw.vinilos_g24.models.Album
import com.misw.vinilos_g24.network.NetworkServiceAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AlbumesFragment : Fragment() {

    private var _binding: FragmentAlbumesBinding? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AlbumAdapter
    private lateinit var albums: List<Album>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_albumes, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = AlbumAdapter()
        recyclerView.adapter = adapter

        loadAlbums()
        return view
    }

    private fun loadAlbums() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://35.226.250.165:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(NetworkServiceAdapter::class.java)
        val call = apiService.getAlbums()

        call.enqueue(object : Callback<List<Album>> {
            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                if (response.isSuccessful) {
                    albums = response.body() ?: emptyList()
                    adapter.setData(albums)
                } else {
                    Toast.makeText(context, "Error cargando álbumes", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(context, "Error cargando álbumes", Toast.LENGTH_SHORT).show()
            }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}