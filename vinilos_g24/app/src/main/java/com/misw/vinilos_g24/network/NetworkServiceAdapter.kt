
import android.content.Context
import com.misw.vinilos_g24.models.Album
import com.misw.vinilos_g24.models.Artista
import com.misw.vinilos_g24.models.Coleccionista
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NetworkServiceAdapter {
    @GET("albums")
    suspend fun getAlbums(): List<Album>

    @GET("albums/{id}")
    suspend fun getAlbumById(@Path("id") albumId: Int): Album

    @GET("musicians")
    suspend fun getArtists(): List<Artista>

    @GET("musicians/{id}")
    suspend fun getMusiciansById(@Path("id") artistaId: Int): Artista

    @GET("collectors")
    suspend fun getCollectors(): List<Coleccionista>

    @GET("collectors/{id}")
    suspend fun getCollectorById(@Path("id") coleccionistaId: Int): Coleccionista

    @POST("albums")
    suspend fun createAlbum(@Body album: Album): Response<Album>

    @POST("albums/{id}/comments")
    suspend fun createAlbumComment(@Body data: PostData): Response<Album>

    companion object {
        const val BASE_URL = "http://34.132.241.74/"
        private var instance: NetworkServiceAdapter? = null

        fun getInstance(context: Context): NetworkServiceAdapter {
            return instance ?: synchronized(this) {
                instance ?: createNetworkService(context).also {
                    instance = it
                }
            }
        }

        private fun createNetworkService(context: Context): NetworkServiceAdapter {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            return retrofit.create(NetworkServiceAdapter::class.java)
        }
    }
}
data class PostData(
    val spinnerValue: String,
    val editTextValue: String,
)