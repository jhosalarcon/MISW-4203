import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.misw.vinilos_g24.models.Coleccionista
import com.misw.vinilos_g24.network.NetworkServiceAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class ColeccionistaRepository(private val apiService: NetworkServiceAdapter) {

    private val _collectors = MutableLiveData<List<Coleccionista>?>()
    val collectors: MutableLiveData<List<Coleccionista>?> = _collectors

    suspend fun fetchCollectors() {
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getCollectors().execute()
                if (response.isSuccessful) {
                    val collectors = response.body()
                    _collectors.postValue(collectors)
                } else {
                    Log.d("Error", "Error en coleccionista repository");
                }
            } catch (e: IOException) {
                Log.d("Error", "Error en coleccionista repository", e)
            }
        }
    }
}