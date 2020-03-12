package co.uk.thewirelessguy.weatherforecast.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import co.uk.thewirelessguy.weatherforecast.BR
import co.uk.thewirelessguy.weatherforecast.R
import co.uk.thewirelessguy.weatherforecast.models.Forecast

class ForecastAdapter(private var items: MutableList<Forecast.ListEntries>, private val listener: (Forecast.ListEntries) -> Unit) : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.weather_list_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount() = items.size

    internal fun setData(forecast: MutableList<Forecast.ListEntries>?) {
        // Assign the list to the RecyclerView. If data is null, assign an empty list to the adapter.
        this.items = forecast ?: mutableListOf()
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Forecast.ListEntries, listener: (Forecast.ListEntries) -> Unit) = with(binding.root) {
            // Use data binding to display data in the view:
            binding.setVariable(BR.viewModel, item)
            binding.executePendingBindings()
            setOnClickListener { listener(item) }
        }
    }
}