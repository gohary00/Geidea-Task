package com.example.geideatask.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class OkhttpClientModule @Inject constructor() {

    @Singleton
    @Provides
    fun authInterceptor(
    ) = Interceptor {
        val original = it.request()
        val originalHttpUrl = original.url
        val url = originalHttpUrl
            .newBuilder()
            .build()
        val requestBuilder = original.newBuilder().url(url)
        requestBuilder.addHeader("Content-Type", "application/json")
        requestBuilder.addHeader("os", "Android")
        it.proceed(original)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
    ): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
}
