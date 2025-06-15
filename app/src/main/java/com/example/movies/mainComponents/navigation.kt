package com.example.movies.mainComponents

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movies.room.RoomViewmodel
import com.example.movies.apiCall.FakeViewModel
import com.example.movies.firebase.FirebaseViewmodel
import com.example.movies.screens.HomePage
import com.example.movies.screens.MovieDetails


@Composable
fun Navigation(
    modifier: Modifier = Modifier,
){
    val navController = rememberNavController()
    val viewModel : FakeViewModel = viewModel()
    val roomViewmodel : RoomViewmodel = viewModel()
    val firebaseViewmodel : FirebaseViewmodel = viewModel()


    NavHost(
        navController = navController,
        startDestination = "homepage"
    ) {

        composable("homepage") {
            HomePage(
                modifier = modifier,
                viewModel = viewModel,
                navController = navController
            )
        }

        composable("details/{movieId}",
            arguments = listOf( navArgument("movieId"){ type = NavType.IntType})
        ) { back :NavBackStackEntry  ->

            val movieId = back.arguments?.getInt("movieId")

            val movie = viewModel.movies.value?.find { it.movieId == movieId }
            if (movie != null) {
                MovieDetails(
                    movie = movie,
                    viewModel = roomViewmodel,
                    firebaseViewmodel = firebaseViewmodel
                )
            }
        }
    }
}