package com.example.movies.firebase

import android.content.Context
import android.widget.Toast
import com.example.movies.apiCall.Movie
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirebaseRepository @Inject constructor(
    private val fireStore: FirebaseFirestore
) {

    fun firebaseRepository(movie: Movie, context: Context){
        val cast = movie.casts.map{
            Casts(
                picUrl = it.profilePath,
                castName = it.name,
                popularity = it.popularity
            )
        }
        val storeMovie = MovieDetails(
            movie.posterPath,
            movie.originalTitle,
            movie.overview,
            movie.voteAverage,
            cast
        )
        fireStore.collection("MovieDetails")
            .document(storeMovie.movieName)
            .set(storeMovie)
            .addOnSuccessListener {
                Toast.makeText(context, "Downloaded to Firebase", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText(context, "Error Occurred", Toast.LENGTH_SHORT).show()
            }

    }
}
