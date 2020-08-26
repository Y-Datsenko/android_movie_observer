package com.datsenko.movieobserver.utils

interface BaseMapper<F, T> {
    fun map(from: F): T
}

interface Mapper<F, T> : BaseMapper<F, T> {
    fun reverse(to: T): F
}
