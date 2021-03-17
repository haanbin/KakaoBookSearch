package com.test.kakaobooksearch

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.test.kakaobooksearch.data.AppRepository
import com.test.kakaobooksearch.data.entities.Document
import com.test.kakaobooksearch.data.entities.KakaoBook
import com.test.kakaobooksearch.data.entities.Meta
import com.test.kakaobooksearch.data.local.LocalDataSource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class AppRepositoryTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var appRepository: AppRepository

    private val queryMap: Map<String, String> = mapOf(
        "query" to "미움받을용기",
        "page" to "1",
        "size" to "50"
    )

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun appRepositoryTest() {
        val response = appRepository.getSearchBooksRx(queryMap)
        response.doOnSubscribe {
            println("SUCCESS : $it")
        }.test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValue {
                println(it)
                it.meta.pageableCount != 0
            }
            .assertComplete()
    }
}