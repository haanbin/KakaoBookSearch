package com.test.kakaobooksearch

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.test.kakaobooksearch.domain.GetSearchBooksUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.*
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class GetSearchBooksUseCaseTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var getSearchBooksUseCase: GetSearchBooksUseCase

    private val queryMap = HashMap<String, String>().apply {
        put("query", "미움받을 용기")
    }

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun getMovieUseCaseTest() {
        Assert.assertNotNull(getSearchBooksUseCase)
        val response = getSearchBooksUseCase.invoke(queryMap)
        response.doOnSubscribe {
            println("SUCCESS : $it")
        }.test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValue {
                println(it)
                it.isSuccessful
            }
            .assertComplete()

    }
}

