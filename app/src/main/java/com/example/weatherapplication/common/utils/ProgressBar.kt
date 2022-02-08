package com.example.weatherapplication.common.utils

import android.view.View
import android.widget.ProgressBar

fun ProgressBar.changeVisibility(isVisible: Boolean?) {
    if (isVisible ==true) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}