package com.example.weatherapplication.utils

import android.view.View
import android.widget.ProgressBar

fun ProgressBar.changeVisibility(isVisible: Boolean) {
    if (isVisible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}