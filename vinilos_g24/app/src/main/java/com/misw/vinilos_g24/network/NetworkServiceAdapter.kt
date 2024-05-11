import com.misw.vinilos_g24.models.Album
import com.misw.vinilos_g24.models.Artista
import com.misw.vinilos_g24.models.Coleccionista
import retrofit2.http.GET
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
}