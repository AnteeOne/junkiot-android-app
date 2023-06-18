package tech.antee.junkiot.data.remote.di.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ScarletControllersQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ScarletLightSensorPredictionsQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ScarletClapsDetectorPredictionsQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ScarletNoiseDetectorPredictionsQualifier
