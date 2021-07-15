package com.test.kakaobooksearch

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test123() {
        val test = Test1234("123")
        val test2 = Test1234("123")
        val test3 = test
        println("test == test2 ${test == test2}")
        println("test === test2 ${test === test2}")
        println("test.hashCode() == test2.hashCode() ${test.hashCode() == test2.hashCode()}")
        println("test == test3 ${test == test3}")
        println("test === test3 ${test === test3}")
        println("test.hashCode() == test3.hashCode() ${test.hashCode() == test3.hashCode()}")
    }
}

class Test1234(val test: String) {

}
