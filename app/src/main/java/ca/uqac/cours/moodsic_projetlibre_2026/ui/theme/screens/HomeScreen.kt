package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.components.ScaffoldApp
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.viewmodels.HomeScreenViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import org.maplibre.compose.camera.CameraPosition
import org.maplibre.compose.camera.rememberCameraState
import org.maplibre.compose.map.MaplibreMap
import org.maplibre.compose.style.BaseStyle
import org.maplibre.spatialk.geojson.Position

@Composable
fun HomeScreen(modifier: Modifier = Modifier, vm : HomeScreenViewModel){
    HomeScreenContent(modifier = modifier, vm)
}

/*
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    ScaffoldApp(){
        innerPadding -> HomeScreenContent(modifier = Modifier.padding(innerPadding))
    }
}
*/

@Composable
fun HomeScreenContent(modifier: Modifier = Modifier, vm :  HomeScreenViewModel){
    MapScreen(vm)
}

@Composable
fun MapScreen(vm :  HomeScreenViewModel) {
    val cameraState = rememberCameraState(firstPosition = CameraPosition(target = Position(longitude = 2.3522, latitude = 48.8566), zoom = 13.0))

    //Demande de permission
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted -> vm.hasPermission = granted }

    LaunchedEffect(Unit) {
        if (!vm.hasPermission) launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    // Récupère la position dès que la permission est accordée


    LaunchedEffect(vm.hasPermission) { vm.GetPosition(); }
    LaunchedEffect(vm.userPosition) { vm.CenterCamera(cameraState) }

    MaplibreMap(
        modifier = Modifier.fillMaxSize(),
        baseStyle = BaseStyle.Uri("https://tiles.openfreemap.org/styles/liberty"),
        cameraState = cameraState
    )
}