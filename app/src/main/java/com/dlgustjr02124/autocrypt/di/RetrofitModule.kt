package com.dlgustjr02124.autocrypt.di

import com.dlgustjr02124.autocrypt.data.setting.Constant
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun providesConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(
            GsonBuilder()
                .create()
        )
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
            connectTimeout(Constant.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(Constant.READ_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(Constant.WRITE_TIMEOUT, TimeUnit.SECONDS)
        }
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        client: OkHttpClient.Builder,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.SERVER_URL)
            .client(client.build())
            .addConverterFactory(gsonConverterFactory)
            .client(client.build())
            .build()
    }
}