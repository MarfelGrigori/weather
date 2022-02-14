package com.example.weatherapplication.common.utils

import io.reactivex.rxjava3.core.Single

interface LocationService {
    fun getLocation(): Single<Location>
}