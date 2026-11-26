package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.viewmodels

import android.app.Application
import android.content.Context
import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.application
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import org.maplibre.compose.camera.CameraPosition
import org.maplibre.compose.camera.CameraState
import org.maplibre.spatialk.geojson.Position

class HomeScreenViewModel(application: Application) : AndroidViewModel(application) {

    val context: Context = application.applicationContext

    var hasPermission by mutableStateOf(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)

    var userPosition by  mutableStateOf<Position?>(null)

    @SuppressLint("MissingPermission")
    suspend fun GetPosition(){
        if (hasPermission) {
            LocationServices.getFusedLocationProviderClient(context)
                .getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
                .addOnSuccessListener { loc ->
                    loc?.let { userPosition = Position(longitude = it.longitude, latitude = it.latitude) }
                }
            }
        }

    suspend fun CenterCamera(cameraState : CameraState){
        userPosition?.let { cameraState.animateTo(CameraPosition(target = it, zoom = 16.0)) }
    }

}