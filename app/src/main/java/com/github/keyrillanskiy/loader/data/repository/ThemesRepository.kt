package com.github.keyrillanskiy.loader.data.repository

import com.github.keyrillanskiy.loader.data.network.Api
import com.github.keyrillanskiy.loader.domain.mappers.ThemesMapper
import com.github.keyrillanskiy.loader.domain.models.ThemesModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemesRepository(private val api: Api, private val themesMapper: ThemesMapper) {
    suspend fun fetchThemes(): Flow<ThemesModel> {
        return api.fetchThemes().map { response -> themesMapper.map(response) }
    }
}
