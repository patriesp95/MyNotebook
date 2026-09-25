package com.patrimesp.mynotebook.data.di

import com.patrimesp.mynotebook.data.api.ApiConfig.BASE_URL
import com.patrimesp.mynotebook.data.api.ApiService
import com.patrimesp.mynotebook.data.database.datasource.local.NoteLocalDataSource
import com.patrimesp.mynotebook.data.repository.notes.NoteRepositoryImpl
import com.patrimesp.mynotebook.data.repository.phrases.PhraseRepositoryImpl
import com.patrimesp.mynotebook.domain.repository.notes.NoteRepository
import com.patrimesp.mynotebook.domain.repository.phrases.PhraseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideMainRepository(api: ApiService): PhraseRepository = PhraseRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideNoteRepository(
        api: ApiService,
        noteLocalDataSource: NoteLocalDataSource
    ): NoteRepository = NoteRepositoryImpl(api, noteLocalDataSource)

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(json: Json): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }

}
