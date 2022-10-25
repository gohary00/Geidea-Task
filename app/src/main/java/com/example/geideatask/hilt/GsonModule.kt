package com.example.geideatask.hilt

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class GsonModule {
    val DEFAULT_SERVER_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ"

    @Provides
    @Singleton
    fun providesGson() =
        GsonBuilder()
            .setDateFormat(DEFAULT_SERVER_DATE_FORMAT)
            .setPrettyPrinting()
            .create()!!
}
