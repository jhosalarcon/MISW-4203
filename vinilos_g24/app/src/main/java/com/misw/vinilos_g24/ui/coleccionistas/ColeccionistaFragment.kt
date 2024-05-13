package com.misw.vinilos_g24.ui.coleccionistas

import ColeccionistaAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misw.vinilos_g24.R
import com.misw.vinilos_g24.databinding.FragmentColeccionistasBinding
import com.misw.vinilos_g24.models.Coleccionista
import com.misw.vinilos_g24.network.NetworkServiceAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

        loadCollectors()
        return view
    }

    private fun loadCollectors() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://34.105.90.15/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(NetworkServiceAdapter::class.java)
        val call = apiService.getCollectors()

        call.enqueue(object : Callback<List<Coleccionista>> {
            override fun onResponse(call: Call<List<Coleccionista>>, response: Response<List<Coleccionista>>) {
                if (response.isSuccessful) {
                    collectors = response.body() ?: emptyList()
                    adapter.setData(collectors)
                } else {
                    Toast.makeText(context, "Error cargando coleccionistas", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Coleccionista>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(context, "Error cargando coleccionistas", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}