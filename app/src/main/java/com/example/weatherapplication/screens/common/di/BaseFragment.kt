package com.example.weatherapplication.screens.common.di

import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

}