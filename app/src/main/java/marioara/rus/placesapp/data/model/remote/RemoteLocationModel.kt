package marioara.rus.placesapp.data.model.remote

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.places.*
import com.google.android.gms.tasks.OnCompleteListener


/**
 *
 * Created by Marioara Rus on 2018-11-28.
 */

object RemoteLocationModel {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    /**
     * Get the current location of the user
     * @param context The instance of the context
     * @param onCompleteListener The callback of the context
     */
    @SuppressLint("MissingPermission")
    fun getCurrentLocation(context: Context, onCompleteListener: OnCompleteListener<Location>) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.lastLocation.addOnCompleteListener(onCompleteListener)
    }
}
