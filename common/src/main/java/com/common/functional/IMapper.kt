package com.common.functional

interface IMapper<F, T> {
    fun map(from: F?): T
}
