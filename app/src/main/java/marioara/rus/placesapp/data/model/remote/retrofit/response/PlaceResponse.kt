package marioara.rus.placesapp.data.model.remote.retrofit.response

import marioara.rus.placesapp.data.model.remote.retrofit.entity.RemotePlace
import marioara.rus.placesapp.data.model.remote.retrofit.entity.RemotePlaceDetails

class PlaceResponse(var error_message: String?,
                    var html_attributions: List<String>?,
                    var result: RemotePlaceDetails?,
                    var status: String?)
