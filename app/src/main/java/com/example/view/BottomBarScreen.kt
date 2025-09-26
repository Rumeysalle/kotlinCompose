import androidx.annotation.DrawableRes
import com.example.view.R

sealed class Screen(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int? = null,
) {
    object Home : Screen("home", "Home", R.drawable.home)
    object Mylist : Screen("mylist", "Mylist", R.drawable.my_list)
    object Downloads : Screen("downloads", "Downloads", R.drawable.download)
    object Profile : Screen("profile", "Profile", R.drawable.profile)

    object MovieDetails : Screen("movieDetail/{movieId}", "MovieDetail") {
        fun createRoute(movieId: String) = "movieDetail/$movieId"
    }

    companion object {
        val bottomBarItems = listOf(Home, Mylist, Downloads, Profile)
    }
}
