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
    fun remotePlaceDetailsToPlaceDetails(remotePlaceDetails: RemotePlaceDetails?) =
            remotePlaceDetails?.run {
                PlaceDetails(formatted_address, place_id, name,
                        "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=$reference&key=AIzaSyB3D9i2ToxdUihNTIRK-yCwSkGZe35NEr8"
                )
            }

    /**
     * this method converts a [RemotePlace] to [Place]
     * @param remotePlace a place that comes from the server
     * @return a converted place
     */
    fun remotePlaceToPlace(remotePlace: RemotePlace) =
            remotePlace.run {
                Place(remoteGeometryToGeometry(geometry), icon, id, name,
                        remoteOpeningHoursToOpeningHours(openingHours),
                        remotePhotosToPhotos(photos), placeId, scope, reference, types, vicinity)
            }

    /**
     * this method converts a list of [RemotePlace] to a list of [Place]
     * @param remotePlace a place that comes from the server
     * @return a converted place
     */
    fun remotePlacesToPlaces(remotePlacesList: List<RemotePlace>?) =
            remotePlacesList?.map {
                remotePlaceToPlace(it)
            }

    /**
     * this method converts a list of [RemotePhoto] to a list of[Photo]
     * @param remotePhotos a list of photos that comes from the server
     * @return a converted list of photos
     */

    fun remotePhotosToPhotos(remotePhotos: List<RemotePhoto>?) =
            remotePhotos?.map {
                remotePhotoToPhoto(it)
            }

    /**
     * this method converts a [RemotePhoto] to [Photo]
     * @param remotePhoto a photo that comes from the server
     * @return a converted photo
     */
    fun remotePhotoToPhoto(remotePhoto: RemotePhoto) =
            remotePhoto.run {
                "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=$photo_reference&key=AIzaSyB3D9i2ToxdUihNTIRK-yCwSkGZe35NEr8"
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
