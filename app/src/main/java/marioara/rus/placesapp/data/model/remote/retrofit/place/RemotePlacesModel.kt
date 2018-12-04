package marioara.rus.placesapp.data.model.remote.retrofit.place

import android.util.Log
import marioara.rus.placesapp.data.entity.Location
import marioara.rus.placesapp.data.entity.PlaceDetailsStatus
import marioara.rus.placesapp.data.entity.PlacesStatus
import marioara.rus.placesapp.data.model.remote.retrofit.RemoteEntitiesParser
import marioara.rus.placesapp.data.model.remote.retrofit.RemoteModel

class RemotePlacesModel(private val apiKey: String) : RemoteModel() {

    private val placesService: PlacesService = retrofit.create(PlacesService::class.java)

    suspend fun getAllPlacesNearby(location: Location): PlacesStatus? {
        val serverResponse = placesService.getPlacesDetails("${location.lat}, ${location.lng}", apiKey).await()
        return if (serverResponse.status == "OK") {
            val places = RemoteEntitiesParser.remotePlacesToPlaces(serverResponse.results, apiKey)
            PlacesStatus(places, null)
        } else {
            Log.e("getAllPlacesNearby", "${serverResponse.error_message}")
            PlacesStatus(null, serverResponse.error_message)
        }
    }

    suspend fun getPlaceDetails(placeId: String): PlaceDetailsStatus? {
        val serverResponse = placesService.getPlaceDetails(placeId, apiKey).await()
        return if (serverResponse.status == "OK") {
            val place = RemoteEntitiesParser.remotePlaceDetailsToPlaceDetails(serverResponse.result, apiKey)
            PlaceDetailsStatus(place, null)
        } else {
            Log.e("getPlaceDetails", "${serverResponse.error_message}")
            PlaceDetailsStatus(null, serverResponse.error_message)
        }
    }
}
