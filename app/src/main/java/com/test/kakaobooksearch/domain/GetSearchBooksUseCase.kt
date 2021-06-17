package com.test.kakaobooksearch.domain

import com.test.kakaobooksearch.data.AppRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSearchBooksUseCase @Inject constructor(private val appRepository: AppRepository) {

    suspend operator fun invoke(queryMap: Map<String, String>) = appRepository.getSearchBooks(queryMap)
}
