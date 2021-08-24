package com.test.kakaobooksearch

import org.junit.jupiter.api.Test

class LazyTest {

    val lazyValue: String by lazy {
        println("computed!")
        "Hello"
    }

    @Test
    fun test() {
        println(lazyValue)
        println(lazyValue)
    }
}