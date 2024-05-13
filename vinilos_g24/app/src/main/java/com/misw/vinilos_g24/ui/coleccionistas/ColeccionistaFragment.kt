package com.misw.vinilos_g24.ui.coleccionistas

import ColeccionistaAdapter
import NetworkServiceAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misw.vinilos_g24.R
import com.misw.vinilos_g24.databinding.FragmentColeccionistasBinding
import com.misw.vinilos_g24.models.Coleccionista
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ColeccionistaFragment : Fragment() {

    private var _binding: FragmentColeccionistasBinding? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ColeccionistaAdapter
    private lateinit var collectors: List<Coleccionista>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_coleccionistas, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ColeccionistaAdapter()
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            loadCollectors()
        }
        return view
    }


    private suspend fun loadCollectors() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://34.105.90.15/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(NetworkServiceAdapter::class.java)
        try {
            val collectors = apiService.getCollectors()
            adapter.setData(collectors)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(
                context,
                "Error cargando coleccionistas: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}