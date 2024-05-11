package com.misw.vinilos_g24.ui.artistas

import ArtistaListAdapter
import NetworkServiceAdapter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misw.vinilos_g24.R
import com.misw.vinilos_g24.databinding.FragmentArtistasBinding
import com.misw.vinilos_g24.models.Artista
import kotlinx.coroutines.launch
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

        lifecycleScope.launch {
            loadArtists()
        }

        return view
    }


    private suspend fun loadArtists() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(NetworkServiceAdapter::class.java)
        try {
            val artists = apiService.getArtists()
            adapter.setData(artists)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(
                context,
                "Error cargando artistas: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onArtistaClick(artista: Int) {
        val detalleArtistFragment = ArtistaDetailFragment.newInstance(artista)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(
            R.id.fragment_container_art,
            detalleArtistFragment,
            "detalleAlbumFragment"
        )
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