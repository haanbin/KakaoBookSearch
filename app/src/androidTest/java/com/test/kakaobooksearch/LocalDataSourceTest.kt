package com.test.kakaobooksearch

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.test.kakaobooksearch.data.entities.Meta
import com.test.kakaobooksearch.data.local.LocalDataSource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class LocalDataSourceTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var localDataSource: LocalDataSource

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun localDataSourceTest() = runBlocking {
        localDataSource.saveMeta(Meta(1, 1), "테스트")
        val metaDto = localDataSource.getMeta("테스트")
        println("metaDto : $metaDto")
        Assert.assertNotNull(metaDto)
    }
}
