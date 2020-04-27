# Android technical test using OpenWeatherMap

Android app displaying the 5 day weather forecast for London using the [OpenWeatherMap API sample data](http://openweathermap.org/forecast5).

## Technologies used:
* Android SDK
* Kotlin
* Data Binding
* [Retrofit](https://github.com/square/retrofit)
* [Moshi](https://github.com/square/moshi)
* Coroutines
* LiveData
* ViewModels
* Repository Pattern
* Dependency Injection using [Koin](https://github.com/InsertKoinIO/koin)
* [Coil](https://github.com/coil-kt/coil) image loading library
* [Stetho](http://facebook.github.io/stetho/) and [Android Remote Debugger](https://github.com/zerobranch/android-remote-debugger)
* [Timber](https://github.com/JakeWharton/timber)


## Build instructions:
* Clone the repository
* From Android Studio:
    * Open project from the folder you cloned it into.
    * From the menu select **Run** and then **Run 'App'**.
* From the command line:
    * MacOs/Linux:
        `./gradlew installDebug`
    * Windows:
        `gradlew installDebug`


## Todo:
* Improve general layout.
* Use day of week instead of full date
* Either add a detail view or expandable card to add additional weather information.
* Get weather based on current user location.
* Handle more than one weather condition for a requested location.
* UI and Unit tests.
