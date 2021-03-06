package io.github.reactivecircus.streamlined

import coil.Coil.setDefaultImageLoader
import io.github.reactivecircus.streamlined.di.AppComponent
import io.github.reactivecircus.streamlined.di.IntegrationTestAppComponent
import io.github.reactivecircus.streamlined.testing.di.TestingFrameworkComponent
import timber.log.Timber

class IntegrationTestApp : StreamlinedApp() {

    override val appComponent: AppComponent = IntegrationTestAppComponent.factory()
        .create(
            context = this,
            testingFrameworkComponent = TestingFrameworkComponent.getOrCreate(this)
        )

    override fun onCreate() {
        // initialize Timber
        Timber.plant(DebugTree())

        // initialize analytics api (disable)
        appComponent.analyticsApi.setEnableAnalytics(false)

        // register lifecycle hook for NavigatorProvider
        registerActivityLifecycleCallbacks(
            appComponent.navigatorProvider as StreamlinedNavigatorProvider
        )

        // set default image loader
        setDefaultImageLoader(appComponent.imageLoader)
    }
}
