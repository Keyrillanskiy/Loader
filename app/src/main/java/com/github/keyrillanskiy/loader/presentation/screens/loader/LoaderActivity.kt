package com.github.keyrillanskiy.loader.presentation.screens.loader

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.github.keyrillanskiy.loader.databinding.ActivityLoaderBinding
import com.github.keyrillanskiy.loader.presentation.RequestResult
import kotlinx.coroutines.flow.collect

class LoaderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoaderBinding
    private val loaderViewModel by viewModels<LoaderViewModel> { LoaderViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = LoaderAdapter()
        binding.themesRecyclerView.adapter = adapter
        
        loaderViewModel.fetchThemes()

        lifecycleScope.launchWhenStarted {
            loaderViewModel.themes.collect { result ->
                when (result) {
                    is RequestResult.Loading -> binding.themesProgressLayout.visibility = View.VISIBLE
                    is RequestResult.Success -> {
                        binding.themesProgressLayout.visibility = View.GONE
                        adapter.submitList(result.data.data)
                    }
                }
            }
        }
    }

}
