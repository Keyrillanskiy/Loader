package com.github.keyrillanskiy.loader.data.network

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface Api {
    
    @GET("netology-code/rn-task/master/netology.json")
    suspend fun fetchThemes(): Flow<ThemesResponse>
    
}
