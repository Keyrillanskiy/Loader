package com.github.keyrillanskiy.loader.domain.mappers

import com.github.keyrillanskiy.loader.data.network.ThemesResponse
import com.github.keyrillanskiy.loader.domain.models.ThemesModel

class ThemesMapper {
    fun map(response: ThemesResponse): ThemesModel {
        val data = response.data.map { dataItem ->
            ThemesModel.ThemesDataModel(
                coursesCount = dataItem.groups.map { it.items }.flatten().count(),
                title = dataItem.direction.title
            )
        }
        return ThemesModel(data)
    }
}
