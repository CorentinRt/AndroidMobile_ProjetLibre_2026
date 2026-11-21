package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.viewmodels

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.deezer.DeezerTrack

class NewShareGlobalViewModel: ViewModel() {

    var currentPhotoTakenUri: Uri? by mutableStateOf(null)
        private set

    var currentSelectedTrack: DeezerTrack? by mutableStateOf(null)
        private set

    fun defineCurrentPhotoTakenUri(photoTakenUri: Uri){
        this.currentPhotoTakenUri = photoTakenUri
    }

    fun defineCurrentSelectedTrack(selectedTrack: DeezerTrack){
        this.currentSelectedTrack = selectedTrack
    }

}