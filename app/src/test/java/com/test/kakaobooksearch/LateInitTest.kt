package com.test.kakaobooksearch

import org.junit.jupiter.api.Test

class LateInitTest {

    lateinit var string: String

    @Test
    fun test() {
        if (!::string.isInitialized) {
            string = "init"
            println(string)
        }
    }
}