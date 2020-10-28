package com.example.githubcompose.networking

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class GithubApi  {
    companion object {
        private const val PROD = "https://api.github.com/"
        private const val GITHUB_ACCESS_TOKEN =
                "a9060141adbdd6754dce789e42eb73283e3972d3"
        const val CONNECT_TIMEOUT = 15
        const val READ_TIMEOUT = 15
    }

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val clientInterceptor = OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))
            .addInterceptor(logging)
            .addInterceptor { chain ->
                chain.proceed(
                        chain.request().newBuilder()
                                .addHeader(
                                        "Authentication",
                                        GITHUB_ACCESS_TOKEN
                                ).build()
                )
            }
            .build()

    var gson: Gson = GsonBuilder()
            .setLenient()
            .create()

    private val retrofit = Retrofit.Builder()
            .client(clientInterceptor)
            .baseUrl(PROD)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    val api: GithubApiCalls = retrofit.create(
            GithubApiCalls::class.java
    )
}
