package com.example.movies.mainComponents
import android.content.Context
import androidx.room.Room
import com.example.movies.room.MovieDatabase
import com.example.movies.apiCall.MoviesApi
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl(): String = "https://jsonfakery.com"

    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()


    @Provides
    @Singleton
    fun provideRetrofit(gson : Gson, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideJobApiService(retrofit: Retrofit): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context : Context) : MovieDatabase {
        return Room.databaseBuilder(
            context = context,
            MovieDatabase::class.java, "MovieDetails"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFireStore() : FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

}
