package marioara.rus.placesapp.data.model.remote.retrofit.entity

data class RemotePlace(
        var geometry: RemoteGeometry?,
        var icon: String?,
        var id: String?,
        var name: String?,
        var opening_hours: RemoteOpeningHours?,
        var photos: List<RemotePhoto>?,
        var place_id: String?,
        var scope: String?,
        var reference: String?,
        var types: List<String>?,
        var vicinity: String?
)
