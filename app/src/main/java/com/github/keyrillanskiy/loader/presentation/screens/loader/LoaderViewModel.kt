package com.github.keyrillanskiy.loader.presentation.screens.loader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.github.keyrillanskiy.loader.data.network.Api
import com.github.keyrillanskiy.loader.data.network.createRetrofit
import com.github.keyrillanskiy.loader.data.repository.ThemesRepository
import com.github.keyrillanskiy.loader.domain.mappers.ThemesMapper
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

/**
 * По хорошему эта инициализация должна делаться в специальном месте с помощью какой-либо библиотеки
 * dependency injection, но для простоты все нужные объекты для вьюмодели создаются напрямую.
 */
class LoaderViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass == LoaderViewModel::class.java) {
            val api = createRetrofit().create(Api::class.java)
            LoaderViewModel(themesRepository = ThemesRepository(api = api, themesMapper = ThemesMapper())) as T
        } else {
            throw IllegalArgumentException("Unknown view model class: ${modelClass.simpleName}")
        }
    }
}
