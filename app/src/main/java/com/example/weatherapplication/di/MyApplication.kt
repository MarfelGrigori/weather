package com.example.weatherapplication.di

import android.app.Application

class MyApplication: Application() {
    val appComponent = DaggerApplicationComponent.create()
}