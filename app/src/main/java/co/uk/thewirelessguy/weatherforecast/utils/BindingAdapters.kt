package co.uk.thewirelessguy.weatherforecast.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import co.uk.thewirelessguy.weatherforecast.extensions.loadUrl

/**
 * Data Binding adapters
 */
object BindingAdapters {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView: AppCompatImageView, url: String?) {
        url?.let { imageView.loadUrl(it) }
    }

}
