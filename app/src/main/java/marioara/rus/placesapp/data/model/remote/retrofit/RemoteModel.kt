package marioara.rus.placesapp.data.model.remote.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

abstract class RemoteModel {
    protected val retrofit: Retrofit

    companion object {
        val URL_PATH = "https://maps.googleapis.com/maps/api/"
    }

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(URL_PATH)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    }
}
