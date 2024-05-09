package com.misw.vinilos_g24.ui.artistas

import ArtistaDetailAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misw.vinilos_g24.R
import com.misw.vinilos_g24.models.Artista
import com.misw.vinilos_g24.network.NetworkServiceAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

        val artistaId = arguments?.getInt(ARG_ARTISTA_ID) ?: 0
        loadAlbumDetail(artistaId)
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


    private fun loadAlbumDetail(artistaId: Int) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://34.28.23.142:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(NetworkServiceAdapter::class.java)
        val call = apiService.getMusiciansById(artistaId)
        call.enqueue(object : Callback<Artista> {
            override fun onResponse(call: Call<Artista>, response: Response<Artista>) {
                if (response.isSuccessful) {
                    val artista = response.body()
                    if (artista != null) {
                        adapter.updateArtista(artista)
                    } else {
                        Toast.makeText(
                            context,
                            "No se recibió ningún detalle de artista",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        context,
                        "OnResponse Error cargando detalle de artista",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }

            override fun onFailure(call: Call<Artista>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(
                    context,
                    "onFailure Error cargando detalle de artista",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        })


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