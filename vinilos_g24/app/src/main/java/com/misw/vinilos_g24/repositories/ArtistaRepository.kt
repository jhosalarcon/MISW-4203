import androidx.lifecycle.MutableLiveData
import com.misw.vinilos_g24.models.Artista
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArtistaRepository(private val apiService: NetworkServiceAdapter) {

    private val _artists = MutableLiveData<List<Artista>?>()
    val artists: MutableLiveData<List<Artista>?> = _artists

    private val _artistDetails = MutableLiveData<Artista?>()
    val artistDetails: MutableLiveData<Artista?> = _artistDetails
    suspend fun refreshData(): List<Artista> {
        return apiService.getArtists()
    }

    suspend fun fetchArtists(): List<Artista> = withContext(Dispatchers.IO) {
        return@withContext apiService.getArtists()
    }

    suspend fun fetchArtistDetails(artistId: Int) = withContext(Dispatchers.IO) {
        return@withContext apiService.getMusiciansById(artistId)
    }
}