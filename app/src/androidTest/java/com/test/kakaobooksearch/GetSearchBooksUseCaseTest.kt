package com.test.kakaobooksearch

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.test.kakaobooksearch.domain.GetSearchBooksUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class GetSearchBooksUseCaseTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var getSearchBooksUseCase: GetSearchBooksUseCase

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
    fun getMovieUseCaseTest() = runBlocking {
        Assert.assertNotNull(getSearchBooksUseCase)
        val response = getSearchBooksUseCase(queryMap)
        Assert.assertNotNull(response)
    }
}
