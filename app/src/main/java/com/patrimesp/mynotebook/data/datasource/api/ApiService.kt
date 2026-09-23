package com.patrimesp.mynotebook.data.datasource.api

import com.patrimesp.mynotebook.data.request.notes.NoteRequest
import com.patrimesp.mynotebook.data.response.notes.NoteResponse
import com.patrimesp.mynotebook.data.response.phrases.PhraseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiService {
    @GET("phrases/{id}.json")
    suspend fun getPhraseById(@Path("id") id: Int): PhraseResponse

    @POST("notes/.json")
    suspend fun addNote(@Body note: NoteRequest): NoteResponse
}
