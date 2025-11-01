import com.example.view.data.remote.MovieApi
import com.example.view.data.remote.response.MovieDetailResponse
import com.example.view.data.remote.response.MovieListResponse
import com.example.view.data.repo.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api : MovieApi
): MovieRepository{
    override suspend fun getMovies(): List<MovieListResponse> {
        TODO("Not yet implemented")
    }
    override suspend fun getMovieById(id: String): MovieDetailResponse {
        TODO("Not yet implemented")
    }

}