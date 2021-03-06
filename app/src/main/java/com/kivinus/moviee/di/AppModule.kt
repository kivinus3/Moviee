package com.kivinus.moviee.di

import android.app.Application
import androidx.room.Room
import com.kivinus.moviee.api.TmdbApiService
import com.kivinus.moviee.db.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesApiService(): TmdbApiService = Retrofit.Builder()
        .baseUrl(TmdbApiService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient()
            .newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
        )
        .build()
        .create(TmdbApiService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(
        app: Application
    ) = Room
        .databaseBuilder(app, MovieDatabase::class.java, "movie_database")
        .build()

    @Provides
    fun provideTaskDao(db: MovieDatabase) = db.movieDao()


}