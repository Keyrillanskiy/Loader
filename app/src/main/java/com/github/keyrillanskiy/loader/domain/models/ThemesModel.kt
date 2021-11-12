package com.github.keyrillanskiy.loader.domain.models

data class ThemesModel(val data: List<ThemesDataModel>) {
    data class ThemesDataModel(val coursesCount: Int, val title: String)
}
