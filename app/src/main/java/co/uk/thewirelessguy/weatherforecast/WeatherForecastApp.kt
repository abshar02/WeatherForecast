package co.uk.thewirelessguy.weatherforecast

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import co.uk.thewirelessguy.weatherforecast.repositories.forecastListRepositoryModule
import co.uk.thewirelessguy.weatherforecast.viewmodels.viewModelModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber
import zerobranch.androidremotedebugger.AndroidRemoteDebugger

class WeatherForecastApp : Application() {

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        initLogging()

        startKoin {
            androidLogger()
            androidContext(this@WeatherForecastApp)
            modules(
                viewModelModule,
                forecastListRepositoryModule
            )
        }
    }

    private fun initLogging() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
            AndroidRemoteDebugger.init(
                AndroidRemoteDebugger.Builder(applicationContext)
                    .enabled(true)
                    .disableInternalLogging()
                    .enableDuplicateLogging() // all logs from `Logging` section will also be printed in logcat
                    .build()
            )
            Timber.plant(Timber.DebugTree())
        }
    }
}