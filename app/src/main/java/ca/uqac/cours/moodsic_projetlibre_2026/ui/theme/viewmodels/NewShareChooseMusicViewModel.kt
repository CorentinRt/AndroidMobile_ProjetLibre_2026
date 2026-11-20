package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.deezer.DeezerTrack

class NewShareChooseMusicViewModel: ViewModel() {

    var searchText by mutableStateOf("")
        private set

    var tracks by mutableStateOf<List<DeezerTrack>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var selectedTrack by mutableStateOf<DeezerTrack?>(null)
        private set


    fun defineSearchText(searchText: String) {
        this.searchText = searchText
    }

    fun defineTracks(tracks: List<DeezerTrack>) {
        this.tracks = tracks
    }

    fun defineIsLoading(isLoading: Boolean) {
        this.isLoading = isLoading
    }


    fun defineErrorMessage(errorMessage: String?) {
        this.errorMessage = errorMessage
    }

    fun defineSelectedTrack(selectedTrack: DeezerTrack?) {
        this.selectedTrack = selectedTrack
    }
}