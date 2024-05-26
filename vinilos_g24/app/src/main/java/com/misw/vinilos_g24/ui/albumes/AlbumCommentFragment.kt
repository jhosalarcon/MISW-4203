package com.misw.vinilos_g24.ui.albumes

import NetworkServiceAdapter
import PostData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.misw.vinilos_g24.R
import com.misw.vinilos_g24.models.Album
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Response

class AlbumCommentFragment : Fragment() {

    private lateinit var spinnerAlbum: Spinner
    private lateinit var spinnerPuntaje: Spinner
    private lateinit var editText: EditText
    private lateinit var buttonSubmit: Button
    private lateinit var albumIds: List<Int>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_comment, container, false)

        spinnerAlbum = view.findViewById(R.id.spinnerAlbum)
        spinnerPuntaje = view.findViewById(R.id.spinnerPuntaje)
        editText = view.findViewById(R.id.commentAlbum)
        buttonSubmit = view.findViewById(R.id.btnSave)

        loadAlbumIds()

        val puntajeAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.puntaje,
            R.layout.spinner_item_selected
        )
        puntajeAdapter.setDropDownViewResource(R.layout.spinner_item_dropdown)
        spinnerPuntaje.adapter = puntajeAdapter

        buttonSubmit.setOnClickListener {
            lifecycleScope.launch {
                createComment()
            }
        }

        return view
    }

    private fun loadAlbumIds() {
        lifecycleScope.launch {
            val retrofit = Retrofit.Builder()
                .baseUrl(NetworkServiceAdapter.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val apiService = retrofit.create(NetworkServiceAdapter::class.java)

            try {
                val albums = apiService.getAlbums()
                albumIds = albums.map { it.id }
                val albumIdStrings = albumIds.map { it.toString() }
                val albumAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item_selected, albumIdStrings)
                albumAdapter.setDropDownViewResource(R.layout.spinner_item_dropdown)
                spinnerAlbum.adapter = albumAdapter
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "Error loading album IDs: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun loadCollectorIds() {
        lifecycleScope.launch {
            val retrofit = Retrofit.Builder()
                .baseUrl(NetworkServiceAdapter.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val apiService = retrofit.create(NetworkServiceAdapter::class.java)

            try {
                val collectors = apiService.getCollectors()
                val collectorIds = collectors.map { it.id }
                val collectorIdStrings = collectorIds.map { it.toString() }
                "val collectorAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item_selected, collectorIdStrings)"
                "collectorAdapter.setDropDownViewResource(R.layout.spinner_item_dropdown)"
                "spinnerAlbum.adapter = collectorAdapter"
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "Error loading collector IDs: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private suspend fun createComment() {
        val retrofit = Retrofit.Builder()
            .baseUrl(NetworkServiceAdapter.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val selectedAlbumIdPosition = spinnerAlbum.selectedItemPosition
        val selectedAlbumId = albumIds[selectedAlbumIdPosition]
        val selectedPuntaje = spinnerPuntaje.selectedItem.toString().toInt()
        val inputText = editText.text.toString()

        val apiService = retrofit.create(NetworkServiceAdapter::class.java)

        try {
            val response: Response<Album> = apiService.createAlbumComment(selectedAlbumId,
                com.misw.vinilos_g24.models.PostData(selectedPuntaje, inputText, collector = 1)
            )
            if (response.isSuccessful) {
                Toast.makeText(context, "Comentario creado exitosamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Error guardando el comentario: ${response.message()}", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Error guardando el comentario: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance(): AlbumCommentFragment {
            val fragment = AlbumCommentFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
