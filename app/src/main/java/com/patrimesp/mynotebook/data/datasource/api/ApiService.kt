package com.patrimesp.mynotebook.data.datasource.api

import com.patrimesp.mynotebook.data.response.PhraseResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("phrases/{id}.json")
    suspend fun getPhraseById(@Path("id") id: Int): PhraseResponse
}