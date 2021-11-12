package com.github.keyrillanskiy.loader.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

private const val BASE_URL = "https://raw.githubusercontent.com"

fun createRetrofit(): Retrofit {
    val contentType = "application/json".toMediaType()
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient())
        .addConverterFactory(jsonConfiguration().asConverterFactory(contentType))
        .build()
}

fun httpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
}

fun jsonConfiguration(): Json = Json { ignoreUnknownKeys = true }
