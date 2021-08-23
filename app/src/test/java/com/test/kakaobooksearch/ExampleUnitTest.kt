package com.test.kakaobooksearch

import org.junit.jupiter.api.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest {

    private var _user = User("이름")
    private val user1 = _user
    private val user2
        get() = _user

    @Test
    fun `Backing Properties 테스트ㄱ`() {
        println("user1:: $user1")
        println("user2: $user2")
        println("_user: 재생성")
        _user = User("이름변경")
        println("user1:: $user1")
        println("user2: $user2")
    }

    data class User(var name: String)
}

