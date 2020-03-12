package co.uk.thewirelessguy.weatherforecast.networking

import co.uk.thewirelessguy.weatherforecast.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import zerobranch.androidremotedebugger.logging.NetLoggingInterceptor

object ApiClient {

    private const val BASE_URL = "https://samples.openweathermap.org/"

    val getClient : ApiInterface by lazy {

        // OkHttpClient for building http request url:
        val client = OkHttpClient.Builder()

        /**
         * Stetho Interceptor: Inspect network calls made by the app using Chrome Developer Tools.
         * Connect your phone, start the app, and navigate to chrome://inspect on your development machine.
         * https://github.com/facebook/stetho
         **/
        if (BuildConfig.DEBUG) client.addNetworkInterceptor(StethoInterceptor())

        /**
         * Android Remote Debugger is a library for remote debugging Android applications. It allows
         * you to view logs, databases, shared preferences and network requests directly in the browser.
         * https://github.com/zerobranch/android-remote-debugger
         */
        if (BuildConfig.DEBUG) client.addInterceptor(NetLoggingInterceptor())

        val moshi = Moshi.Builder()
            // Add any additional adapters before this line:
            .add(KotlinJsonAdapterFactory())
            .build()

        return@lazy Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client.build())
            .build().create(ApiInterface::class.java)
    }

}