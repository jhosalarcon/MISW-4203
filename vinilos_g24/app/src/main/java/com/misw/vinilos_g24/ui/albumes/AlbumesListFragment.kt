package com.misw.vinilos_g24.ui.albumes

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
import com.misw.vinilos_g24.databinding.FragmentAlbumesBinding
import com.misw.vinilos_g24.models.Album
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AlbumesListFragment : Fragment(), AlbumListAdapter.OnAlbumClickListener {

    private var _binding: FragmentAlbumesBinding? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AlbumListAdapter
    private lateinit var albums: List<Album>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_albumes, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = AlbumListAdapter(this)
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            loadAlbums()
        }
        return view
    }

    private suspend fun loadAlbums() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(NetworkServiceAdapter::class.java)
        try {
            val albums = apiService.getAlbums()
            adapter.setData(albums)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(
                context,
                "Error cargando detalle de álbum: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAlbumClick(album: Int) {
        val detalleAlbumFragment = AlbumesDetailFragment.newInstance(album)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, detalleAlbumFragment, "detalleAlbumFragment")
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