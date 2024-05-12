import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.misw.vinilos_g24.models.Album
import com.misw.vinilos_g24.network.NetworkServiceAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class AlbumRepository(private val apiService: NetworkServiceAdapter) {

    private val _albums = MutableLiveData<List<Album>?>()
    val albums: MutableLiveData<List<Album>?> = _albums

    private val _albumDetails = MutableLiveData<Album?>()
    val albumDetails: MutableLiveData<Album?> = _albumDetails

    suspend fun fetchAlbums() {
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getAlbums().execute()
                if (response.isSuccessful) {
                    val albums = response.body()
                    _albums.postValue(albums)
                } else {
                    Log.d("Error", "Error en album repository");
                }
            } catch (e: IOException) {
                Log.d("Error", "Error en album repository", e)
            }
        }
    }

    suspend fun fetchAlbumDetails(albumId: Int) {
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getAlbumById(albumId).execute()
                if (response.isSuccessful) {
                    val albumDetails = response.body()
                    _albumDetails.postValue(albumDetails)
                } else {
                    Log.d("Error", "Error en album repository");
                }
            } catch (e: IOException) {
                Log.d("Error", "Error en album repository", e)
            }
        }
    }
}