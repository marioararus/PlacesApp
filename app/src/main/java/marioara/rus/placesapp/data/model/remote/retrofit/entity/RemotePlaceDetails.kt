package marioara.rus.placesapp.data.model.remote.retrofit.entity

data class RemotePlaceDetails(
        var formatted_address: String?,
        var place_id: String?,
        var name: String?,
        var photos: List<RemotePhoto>?
)
