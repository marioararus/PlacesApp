package marioara.rus.placesapp.data.model.remote.retrofit

import marioara.rus.placesapp.data.entity.Location
import marioara.rus.placesapp.data.entity.OpeningHours
import marioara.rus.placesapp.data.entity.Place
import marioara.rus.placesapp.data.entity.PlaceDetails
import marioara.rus.placesapp.data.model.remote.retrofit.entity.*

object RemoteEntitiesParser {

    /**
     * this method converts a [RemotePlace] to [Place]
     * @param remotePlace a place that comes from the server
     * @return a converted place
     */
    fun remotePlaceDetailsToPlaceDetails(remotePlaceDetails: RemotePlaceDetails?, apiKey: String) =
            remotePlaceDetails?.run {
                PlaceDetails(formatted_address, place_id, name, remotePhotosToPhotos(photos, apiKey))
            }

    /**
     * this method converts a [RemotePlace] to [Place]
     * @param remotePlace a place that comes from the server
     * @return a converted place
     */
    fun remotePlaceToPlace(remotePlace: RemotePlace, apiKey: String) =
            remotePlace.run {
                Place(remoteGeometryToGeometry(geometry), icon, id, name,
                        remoteOpeningHoursToOpeningHours(opening_hours),
                        remotePhotosToPhotos(photos, apiKey), place_id, scope, reference, types, vicinity)
            }

    /**
     * this method converts a list of [RemotePlace] to a list of [Place]
     * @param remotePlace a place that comes from the server
     * @return a converted place
     */
    fun remotePlacesToPlaces(remotePlacesList: List<RemotePlace>?, apiKey: String) =
            remotePlacesList?.map {
                remotePlaceToPlace(it, apiKey)
            }

    /**
     * this method converts a list of [RemotePhoto] to a list of[Photo]
     * @param remotePhotos a list of photos that comes from the server
     * @return a converted list of photos
     */

    fun remotePhotosToPhotos(remotePhotos: List<RemotePhoto>?, apiKey: String) =
            remotePhotos?.map {
                remotePhotoToPhoto(it, apiKey)
            }

    /**
     * this method converts a [RemotePhoto] to [Photo]
     * @param remotePhoto a photo that comes from the server
     * @return a converted photo
     */
    fun remotePhotoToPhoto(remotePhoto: RemotePhoto, apiKey: String) =
            remotePhoto.run {
                "${RemoteModel.URL_PATH}place/photo?maxwidth=400&photoreference=$photo_reference&key=$apiKey"
            }

    /**
     * this method converts a [RemoteOpeningHours] to [OpeningHours]
     * @param remoteOpeningHours place's opening hours that comes from the server
     * @return a converted opening hour
     */
    fun remoteOpeningHoursToOpeningHours(remoteOpeningHours: RemoteOpeningHours?) =
            remoteOpeningHours?.run {
                OpeningHours(openNow)
            }


    /**
     * this method converts a [RemoteGeometry] to [Location]
     * @param remoteGeometry a location that comes from the server
     * @return a converted location
     */
    fun remoteGeometryToGeometry(remoteGeometry: RemoteGeometry?) =
            remoteGeometry?.run {
                Location(location?.lat, location?.lng)
            }
}
