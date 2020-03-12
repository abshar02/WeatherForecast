package co.uk.thewirelessguy.weatherforecast.repositories

import co.uk.thewirelessguy.weatherforecast.models.Forecast
import org.koin.dsl.module
import co.uk.thewirelessguy.weatherforecast.networking.ApiClient
import co.uk.thewirelessguy.weatherforecast.networking.ApiInterface
import co.uk.thewirelessguy.weatherforecast.networking.Resource
import co.uk.thewirelessguy.weatherforecast.networking.ResponseHandler

val forecastListRepositoryModule = module {
    factory { ForecastListRepository() }
}

class ForecastListRepository {

    suspend fun fetchForecastList(): Resource<Forecast> {
        val client: ApiInterface = ApiClient.getClient
        val responseHandler = ResponseHandler()
        return try {
            val response = client.getWeatherForecast()
            return responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

}
