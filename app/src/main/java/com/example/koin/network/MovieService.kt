package com.example.koin.network

import com.example.koin.network_data.MovieList
import com.example.koin.network_data.ReviewList
import com.example.koin.network_data.TrailerList
import com.example.koin.util.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieService {

    @GET("top_rated")
    fun getTopRatedMovies(@Query("api_key") key: String): Call<MovieList>

    @GET("popular")
    fun getPopularMovies(@Query("api_key") key: String): Call<MovieList>

    @GET("{id}/videos")
    fun getTrailerList(@Path("id") id: Int, @Query("api_key") key: String): Call<TrailerList>

    @GET("{id}/reviews")
    fun getReviewList(@Path("id") id: Int, @Query("api_key") key: String): Call<ReviewList>

    companion object {

        fun getServiceInstance(): MovieService {
            val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY}
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(client)
                .build().create(MovieService::class.java)
        }
    }
}