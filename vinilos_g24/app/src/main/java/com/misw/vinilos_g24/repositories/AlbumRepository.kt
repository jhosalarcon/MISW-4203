import androidx.lifecycle.MutableLiveData
import com.misw.vinilos_g24.models.Album
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AlbumRepository(private val apiService: NetworkServiceAdapter) {

    private val _albums = MutableLiveData<List<Album>?>()
    val albums: MutableLiveData<List<Album>?> = _albums

    private val _albumDetails = MutableLiveData<Album?>()
    val albumDetails: MutableLiveData<Album?> = _albumDetails

    suspend fun refreshData(): List<Album> {
        return apiService.getAlbums()
    }

    suspend fun fetchAlbums(): List<Album> = withContext(Dispatchers.IO) {
        return@withContext apiService.getAlbums()
    }

    suspend fun fetchAlbumDetails(albumId: Int) = withContext(Dispatchers.IO) {
        return@withContext apiService.getAlbumById(albumId)
    }
}