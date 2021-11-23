package com.example.weatherapplication.mappers

interface Mapper<F,T> {
    fun map(from:F):T
}