package marioara.rus.placesapp.data.model.remote.retrofit.place

import kotlinx.coroutines.Deferred
import marioara.rus.placesapp.data.model.remote.retrofit.response.PlaceDetailsResponse
import marioara.rus.placesapp.data.model.remote.retrofit.response.PlacesResponse
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface PlacesService {

    @Headers("Accept: application/json",
            "Content-Type: application/json")
    @POST("place/nearbysearch/json?rankby=distance&type=cafe&")
    fun getPlacesDetails(@Query(value = "location") latln: String,
                         @Query(value = "key") key: String): Deferred<PlacesResponse>

    @Headers("Accept: application/json",
            "Content-Type: application/json")
    @POST("place/details/json?")
    fun getPlaceDetails(@Query(value = "placeid") placeid: String,
                        @Query(value = "key") key: String): Deferred<PlaceDetailsResponse>


}
