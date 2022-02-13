package com.example.weatherapplication.common.utils

import io.reactivex.rxjava3.core.Single

interface LocationServiceInterface {
    fun getLocation(): Single<Location>
}