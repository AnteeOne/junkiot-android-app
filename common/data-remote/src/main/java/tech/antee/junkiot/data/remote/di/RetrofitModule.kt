package tech.antee.junkiot.data.remote.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.antee.junkiot.data.remote.config.NetworkConfig
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun retrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(NetworkConfig.HTTP_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun okHttpClient(): OkHttpClient = OkHttpClient.Builder().build()
}
