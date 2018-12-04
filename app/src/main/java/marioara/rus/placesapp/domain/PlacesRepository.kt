package marioara.rus.placesapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import marioara.rus.placesapp.data.entity.Location
import marioara.rus.placesapp.data.entity.PlaceDetailsStatus
import marioara.rus.placesapp.data.entity.PlacesStatus
import marioara.rus.placesapp.data.model.remote.retrofit.place.RemotePlacesModel


class PlacesRepository(apiKey: String) {
    private val remotePlacesModel = RemotePlacesModel(apiKey)
    private val mListLiveData: MutableLiveData<PlacesStatus> = MutableLiveData()
    private val mPlaceDetailsDetailsLiveData: MutableLiveData<PlaceDetailsStatus> = MutableLiveData()

    fun getPlaces(lat: Double, lng: Double): LiveData<PlacesStatus>? {
        GlobalScope.launch {
            val placeStatus = remotePlacesModel.getAllPlacesNearby(Location(lat, lng))

            // Return all the remote clinics
            mListLiveData.postValue(placeStatus)
        }
        return mListLiveData
    }

    fun getPlaceDetails(placeId: String): LiveData<PlaceDetailsStatus>? {
        GlobalScope.launch {
            val placeStatus = remotePlacesModel.getPlaceDetails(placeId)

            // Return all the remote clinics
            mPlaceDetailsDetailsLiveData.postValue(placeStatus)
        }
        return mPlaceDetailsDetailsLiveData
    }
}
