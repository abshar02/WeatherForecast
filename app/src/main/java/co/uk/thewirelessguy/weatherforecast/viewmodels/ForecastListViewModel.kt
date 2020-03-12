package co.uk.thewirelessguy.weatherforecast.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.uk.thewirelessguy.weatherforecast.models.Forecast
import co.uk.thewirelessguy.weatherforecast.repositories.ForecastListRepository
import kotlinx.coroutines.launch
import org.koin.dsl.module
import co.uk.thewirelessguy.weatherforecast.networking.Resource

val viewModelModule = module {
    factory { ForecastListViewModel(get()) }
}

class ForecastListViewModel(private val repository: ForecastListRepository) : ViewModel() {

    // Encapsulate access to mutable LiveData using backing property:
    private val _forecastListLiveData = MutableLiveData<Resource<Forecast>>()
    val forecastListLiveData: LiveData<Resource<Forecast>> = _forecastListLiveData

    fun getForecastLiveData() {
        viewModelScope.launch {
            val latestForecast = repository.fetchForecastList()

            _forecastListLiveData.postValue(latestForecast)
        }
    }
}