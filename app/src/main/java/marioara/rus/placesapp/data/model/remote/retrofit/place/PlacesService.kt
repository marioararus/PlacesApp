package marioara.rus.placesapp.data.model.remote.retrofit.place

import kotlinx.coroutines.Deferred
import marioara.rus.placesapp.data.model.remote.retrofit.response.PlaceResponse
import marioara.rus.placesapp.data.model.remote.retrofit.response.PlacesResponse
import retrofit2.http.*

interface PlacesService {

    @Headers("Accept: application/json",
            "Content-Type: application/json")
    @POST("place/nearbysearch/json?rankby=distance&type=cafe&key=AIzaSyB3D9i2ToxdUihNTIRK-yCwSkGZe35NEr8&")
    fun getPlacesDetails(@Query(value = "location") latln: String): Deferred<PlacesResponse>

    @Headers("Accept: application/json",
            "Content-Type: application/json")
    @POST("place/details/json?key=AIzaSyB3D9i2ToxdUihNTIRK-yCwSkGZe35NEr8&")
    fun getPlaceDetails(@Query(value = "placeid") placeid: String): Deferred<PlaceResponse>


}
