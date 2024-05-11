
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.misw.vinilos_g24.models.Artista
import com.misw.vinilos_g24.network.NetworkServiceAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class ArtistaRepository(private val apiService: NetworkServiceAdapter) {

    private val _artists = MutableLiveData<List<Artista>?>()
    val artists: MutableLiveData<List<Artista>?> = _artists

    private val _artistDetails = MutableLiveData<Artista?>()
    val artistDetails: MutableLiveData<Artista?> = _artistDetails

    suspend fun fetchArtists() {
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getArtists().execute()
                if (response.isSuccessful) {
                    val artists = response.body()
                    _artists.postValue(artists)
                } else {
                    Log.d("Error", "Error en artista repository");
                }
            } catch (e: IOException) {
                Log.d("Error", "Error en artista repository", e)
            }
        }
    }

    suspend fun fetchArtistaDetails(artistId: Int) {
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getMusiciansById(artistId).execute()
                if (response.isSuccessful) {
                    val artistDetails = response.body()
                    _artistDetails.postValue(artistDetails)
                } else {
                    Log.d("Error", "Error en artista repository");
                }
            } catch (e: IOException) {
                Log.d("Error", "Error en artista repository", e)
            }
        }
    }
}