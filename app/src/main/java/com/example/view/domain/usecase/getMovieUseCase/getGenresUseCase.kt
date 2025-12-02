import com.example.movieapp.domain.util.Resource
import com.example.view.BuildConfig
import com.example.view.domain.model.Genre
import com.example.view.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<Resource<List<Genre>>> = flow {
        emit(Resource.Loading())
        try {
            // API Key UseCase içinde veya Repository'de handle edildi
            val response = movieRepository.getGenres(BuildConfig.TMDB_API_KEY)

            // Mapping işlemi
            val genres = response.genres
                .map { Genre(it.id, it.name) }
                .sortedBy { it.name }

            emit(Resource.Success(genres))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Hata oluştu"))
        } catch (e: IOException) {
            emit(Resource.Error("İnternet bağlantısı yok"))
        }
    }
}