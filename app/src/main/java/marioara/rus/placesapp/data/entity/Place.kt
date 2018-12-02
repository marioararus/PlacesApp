package marioara.rus.placesapp.data.entity

data class Place(
        var location: Location?,
        var icon: String?,
        var id: String?,
        var name: String?,
        var openingHours: OpeningHours?,
        var photos: List<String>?,
        var placeId: String?,
        var scope: String?,
        var reference: String?,
        var types: List<String>?,
        var vicinity: String?
)
