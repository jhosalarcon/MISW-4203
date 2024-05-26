package com.misw.vinilos_g24.ui.artistas

import ArtistaDetailAdapter
import NetworkServiceAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misw.vinilos_g24.R
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArtistaDetailFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ArtistaDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_artista, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ArtistaDetailAdapter()
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            val artistId = arguments?.getInt(ARG_ARTISTA_ID) ?: 0
            loadArtistDetail(artistId)
        }
        onResume()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val fragmentManager = requireActivity().supportFragmentManager
            if (fragmentManager.backStackEntryCount > 0) {
                fragmentManager.popBackStack()
            } else {
                requireActivity().finish()
            }
        }
    }

    private suspend fun loadArtistDetail(artistId: Int) {
        val retrofit = Retrofit.Builder()
            .baseUrl(NetworkServiceAdapter.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(NetworkServiceAdapter::class.java)
        try {
            val artista = apiService.getMusiciansById(artistId)
            adapter.updateArtista(artista)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(
                context,
                "Error cargando detalle del artista: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    companion object {
        private const val ARG_ARTISTA_ID = "artistaId"
        fun newInstance(artistaId: Int): ArtistaDetailFragment {
            val fragment = ArtistaDetailFragment()
            val args = Bundle()
            args.putInt(ARG_ARTISTA_ID, artistaId)
            fragment.arguments = args
            return fragment
        }
    }

}