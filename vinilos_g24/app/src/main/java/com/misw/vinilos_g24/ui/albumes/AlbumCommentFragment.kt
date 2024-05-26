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
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AlbumCommentFragment : Fragment() {

    private lateinit var spinner: Spinner
    private lateinit var editText: EditText
    private lateinit var buttonSubmit: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_comment, container, false)

        spinner = view.findViewById(R.id.spinnerAlbum)
        spinner = view.findViewById(R.id.spinnerPuntaje)
        editText = view.findViewById(R.id.commentAlbum)
        buttonSubmit = view.findViewById(R.id.btnSave)

        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.puntaje, // Define this array in your strings.xml
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        buttonSubmit.setOnClickListener {
            lifecycleScope.launch {
                createComment()
            }
        }

        return view
    }

    private suspend fun createComment() {
        val retrofit = Retrofit.Builder()
            .baseUrl(NetworkServiceAdapter.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val selectedValue = spinner.selectedItem.toString()
        val inputText = editText.text.toString()

        val apiService = retrofit.create(NetworkServiceAdapter::class.java)

        try {
            apiService.createAlbumComment(PostData(selectedValue, inputText))
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
        fun newInstance(): AlbumCommentFragment {
            val fragment = AlbumCommentFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}