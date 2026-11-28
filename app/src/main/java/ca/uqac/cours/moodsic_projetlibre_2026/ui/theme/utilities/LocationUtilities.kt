package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.utilities

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

@SuppressLint("MissingPermission")
fun getCurrentLocation(
    context: Context,
    onReturnLocation: (Double?, Double?) -> Unit
){
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    fusedLocationClient.getCurrentLocation(
        Priority.PRIORITY_HIGH_ACCURACY,
        null
    ).addOnSuccessListener { location ->
        if (location != null) {
            onReturnLocation(location.latitude, location.longitude)
        } else {
            onReturnLocation(null, null)
        }
    }.addOnFailureListener {
        onReturnLocation(null, null)
    }
}