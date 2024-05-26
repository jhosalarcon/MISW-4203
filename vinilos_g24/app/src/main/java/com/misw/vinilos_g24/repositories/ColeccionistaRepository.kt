import androidx.lifecycle.MutableLiveData
import com.misw.vinilos_g24.models.Coleccionista
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ColeccionistaRepository(private val apiService: NetworkServiceAdapter) {

    private val _collectors = MutableLiveData<List<Coleccionista>?>()
    val collectors: MutableLiveData<List<Coleccionista>?> = _collectors

    private val _collectorDetails = MutableLiveData<Coleccionista?>()
    val collectorDetails: MutableLiveData<Coleccionista?> = _collectorDetails

    suspend fun refreshData(): List<Coleccionista> {
        return apiService.getCollectors()
    }

    suspend fun fetchCollectors() = withContext(Dispatchers.IO) {
        return@withContext apiService.getCollectors()
    }

    suspend fun fetchCollectorDetails(collectorId: Int) = withContext(Dispatchers.IO) {
        return@withContext apiService.getCollectorById(collectorId)
    }
}
