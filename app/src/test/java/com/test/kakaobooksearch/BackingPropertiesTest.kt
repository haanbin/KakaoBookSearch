package com.test.kakaobooksearch

import org.junit.jupiter.api.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class BackingPropertiesTest {

    private var _user = User("이름")
    private val user1 = _user
    private val user2
        get() = _user

    @Test
    fun test() {
        println("user1: $user1")
        println("user2: $user2")
        println("_user: 재생성")
        _user = User("이름변경")
        println("user1: $user1")
        println("user2: $user2")
    }

    @Test
    fun test2() {
        val instruments = listOf("viola", "cello", "violin")
        val filtered = instruments.asSequence().filter { it[0] == 'v'}
        println("filtered: " + filtered) // filtered:
    }

    data class User(var name: String)
}

