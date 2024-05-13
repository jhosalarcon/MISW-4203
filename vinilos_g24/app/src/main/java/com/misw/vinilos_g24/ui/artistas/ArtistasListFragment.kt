package com.misw.vinilos_g24.ui.artistas

import ArtistaListAdapter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misw.vinilos_g24.R
import com.misw.vinilos_g24.databinding.FragmentArtistasBinding
import com.misw.vinilos_g24.models.Artista
import com.misw.vinilos_g24.network.NetworkServiceAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArtistasListFragment : Fragment(), ArtistaListAdapter.OnArtistaClickListener {

    private var _binding: FragmentArtistasBinding? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ArtistaListAdapter
    private lateinit var artists: List<Artista>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_artistas, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ArtistaListAdapter(this)
        recyclerView.adapter = adapter

        loadArtists()
        return view
    }

    private fun loadArtists() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://34.105.90.15/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(NetworkServiceAdapter::class.java)
        val call = apiService.getArtists()

        call.enqueue(object : Callback<List<Artista>> {
            override fun onResponse(call: Call<List<Artista>>, response: Response<List<Artista>>) {
                if (response.isSuccessful) {
                    artists = response.body() ?: emptyList()
                    adapter.setData(artists)
                } else {
                    Toast.makeText(context, "Error cargando artistas", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Artista>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(context, "Error cargando artistas", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onArtistaClick(album: Int) {
        val detalleArtistFragment = ArtistaDetailFragment.newInstance(album)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_art, detalleArtistFragment, "detalleAlbumFragment")
        transaction.addToBackStack(null)
        transaction.commit()

        Handler(Looper.getMainLooper()).postDelayed({
            val detailFragment =
                requireActivity().supportFragmentManager.findFragmentByTag("detalleAlbumFragment")
            detailFragment?.let {
                requireActivity().supportFragmentManager.beginTransaction().hide(it).commit()
            }
        }, 5000)
    }
}