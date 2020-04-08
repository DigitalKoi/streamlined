package io.github.reactivecircus.streamlined.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.github.reactivecircus.streamlined.BuildConfig
import io.github.reactivecircus.streamlined.remote.api.NewsApiService
import io.github.reactivecircus.streamlined.remote.di.RealRemoteComponent

@Module
object ServiceModule {

    @Provides
    @Reusable
    fun realRemoteComponent(): RealRemoteComponent {
        return RealRemoteComponent.factory().create(
            baseUrl = BuildConfig.BASE_URL,
            apiKey = BuildConfig.API_KEY
        )
    }

    @Provides
    @Reusable
    fun newsApiService(realRemoteComponent: RealRemoteComponent): NewsApiService {
        return realRemoteComponent.newsApiService
    }
}
