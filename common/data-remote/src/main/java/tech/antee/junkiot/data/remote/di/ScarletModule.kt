package tech.antee.junkiot.data.remote.di

import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.StreamAdapter
import com.tinder.scarlet.messageadapter.gson.GsonMessageAdapter
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import tech.antee.junkiot.data.remote.config.NetworkConfig
import tech.antee.junkiot.data.remote.di.qualifiers.ScarletClapsDetectorPredictionsQualifier
import tech.antee.junkiot.data.remote.di.qualifiers.ScarletControllersQualifier
import tech.antee.junkiot.data.remote.di.qualifiers.ScarletLightSensorPredictionsQualifier
import tech.antee.junkiot.data.remote.di.qualifiers.ScarletNoiseDetectorPredictionsQualifier
import tech.antee.junkiot.data.remote.di.qualifiers.ScarletOkHttpQualifier
import tech.antee.junkiot.data.remote.scarlet.FlowStreamAdapter
import javax.inject.Singleton

@Module
class ScarletModule {

    @Singleton
    @Provides
    @ScarletControllersQualifier
    fun scarletControllers(
        @ScarletOkHttpQualifier okHttpClient: OkHttpClient,
        streamAdapterFactory: StreamAdapter.Factory
    ): Scarlet = createScarletClient("controllers", okHttpClient, streamAdapterFactory)

    @Singleton
    @Provides
    @ScarletLightSensorPredictionsQualifier
    fun scarletLightSensorPredictions(
        @ScarletOkHttpQualifier okHttpClient: OkHttpClient,
        streamAdapterFactory: StreamAdapter.Factory
    ): Scarlet = createScarletClient("controllers/light-sensor/predictions", okHttpClient, streamAdapterFactory)

    @Singleton
    @Provides
    @ScarletClapsDetectorPredictionsQualifier
    fun scarletClapsDetectorValues(
        @ScarletOkHttpQualifier okHttpClient: OkHttpClient,
        streamAdapterFactory: StreamAdapter.Factory
    ): Scarlet = createScarletClient("controllers/claps-detector/values", okHttpClient, streamAdapterFactory)

    @Singleton
    @Provides
    @ScarletNoiseDetectorPredictionsQualifier
    fun scarletNoiseDetectorValues(
        @ScarletOkHttpQualifier okHttpClient: OkHttpClient,
        streamAdapterFactory: StreamAdapter.Factory
    ): Scarlet = createScarletClient("controllers/noise-detector/values", okHttpClient, streamAdapterFactory)

    @Singleton
    @Provides
    fun provideStreamAdapterFactory(): StreamAdapter.Factory = FlowStreamAdapter.Factory

    @Provides
    @Singleton
    @ScarletOkHttpQualifier
    fun okHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    private companion object {

        private fun createScarletClient(
            endpoint: String,
            okHttpClient: OkHttpClient,
            streamAdapterFactory: StreamAdapter.Factory
        ): Scarlet = Scarlet.Builder()
            .webSocketFactory(okHttpClient.newWebSocketFactory(NetworkConfig.WS_URL + endpoint))
            .addMessageAdapterFactory(GsonMessageAdapter.Factory())
            .addStreamAdapterFactory(streamAdapterFactory)
            .build()
    }
}
