package com.test.kakaobooksearch.domain

import com.test.kakaobooksearch.data.AppRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSearchBooksRxUseCase @Inject constructor(private val appRepository: AppRepository) {

    operator fun invoke(queryMap: Map<String, String>) = appRepository.getSearchBooksRx(queryMap)
}