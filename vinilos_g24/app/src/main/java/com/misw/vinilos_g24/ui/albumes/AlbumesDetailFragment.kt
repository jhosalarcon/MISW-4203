package com.misw.vinilos_g24.ui.albumes

import AlbumDetailAdapter
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

class AlbumesDetailFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AlbumDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_albumes, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = AlbumDetailAdapter()
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            val albumId = arguments?.getInt(ARG_ALBUM_ID) ?: 0
            loadAlbumDetail(albumId)
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

    private suspend fun loadAlbumDetail(albumId: Int) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://34.105.6.205/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(NetworkServiceAdapter::class.java)
        try {
            val album = apiService.getAlbumById(albumId)
            adapter.updateAlbum(album)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(
                context,
                "Error cargando detalle de álbum: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    companion object {
        private const val ARG_ALBUM_ID = "albumId"
        fun newInstance(albumId: Int): AlbumesDetailFragment {
            val fragment = AlbumesDetailFragment()
            val args = Bundle()
            args.putInt(ARG_ALBUM_ID, albumId)
            fragment.arguments = args
            return fragment
        }
    }

}