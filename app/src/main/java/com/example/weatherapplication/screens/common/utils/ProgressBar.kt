package com.example.weatherapplication.screens.common.utils

import android.view.View
import android.widget.ProgressBar

fun ProgressBar.changeVisibility(isVisible: Boolean?) {
    if (isVisible ==true) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}