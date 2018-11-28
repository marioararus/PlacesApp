package marioara.rus.placesapp.data.model

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnCompleteListener

/**
 *
 * Created by Marioara Rus on 2018-11-28.
 */

object LocationModel {

    private lateinit var fusedLocationClient: FusedLocationProviderClient


    /**
     * Get the current location of the user
     * @param application The instance of the application
     * @param onCompleteListener The callback of the application
     */
    @SuppressLint("MissingPermission")
    fun getCurrentLocation(application: Application, onCompleteListener: OnCompleteListener<Location>) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)
        fusedLocationClient.lastLocation.addOnCompleteListener(onCompleteListener)
    }
}
