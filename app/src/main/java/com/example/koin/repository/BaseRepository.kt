package com.example.koin.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.koin.helper.SharedPrefHelper
import com.example.koin.network.MovieService
import kotlinx.coroutines.*
import org.koin.core.logger.KOIN_TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

abstract class BaseRepository<T>(@PublishedApi internal val service: MovieService, internal val sharedPrefHelper: SharedPrefHelper) {

    abstract fun loadMovies(): LiveData<T>

    inline fun <reified T : Any> fetchData(crossinline call: (MovieService) -> Call<T>): LiveData<T> {

        val result = MutableLiveData<T>()

        CoroutineScope(Dispatchers.IO).launch {
            val request = call(service)
            withContext(Dispatchers.Main) {
                try {
                    request.enqueue(object : Callback<T> {
                        override fun onFailure(call: Call<T>, t: Throwable) {
                            Log.d(KOIN_TAG, "onFailure ${t.message}")
                        }

                        override fun onResponse(call: Call<T>, response: Response<T>) {
                            if (!response.isSuccessful) {
                                Log.d(KOIN_TAG, "on Response error code: ${response.code()}")
                            }

                            if (response.isSuccessful) {
                                result.postValue(response.body())
                            }
                        }

                    })

                } catch (h: HttpException) {
                    Log.d(KOIN_TAG, "Error getting response: ${h.message()}")
                } catch (t: Throwable) {
                    Log.d(KOIN_TAG, "Ooops something really went wrong ${t.message}")
                }
            }
        }

        return result

    }
}