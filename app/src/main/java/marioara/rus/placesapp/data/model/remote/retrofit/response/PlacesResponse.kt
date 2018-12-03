package marioara.rus.placesapp.data.model.remote.retrofit.response

import marioara.rus.placesapp.data.model.remote.retrofit.entity.RemotePlace

class PlacesResponse(var error_message: String?,
                     var html_attributions: List<String>?,
                     var results: List<RemotePlace>?,
                     var status: String?)
