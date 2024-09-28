package com.ohmz.tictactoe.core.models

interface CoreImageStore<T> {
    fun storeImage(image: T): Int

    fun getImage(id: Int): T?
}
