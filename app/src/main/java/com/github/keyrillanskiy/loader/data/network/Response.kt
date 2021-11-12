package com.github.keyrillanskiy.loader.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ThemesResponse(@SerialName("data") val data: List<ThemesDataResponse>) {
    
    @Serializable
    data class ThemesDataResponse(
        @SerialName("groups") val groups: List<ThemeGroupResponse>,
        @SerialName("direction") val direction: ThemeDirectionResponse
    ) {
        
        @Serializable
        data class ThemeGroupResponse(@SerialName("items") val items: List<ThemeGroupItemResponse>) {
            @Serializable
            class ThemeGroupItemResponse
        }

        @Serializable
        data class ThemeDirectionResponse(@SerialName("title") val title: String)
        
    }
    
}
