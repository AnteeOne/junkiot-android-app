package tech.antee.junkiot.data.remote.di

import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.StreamAdapter
import com.tinder.scarlet.messageadapter.gson.GsonMessageAdapter
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import tech.antee.junkiot.data.remote.config.NetworkConfig
import tech.antee.junkiot.data.remote.scarlet.FlowStreamAdapter
import tech.antee.junkiot.data.remote.scarlet.ScarletOkHttpQualifier
import javax.inject.Singleton

@Module
class ScarletModule {

    @Singleton
    @Provides
    fun scarlet(
        @ScarletOkHttpQualifier okHttpClient: OkHttpClient,
        streamAdapterFactory: StreamAdapter.Factory
    ): Scarlet = Scarlet.Builder()
        .webSocketFactory(okHttpClient.newWebSocketFactory(NetworkConfig.WS_URL))
        .addMessageAdapterFactory(GsonMessageAdapter.Factory())
        .addStreamAdapterFactory(streamAdapterFactory)
        .build()

    @Singleton
    @Provides
    fun provideStreamAdapterFactory(): StreamAdapter.Factory = FlowStreamAdapter.Factory

    @Provides
    @Singleton
    @ScarletOkHttpQualifier
    fun okHttpClient(): OkHttpClient = OkHttpClient.Builder().build()
}


