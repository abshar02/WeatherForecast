package co.uk.thewirelessguy.weatherforecast.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import co.uk.thewirelessguy.weatherforecast.R
import co.uk.thewirelessguy.weatherforecast.extensions.setEmptyStateView
import co.uk.thewirelessguy.weatherforecast.models.Forecast
import co.uk.thewirelessguy.weatherforecast.viewmodels.ForecastListViewModel
import kotlinx.android.synthetic.main.fragment_weather_list.*
import kotlinx.android.synthetic.main.fragment_weather_list.view.*
import kotlinx.android.synthetic.main.fragment_weather_list.view.swipe_refresh
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import co.uk.thewirelessguy.weatherforecast.extensions.stop
import co.uk.thewirelessguy.weatherforecast.extensions.toast
import co.uk.thewirelessguy.weatherforecast.networking.Status

class WeatherListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var forecastAdapter: ForecastAdapter
    private val viewModel: ForecastListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather_list, container, false)

        if (view.weatherList is RecyclerView) {
            val context = view.context
            val recyclerView = view.weatherList

            recyclerView.apply {
                // Set LinearLayoutManager using default vertical list:
                layoutManager = LinearLayoutManager(context)

                //hasFixedSize() // Improve performance (use only with fixed size items)
                setItemViewCacheSize(20)

                // Set adapter and initialise with empty list:
                forecastAdapter = ForecastAdapter(mutableListOf()) {
                        item : Forecast.ListEntries -> itemClicked(item)
                }
                adapter = forecastAdapter

                // Set a view to display when list is empty:
                adapter?.setEmptyStateView(view.weatherListEmpty)
            }
        }

        view.swipe_refresh.setOnRefreshListener(this)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.forecastListLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> forecastAdapter.setData(it.data?.list?.toMutableList())
                Status.ERROR -> context?.toast("Error: "+it.message.toString())
                Status.LOADING -> showLoading()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        refreshData()
    }

    override fun onRefresh() {
        Timber.d("Refreshing data")
        // Handle pull down to refresh
        refreshData()
    }


    private fun refreshData() {
        Timber.d("Getting forecast...")
        viewModel.getForecastLiveData()
        swipe_refresh.stop()
    }

    private fun showLoading() {
        Timber.d("Loading...")
    }


    private fun itemClicked(item: Forecast.ListEntries) {
        context?.toast(item.weather[0].description)
    }
}