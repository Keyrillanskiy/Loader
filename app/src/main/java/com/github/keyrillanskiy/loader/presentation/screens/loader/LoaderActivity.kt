package com.github.keyrillanskiy.loader.presentation.screens.loader

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.github.keyrillanskiy.loader.R
import kotlinx.coroutines.flow.collect

class LoaderActivity : AppCompatActivity() {

    private val loaderViewModel by viewModels<LoaderViewModel>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loader)
        
        loaderViewModel.fetchThemes()
        
        lifecycleScope.launchWhenStarted { 
            loaderViewModel.themes.collect { result ->
                //TODO: show list
            }
        }
    }

}
