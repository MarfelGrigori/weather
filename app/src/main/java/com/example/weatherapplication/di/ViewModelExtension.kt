package com.example.weatherapplication.di


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * For Fragments, allows declarations like
 * ```
 * val myViewModel = viewModelProvider(myViewModelFactory)
 * ```
 */
inline fun <reified VM : ViewModel> BaseFragment.viewModelProvider() =
    ViewModelProvider(this, viewModelFactory).get(VM::class.java)

/**
 for Fragments that want a [ViewModel] scoped to the Activity.
 */
inline fun <reified VM : ViewModel> BaseFragment.activityViewModelProvider() =
    ViewModelProvider(requireActivity(), viewModelFactory).get(VM::class.java)

/**
 for Fragments that want a [ViewModel] scoped to the parent
 * Fragment.
 */
inline fun <reified VM : ViewModel> BaseFragment.parentViewModelProvider() =
    ViewModelProvider(requireParentFragment(), viewModelFactory).get(VM::class.java)

inline fun <reified VM : ViewModel> BaseFragment.nestedParentViewModelProvider() =
    ViewModelProvider(requireParentFragment().requireParentFragment(), viewModelFactory).get(VM::class.java)
