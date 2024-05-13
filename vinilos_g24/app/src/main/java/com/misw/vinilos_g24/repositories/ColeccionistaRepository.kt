
import androidx.lifecycle.MutableLiveData
import com.misw.vinilos_g24.models.Coleccionista
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ColeccionistaRepository(private val apiService: NetworkServiceAdapter) {

    private val _collectors = MutableLiveData<List<Coleccionista>?>()
    val collectors: MutableLiveData<List<Coleccionista>?> = _collectors

    suspend fun refreshData(): List<Coleccionista> {
        return apiService.getCollectors()
    }

    suspend fun fetchCollectors() = withContext(Dispatchers.IO) {
        return@withContext apiService.getCollectors()
    }
}