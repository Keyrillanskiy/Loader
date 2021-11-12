package com.github.keyrillanskiy.loader.presentation.screens.loader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.keyrillanskiy.loader.data.repository.ThemesRepository
import com.github.keyrillanskiy.loader.domain.models.ThemesModel
import com.github.keyrillanskiy.loader.presentation.RequestResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoaderViewModel(private val themesRepository: ThemesRepository) : ViewModel() {

    private val _themes = MutableStateFlow<RequestResult<ThemesModel>>(RequestResult.Loading())
    val themes = _themes.asStateFlow()

    fun fetchThemes() {
        viewModelScope.launch {
            themesRepository.fetchThemes().collect { model -> _themes.value = RequestResult.Success(model) }
        }
    }

}
